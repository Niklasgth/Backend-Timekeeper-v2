package com.tidsrapport.backend.api.controller;

import com.tidsrapport.backend.api.dto.TaskCategoryDto;
import com.tidsrapport.backend.api.model.TaskCategory;
import com.tidsrapport.backend.api.service.TaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-categories")
public class TaskCategoryController {

    @Autowired
    private TaskCategoryService taskCategoryService;

    // Hämta alla kategorier
    @GetMapping
  public List<TaskCategoryDto> getCategories() {
        return taskCategoryService.getAllCategories().stream()
         .map(TaskCategoryDto::new)
                  .toList();
    }

    // Hämta kategori med id
    @GetMapping("/{id}")
    public TaskCategoryDto getCategoryById(@PathVariable String id) {
    return new TaskCategoryDto(taskCategoryService.getCategoryById(id));
}

    // Skapa en ny kategori
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskCategory createCategory(@RequestBody TaskCategory taskCategory) {
        return taskCategoryService.createCategory(taskCategory);
    }

    // Uppdatera en kategori
    @PutMapping("/{id}")
    public TaskCategory updateCategory(@PathVariable String id, @RequestBody TaskCategory taskCategory) {
        return taskCategoryService.updateCategory(id, taskCategory);
    }
}
