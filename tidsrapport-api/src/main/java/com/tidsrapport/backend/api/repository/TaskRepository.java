package com.tidsrapport.backend.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tidsrapport.backend.api.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {
    
}
