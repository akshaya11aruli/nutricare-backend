package com.nutricare.ai.service;

import com.nutricare.ai.dto.*;
import com.nutricare.ai.entity.*;
import com.nutricare.ai.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WellnessService {

    private final WellnessProfileRepository profileRepository;
    private final WaterLogRepository waterLogRepository;
    private final MealSelectionRepository mealSelectionRepository;
    private final MealPlanRepository mealPlanRepository;
    private final UserReminderRepository reminderRepository;
    private final ActivityLogRepository activityLogRepository;
    private final ShoppingItemRepository shoppingItemRepository;
    private final SupportRequestRepository supportRequestRepository;

    public WellnessService(WellnessProfileRepository profileRepository,
                           WaterLogRepository waterLogRepository,
                           MealSelectionRepository mealSelectionRepository,
                           MealPlanRepository mealPlanRepository,
                           UserReminderRepository reminderRepository,
                           ActivityLogRepository activityLogRepository,
                           ShoppingItemRepository shoppingItemRepository,
                           SupportRequestRepository supportRequestRepository) {
        this.profileRepository = profileRepository;
        this.waterLogRepository = waterLogRepository;
        this.mealSelectionRepository = mealSelectionRepository;
        this.mealPlanRepository = mealPlanRepository;
        this.reminderRepository = reminderRepository;
        this.activityLogRepository = activityLogRepository;
        this.shoppingItemRepository = shoppingItemRepository;
        this.supportRequestRepository = supportRequestRepository;
    }

    // PROFILE
    @Transactional
    public WellnessProfile saveProfile(ProfileDto dto) {
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("User ID is required");
        }

        WellnessProfile profile = profileRepository.findByUserId(dto.getUserId())
                .orElse(new WellnessProfile());

        profile.setUserId(dto.getUserId());
        if (dto.getDisplayName() != null) profile.setDisplayName(dto.getDisplayName());
        profile.setWellnessGoal(dto.getWellnessGoal() != null ? dto.getWellnessGoal() : "");
        profile.setDietaryPreference(dto.getDietaryPreference() != null ? dto.getDietaryPreference() : "");
        profile.setAllergies(dto.getAllergies() != null ? dto.getAllergies() : "");
        profile.setActivityLevel(dto.getActivityLevel() != null ? dto.getActivityLevel() : "");
        profile.setMealTimings(dto.getMealTimings() != null ? dto.getMealTimings() : "");
        profile.setDailyBudget(dto.getDailyBudget());
        if (dto.getPreferredUnits() != null) profile.setPreferredUnits(dto.getPreferredUnits());
        if (dto.getLikedFoods() != null) profile.setLikedFoods(dto.getLikedFoods());
        if (dto.getDislikedFoods() != null) profile.setDislikedFoods(dto.getDislikedFoods());
        if (dto.getOtherRestrictions() != null) profile.setOtherRestrictions(dto.getOtherRestrictions());
        if (dto.getAdditionalNotes() != null) profile.setAdditionalNotes(dto.getAdditionalNotes());

        return profileRepository.save(profile);
    }

    @Transactional(readOnly = true)
    public WellnessProfile getProfile(Long userId) {
        return profileRepository.findByUserId(userId).orElse(null);
    }

    // WATER
    @Transactional
    public WaterLog saveWater(WaterDto dto) {
        if (dto.getUserId() == null || dto.getGlasses() == null) {
            throw new IllegalArgumentException("Valid user ID and water count are required");
        }

        LocalDate date = dto.getLogDate() != null ? dto.getLogDate() : LocalDate.now();
        WaterLog waterLog = waterLogRepository.findByUserIdAndLogDate(dto.getUserId(), date)
                .orElse(new WaterLog(dto.getUserId(), date, 0));

        waterLog.setGlasses(dto.getGlasses());
        return waterLogRepository.save(waterLog);
    }

    @Transactional(readOnly = true)
    public int getWater(Long userId, LocalDate date) {
        LocalDate logDate = date != null ? date : LocalDate.now();
        return waterLogRepository.findByUserIdAndLogDate(userId, logDate)
                .map(WaterLog::getGlasses)
                .orElse(0);
    }

    @Transactional(readOnly = true)
    public List<WaterLog> getWaterHistory(Long userId, int days) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(Math.max(1, days - 1));
        return waterLogRepository.findByUserIdAndLogDateBetweenOrderByLogDateAsc(userId, startDate, endDate);
    }

    // MEAL SELECTION (Today / legacy compatibility)
    @Transactional
    public MealSelection saveMealSelection(MealSelectionDto dto) {
        if (dto.getUserId() == null || dto.getCategory() == null || dto.getMealId() == null) {
            throw new IllegalArgumentException("Valid user and meal selection are required");
        }

        LocalDate date = dto.getLogDate() != null ? dto.getLogDate() : LocalDate.now();
        MealSelection selection = mealSelectionRepository
                .findByUserIdAndLogDateAndCategory(dto.getUserId(), date, dto.getCategory())
                .orElse(new MealSelection(dto.getUserId(), date, dto.getCategory(), dto.getMealId().trim(), dto.getMealTitle().trim()));

        selection.setMealId(dto.getMealId().trim());
        selection.setMealTitle(dto.getMealTitle().trim());
        MealSelection saved = mealSelectionRepository.save(selection);

        // Also sync into meal_plans table so weekly plan stays consistent
        Optional<MealPlan> existingPlan = mealPlanRepository
                .findByUserIdAndPlanDateAndMealCategory(dto.getUserId(), date, dto.getCategory());
        if (existingPlan.isPresent()) {
            MealPlan plan = existingPlan.get();
            plan.setMealId(dto.getMealId().trim());
            plan.setMealName(dto.getMealTitle().trim());
            mealPlanRepository.save(plan);
        } else {
            MealPlan plan = new MealPlan(dto.getUserId(), date, dto.getCategory(), dto.getMealId().trim(), dto.getMealTitle().trim());
            mealPlanRepository.save(plan);
        }

        return saved;
    }

    @Transactional(readOnly = true)
    public List<MealSelectionDto> getMealSelections(Long userId, LocalDate date) {
        LocalDate logDate = date != null ? date : LocalDate.now();
        return mealSelectionRepository.findByUserIdAndLogDate(userId, logDate).stream()
                .map(m -> new MealSelectionDto(m.getCategory(), m.getMealId(), m.getMealTitle()))
                .collect(Collectors.toList());
    }

    // REMINDERS
    @Transactional
    public UserReminder saveReminders(ReminderDto dto) {
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("User ID is required");
        }

        UserReminder reminder = reminderRepository.findByUserId(dto.getUserId())
                .orElse(new UserReminder(dto.getUserId()));

        if (dto.getEnabled() != null) reminder.setEnabled(dto.getEnabled());
        if (dto.getBreakfast() != null) reminder.setBreakfast(dto.getBreakfast());
        if (dto.getLunch() != null) reminder.setLunch(dto.getLunch());
        if (dto.getSnack() != null) reminder.setSnack(dto.getSnack());
        if (dto.getDinner() != null) reminder.setDinner(dto.getDinner());
        if (dto.getWater() != null) reminder.setWater(dto.getWater());
        if (dto.getMovement() != null) reminder.setMovement(dto.getMovement());
        if (dto.getWinddown() != null) reminder.setWinddown(dto.getWinddown());

        return reminderRepository.save(reminder);
    }

    @Transactional(readOnly = true)
    public UserReminder getReminders(Long userId) {
        return reminderRepository.findByUserId(userId)
                .orElseGet(() -> new UserReminder(userId));
    }

    // ACTIVITY PLANNER
    @Transactional
    public ActivityLog saveActivity(ActivityDto dto) {
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("User ID is required");
        }

        LocalDate date = dto.getLogDate() != null ? dto.getLogDate() : LocalDate.now();
        ActivityLog log = activityLogRepository.findByUserIdAndLogDate(dto.getUserId(), date)
                .orElse(new ActivityLog(dto.getUserId(), date));

        if (dto.getSteps() != null) log.setSteps(dto.getSteps());
        if (dto.getStepTarget() != null) log.setStepTarget(dto.getStepTarget());
        if (dto.getExerciseMinutes() != null) log.setExerciseMinutes(dto.getExerciseMinutes());
        if (dto.getCaloriesBurned() != null) log.setCaloriesBurned(dto.getCaloriesBurned());
        if (dto.getRoutineChecksJson() != null) log.setRoutineChecksJson(dto.getRoutineChecksJson());

        return activityLogRepository.save(log);
    }

    @Transactional(readOnly = true)
    public ActivityLog getActivity(Long userId, LocalDate date) {
        LocalDate logDate = date != null ? date : LocalDate.now();
        return activityLogRepository.findByUserIdAndLogDate(userId, logDate)
                .orElseGet(() -> new ActivityLog(userId, logDate));
    }

    @Transactional(readOnly = true)
    public List<ActivityLog> getActivityHistory(Long userId, int days) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(Math.max(1, days - 1));
        return activityLogRepository.findByUserIdAndLogDateBetweenOrderByLogDateAsc(userId, startDate, endDate);
    }

    // SHOPPING LIST
    @Transactional
    public void saveShoppingItems(ShoppingDto dto) {
        if (dto.getUserId() == null || dto.getItems() == null) return;

        for (ShoppingDto.Item item : dto.getItems()) {
            Optional<ShoppingItem> existing = shoppingItemRepository
                    .findByUserIdAndItemKey(dto.getUserId(), item.getId());
            if (existing.isPresent()) {
                ShoppingItem si = existing.get();
                if (item.getName() != null && !item.getName().trim().isEmpty()) {
                    si.setItemName(item.getName().trim());
                }
                if (item.getChecked() != null) {
                    si.setChecked(Boolean.TRUE.equals(item.getChecked()));
                }
                shoppingItemRepository.save(si);
            } else {
                ShoppingItem si = new ShoppingItem(
                        dto.getUserId(),
                        item.getId(),
                        item.getName(),
                        item.getSource(),
                        Boolean.TRUE.equals(item.getChecked()),
                        Boolean.TRUE.equals(item.getIsCustom())
                );
                shoppingItemRepository.save(si);
            }
        }
    }

    @Transactional(readOnly = true)
    public List<ShoppingItem> getShoppingItems(Long userId) {
        return shoppingItemRepository.findByUserId(userId);
    }

    @Transactional
    public void deleteShoppingItem(Long userId, String itemKey) {
        shoppingItemRepository.deleteByUserIdAndItemKey(userId, itemKey);
    }

    @Transactional
    public void deleteCheckedShoppingItems(Long userId) {
        shoppingItemRepository.deleteByUserIdAndCheckedTrue(userId);
    }

    @Transactional
    public void clearCustomShoppingItems(Long userId) {
        shoppingItemRepository.deleteByUserIdAndIsCustomTrue(userId);
    }

    // SUPPORT
    @Transactional
    public SupportRequest saveSupportRequest(SupportDto dto) {
        SupportRequest req = new SupportRequest(
                dto.getUserId(),
                dto.getUserEmail(),
                dto.getTopic(),
                dto.getMessage()
        );
        return supportRequestRepository.save(req);
    }

    // PROGRESS SUMMARY
    @Transactional(readOnly = true)
    public Map<String, Object> getProgressSummary(Long userId) {
        Map<String, Object> summary = new HashMap<>();

        LocalDate today = LocalDate.now();
        int todayWater = getWater(userId, today);
        ActivityLog todayActivity = getActivity(userId, today);
        List<MealPlan> weeklyPlans = mealPlanRepository.findByUserId(userId);
        UserReminder reminders = getReminders(userId);

        summary.put("today_water_glasses", todayWater);
        summary.put("today_water_ml", todayWater * 250);
        summary.put("today_steps", todayActivity.getSteps());
        summary.put("today_step_target", todayActivity.getStepTarget());
        summary.put("today_active_minutes", todayActivity.getExerciseMinutes());
        summary.put("today_calories_burned", todayActivity.getCaloriesBurned());
        summary.put("total_meals_planned", weeklyPlans.size());
        summary.put("reminders_enabled", reminders.getEnabled());

        return summary;
    }
}
