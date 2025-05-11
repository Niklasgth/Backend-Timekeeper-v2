//DTO för säkerställa kommunikationen går som jag vill mellan backend och frontend.

package com.tidsrapport.backend.api.dto;

import com.tidsrapport.backend.api.model.Task;

public class TaskDto {
    private String id;
    private String categoryId;
    private String categoryName;
    private String startTime;
    private String endTime;
    private long duration;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.categoryId = task.getCategoryId();
        this.categoryName = task.getCategoryName();
        this.startTime = task.getStartTime();
        this.endTime = task.getEndTime();
        this.duration = task.getDuration();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
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
}
