package com.tidsrapport.backend.api.service;

import com.tidsrapport.backend.api.model.TaskCategory;
import com.tidsrapport.backend.api.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskCategoryService {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    // Hämta alla kategorier
    public List<TaskCategory> getAllCategories() {
        return taskCategoryRepository.findAll();
    }

    // Hämta kategori med id
    public TaskCategory getCategoryById(String id) {
        Optional<TaskCategory> category = taskCategoryRepository.findById(id);
        return category.orElseThrow(() -> new RuntimeException("TaskCategory not found"));
    }

    // Skapa en ny kategori
    public TaskCategory createCategory(TaskCategory taskCategory) {
        return taskCategoryRepository.save(taskCategory);
    }

    // Uppdatera en kategori
    public TaskCategory updateCategory(String id, TaskCategory taskCategory) {
        if (!taskCategoryRepository.existsById(id)) {
            throw new RuntimeException("TaskCategory not found");
        }
        taskCategory.setId(id);
        return taskCategoryRepository.save(taskCategory);
    }
}
