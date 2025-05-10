package com.tidsrapport.backend.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {
    @Id
    private String id;

    private String categoryId; 
    private String categoryName;

    private String startTime;
    private String endTime;
    private long duration;

    public Task() {}

    public Task(String categoryId, String categoryName, String startTime, String endTime, long duration) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    // Getters & setters
    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
