package com.nutricare.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ShoppingDto {

    @JsonProperty("user_id")
    private Long userId;

    private List<Item> items;

    public static class Item {
        private String id;
        private String name;
        private String source;
        private Boolean checked;

        @JsonProperty("is_custom")
        private Boolean isCustom;

        public Item() {}

        public Item(String id, String name, String source, Boolean checked, Boolean isCustom) {
            this.id = id;
            this.name = name;
            this.source = source;
            this.checked = checked;
            this.isCustom = isCustom;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Boolean getChecked() {
            return checked;
        }

        public void setChecked(Boolean checked) {
            this.checked = checked;
        }

        public Boolean getIsCustom() {
            return isCustom;
        }

        public void setIsCustom(Boolean isCustom) {
            this.isCustom = isCustom;
        }
    }

    public ShoppingDto() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
