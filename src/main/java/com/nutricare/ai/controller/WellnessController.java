package com.nutricare.ai.controller;

import com.nutricare.ai.dto.*;
import com.nutricare.ai.entity.*;
import com.nutricare.ai.service.WellnessService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wellness")
public class WellnessController {

    private final WellnessService wellnessService;

    public WellnessController(WellnessService wellnessService) {
        this.wellnessService = wellnessService;
    }

    // PROFILE
    @PostMapping("/profile")
    public ResponseEntity<Map<String, Object>> saveProfile(@RequestBody ProfileDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            WellnessProfile profile = wellnessService.saveProfile(dto);
            response.put("success", true);
            response.put("message", "Wellness profile saved successfully!");
            response.put("profile", profile);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage() != null ? e.getMessage() : "Could not save wellness profile.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<Map<String, Object>> getProfile(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        WellnessProfile profile = wellnessService.getProfile(userId);
        response.put("success", true);
        response.put("profile", profile);
        return ResponseEntity.ok(response);
    }

    // WATER
    @PostMapping("/water")
    public ResponseEntity<Map<String, Object>> saveWater(@RequestBody WaterDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            wellnessService.saveWater(dto);
            response.put("success", true);
            response.put("message", "Water count saved successfully!");
            response.put("glasses", dto.getGlasses());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage() != null ? e.getMessage() : "Could not save water count.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/water/{userId}")
    public ResponseEntity<Map<String, Object>> getWater(
            @PathVariable Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Map<String, Object> response = new HashMap<>();
        int glasses = wellnessService.getWater(userId, date);
        response.put("success", true);
        response.put("glasses", glasses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/water/history/{userId}")
    public ResponseEntity<Map<String, Object>> getWaterHistory(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "7") int days) {
        Map<String, Object> response = new HashMap<>();
        List<WaterLog> history = wellnessService.getWaterHistory(userId, days);
        response.put("success", true);
        response.put("history", history);
        return ResponseEntity.ok(response);
    }

    // MEAL SELECTIONS (Today / single selection)
    @PostMapping("/meals")
    public ResponseEntity<Map<String, Object>> saveMealSelection(@RequestBody MealSelectionDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            wellnessService.saveMealSelection(dto);
            response.put("success", true);
            response.put("message", "Meal selection saved successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage() != null ? e.getMessage() : "Could not save your meal selection.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/meals/{userId}")
    public ResponseEntity<Map<String, Object>> getMealSelections(
            @PathVariable Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Map<String, Object> response = new HashMap<>();
        List<MealSelectionDto> meals = wellnessService.getMealSelections(userId, date);
        response.put("success", true);
        response.put("meals", meals);
        return ResponseEntity.ok(response);
    }

    // REMINDERS
    @PostMapping("/reminders")
    public ResponseEntity<Map<String, Object>> saveReminders(@RequestBody ReminderDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserReminder reminder = wellnessService.saveReminders(dto);
            response.put("success", true);
            response.put("message", "Reminders saved successfully.");
            response.put("reminders", reminder);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage() != null ? e.getMessage() : "Could not save reminders.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/reminders/{userId}")
    public ResponseEntity<Map<String, Object>> getReminders(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        UserReminder reminder = wellnessService.getReminders(userId);
        response.put("success", true);
        response.put("reminders", reminder);
        return ResponseEntity.ok(response);
    }

    // ACTIVITY PLANNER
    @PostMapping("/activity")
    public ResponseEntity<Map<String, Object>> saveActivity(@RequestBody ActivityDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            ActivityLog log = wellnessService.saveActivity(dto);
            response.put("success", true);
            response.put("message", "Activity logged successfully.");
            response.put("activity", log);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage() != null ? e.getMessage() : "Could not save activity.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/activity/{userId}")
    public ResponseEntity<Map<String, Object>> getActivity(
            @PathVariable Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Map<String, Object> response = new HashMap<>();
        ActivityLog log = wellnessService.getActivity(userId, date);
        response.put("success", true);
        response.put("activity", log);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activity/history/{userId}")
    public ResponseEntity<Map<String, Object>> getActivityHistory(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "7") int days) {
        Map<String, Object> response = new HashMap<>();
        List<ActivityLog> history = wellnessService.getActivityHistory(userId, days);
        response.put("success", true);
        response.put("history", history);
        return ResponseEntity.ok(response);
    }

    // SHOPPING LIST
    @PostMapping("/shopping")
    public ResponseEntity<Map<String, Object>> saveShopping(@RequestBody ShoppingDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            wellnessService.saveShoppingItems(dto);
            response.put("success", true);
            response.put("message", "Shopping list saved successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage() != null ? e.getMessage() : "Could not save shopping list.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/shopping/{userId}")
    public ResponseEntity<Map<String, Object>> getShopping(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        List<ShoppingItem> items = wellnessService.getShoppingItems(userId);
        response.put("success", true);
        response.put("items", items);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/shopping/{userId}/{itemKey}")
    public ResponseEntity<Map<String, Object>> deleteShoppingItem(
            @PathVariable Long userId,
            @PathVariable String itemKey) {
        Map<String, Object> response = new HashMap<>();
        try {
            wellnessService.deleteShoppingItem(userId, itemKey);
            response.put("success", true);
            response.put("message", "Shopping item deleted.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage() != null ? e.getMessage() : "Could not delete shopping item.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/shopping/{userId}")
    public ResponseEntity<Map<String, Object>> clearShoppingItems(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "all") String scope) {
        Map<String, Object> response = new HashMap<>();
        try {
            if ("checked".equalsIgnoreCase(scope)) {
                wellnessService.deleteCheckedShoppingItems(userId);
                response.put("message", "Cleared purchased items.");
            } else {
                wellnessService.clearCustomShoppingItems(userId);
                response.put("message", "Cleared custom items.");
            }
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Could not clear items.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // SUPPORT REQUESTS
    @PostMapping("/support")
    public ResponseEntity<Map<String, Object>> submitSupport(@Valid @RequestBody SupportDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            SupportRequest saved = wellnessService.saveSupportRequest(dto);
            response.put("success", true);
            response.put("message", "Thank you for your feedback! Your note has been recorded. 🌸");
            response.put("ticketId", saved.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Could not submit support ticket.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // PROGRESS REPORTS
    @GetMapping("/progress/{userId}")
    public ResponseEntity<Map<String, Object>> getProgress(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> summary = wellnessService.getProgressSummary(userId);
        response.put("success", true);
        response.put("progress", summary);
        return ResponseEntity.ok(response);
    }
}
