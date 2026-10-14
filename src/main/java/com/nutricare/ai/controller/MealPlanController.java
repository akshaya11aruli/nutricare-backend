package com.nutricare.ai.controller;

import com.nutricare.ai.dto.MealPlanDto;
import com.nutricare.ai.service.MealPlanService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/api/wellness/plans", "/api/plans"})
public class MealPlanController {

    private final MealPlanService mealPlanService;

    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> savePlan(@RequestBody MealPlanDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            MealPlanDto saved = mealPlanService.saveOrUpdatePlan(dto);
            response.put("success", true);
            response.put("message", "Weekly meal plan entry saved successfully!");
            response.put("plan", saved);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage() != null ? e.getMessage() : "Could not save weekly meal plan.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/batch")
    public ResponseEntity<Map<String, Object>> batchSavePlans(
            @RequestParam Long userId,
            @RequestBody List<MealPlanDto> plans) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<MealPlanDto> saved = mealPlanService.batchSavePlans(userId, plans);
            response.put("success", true);
            response.put("message", "Weekly meal plans synchronized successfully!");
            response.put("plans", saved);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage() != null ? e.getMessage() : "Could not synchronize meal plans.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getPlans(
            @PathVariable Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Map<String, Object> response = new HashMap<>();
        List<MealPlanDto> plans = mealPlanService.getPlans(userId, startDate, endDate);
        response.put("success", true);
        response.put("plans", plans);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> deletePlan(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String category) {
        Map<String, Object> response = new HashMap<>();
        try {
            mealPlanService.deletePlan(userId, date, category);
            response.put("success", true);
            response.put("message", "Meal selection cleared successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage() != null ? e.getMessage() : "Could not clear meal selection.");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
