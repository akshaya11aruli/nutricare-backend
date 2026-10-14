package com.nutricare.ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private static final List<Map<String, Object>> RECIPES = List.of(
            // Breakfast
            Map.of("id", "pastel-smoothie-bowl", "category", "Breakfast", "title", "The Pastel Smoothie Bowl", "prepTime", "10 mins", "calories", 310),
            Map.of("id", "chia-pudding-parfait", "category", "Breakfast", "title", "The Multi-Layered Chia Pudding Parfait", "prepTime", "15 mins", "calories", 290),
            Map.of("id", "baked-berry-oatmeal", "category", "Breakfast", "title", "Slow-Baked Berry & Vanilla Oatmeal", "prepTime", "35 mins", "calories", 340),
            Map.of("id", "green-goddess-omelet", "category", "Breakfast", "title", "Herbed Green Goddess Soufflé Omelet", "prepTime", "15 mins", "calories", 320),
            Map.of("id", "matcha-overnight-oats", "category", "Breakfast", "title", "Matcha & Coconut Cream Overnight Oats", "prepTime", "10 mins", "calories", 330),
            Map.of("id", "banana-oat-pancakes", "category", "Breakfast", "title", "Fluffy Golden Banana & Oat Blender Pancakes", "prepTime", "20 mins", "calories", 360),
            Map.of("id", "spinach-feta-egg-souffle", "category", "Breakfast", "title", "Baked Spinach, Dill & Greek Feta Soufflé Cups", "prepTime", "25 mins", "calories", 280),

            // Lunch
            Map.of("id", "crispy-chickpea-sweet-potato", "category", "Lunch", "title", "Crispy Chickpea & Sweet Potato Bliss Bowl", "prepTime", "30 mins", "calories", 420),
            Map.of("id", "falafel-mediterranean-bowl", "category", "Lunch", "title", "Deconstructed Falafel Mediterranean Bowl", "prepTime", "25 mins", "calories", 450),
            Map.of("id", "rainbow-tofu-buddha-bowl", "category", "Lunch", "title", "Rainbow Edamame & Crispy Tofu Buddha Bowl", "prepTime", "25 mins", "calories", 390),
            Map.of("id", "chili-lime-chicken-lettuce-cups", "category", "Lunch", "title", "Zesty Chili-Lime Chicken Lettuce Cups", "prepTime", "20 mins", "calories", 350),
            Map.of("id", "smoked-tofu-edamame-poke", "category", "Lunch", "title", "Sesame-Crusted Smoked Tofu & Edamame Poke", "prepTime", "20 mins", "calories", 410),
            Map.of("id", "warm-grain-veggie-bowl", "category", "Lunch", "title", "Warm Quinoa, Roasted Veggie & Tahini Bowl", "prepTime", "30 mins", "calories", 440),
            Map.of("id", "modernist-avocado-egg-boat", "category", "Lunch", "title", "Whipped Tahini & Jammy Egg Sweet Potato Boats", "prepTime", "25 mins", "calories", 380),

            // Snacks
            Map.of("id", "halloumi-avocado-flatbread", "category", "Snacks", "title", "Charred Avocado & Grilled Halloumi Flatbread", "prepTime", "15 mins", "calories", 290),
            Map.of("id", "salmon-cucumber-rollups", "category", "Snacks", "title", "Seared Salmon & Cucumber Ribbon Roll-Ups", "prepTime", "15 mins", "calories", 210),
            Map.of("id", "stuffed-veggie-wrap", "category", "Snacks", "title", "Roasted Pepper, Spinach & Whipped Ricotta Pinwheels", "prepTime", "12 mins", "calories", 240),
            Map.of("id", "deconstructed-avocado-toast", "category", "Snacks", "title", "Modernist Deconstructed Avocado Tartine", "prepTime", "10 mins", "calories", 260),
            Map.of("id", "turkey-avocado-bento-skewers", "category", "Snacks", "title", "Smoked Turkey & Herb Avocado Bento Skewers", "prepTime", "10 mins", "calories", 190),
            Map.of("id", "lentil-soup-toast", "category", "Snacks", "title", "Silky Spiced Red Lentil Sipping Broth", "prepTime", "20 mins", "calories", 220),
            Map.of("id", "pesto-egg-veggie-bowl", "category", "Snacks", "title", "Walnut Basil Pesto & Jammy Egg Snack Bowl", "prepTime", "12 mins", "calories", 280),

            // Dinner
            Map.of("id", "vegetable-dal-khichdi", "category", "Dinner", "title", "Comforting Vegetable Moong Dal Khichdi", "prepTime", "35 mins", "calories", 410),
            Map.of("id", "paneer-veggie-stirfry", "category", "Dinner", "title", "Golden Herb-Crusted Paneer & Veggie Sauté", "prepTime", "25 mins", "calories", 440),
            Map.of("id", "turkey-meatball-couscous", "category", "Dinner", "title", "Spiced Herb Turkey Meatballs & Fluffy Couscous", "prepTime", "35 mins", "calories", 480),
            Map.of("id", "dinner-protein-bowl", "category", "Dinner", "title", "Golden Pan-Seared Salmon & Asparagus Platter", "prepTime", "25 mins", "calories", 510),
            Map.of("id", "chipotle-chicken-fajita", "category", "Dinner", "title", "Smoky Chipotle Chicken & Roasted Pepper Skillet", "prepTime", "30 mins", "calories", 470),
            Map.of("id", "fresh-dinner-salad", "category", "Dinner", "title", "Heirloom Tomato, Cucumber & Herb Dinner Mezze", "prepTime", "15 mins", "calories", 330),
            Map.of("id", "korean-sunny-egg-bibimbap", "category", "Dinner", "title", "Warm Brown Rice Bibimbap with Crispy Sunny Egg", "prepTime", "30 mins", "calories", 460)
    );

    @GetMapping
    public ResponseEntity<Map<String, Object>> getRecipes(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search) {
        List<Map<String, Object>> filtered = RECIPES.stream()
                .filter(r -> category == null || category.equalsIgnoreCase("all") || category.equalsIgnoreCase((String) r.get("category")))
                .filter(r -> search == null || search.trim().isEmpty() || ((String) r.get("title")).toLowerCase().contains(search.trim().toLowerCase()))
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("total", filtered.size());
        response.put("recipes", filtered);
        return ResponseEntity.ok(response);
    }
}
