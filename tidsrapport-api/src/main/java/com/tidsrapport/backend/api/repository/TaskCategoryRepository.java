package com.tidsrapport.backend.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tidsrapport.backend.api.model.TaskCategory;

@Repository
public interface TaskCategoryRepository extends MongoRepository<TaskCategory, String> {
  
}
