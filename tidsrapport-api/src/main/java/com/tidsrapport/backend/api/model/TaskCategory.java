package com.tidsrapport.backend.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "taskCategories")
public class TaskCategory {

    @Id
    private String id;

    private String name;

    private Instant createdAt = Instant.now(); // Sätter tid automatiskt vid skapande

    public TaskCategory() {}

    public TaskCategory(String name) {
        this.name = name;
        this.createdAt = Instant.now();
    }

    // Getters och setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
