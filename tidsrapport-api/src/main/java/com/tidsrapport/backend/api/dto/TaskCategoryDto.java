package com.tidsrapport.backend.api.dto;

import com.tidsrapport.backend.api.model.TaskCategory;

public class TaskCategoryDto {
    private String id;
    private String name;

    public TaskCategoryDto(TaskCategory category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
