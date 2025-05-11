package com.tidsrapport.backend.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tidsrapport.backend.api.service.TaskService;
import com.tidsrapport.backend.api.model.Task;
import com.tidsrapport.backend.api.dto.TaskDto;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // GET /api/tasks
   @GetMapping
public List<TaskDto> getTasks() {
    return service.getAllTasks().stream()
                  .map(TaskDto::new)
                  .toList();
}

   // GET /api/tasks/{id}
@GetMapping("/{id}")
public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") String id) {
    return service.getTaskById(id)
                  .map(task -> ResponseEntity.ok(new TaskDto(task)))
                  .orElse(ResponseEntity.notFound().build());
}


    // POST /api/tasks
   @PostMapping
public TaskDto createTask(@RequestBody Task task) {
    Task saved = service.createTask(task);
    return new TaskDto(saved);
}


  @PutMapping("/{id}")
public ResponseEntity<TaskDto> updateTask(
        @PathVariable("id") String id,
        @RequestBody Task updatedTask
) {
    return service.updateTask(id, updatedTask)
                  .map(t -> ResponseEntity.ok(new TaskDto(t)))
                  .orElse(ResponseEntity.notFound().build());
}

   @PatchMapping("/{id}")
public ResponseEntity<TaskDto> patchTask(
        @PathVariable("id") String id,
        @RequestBody Map<String, Object> updates
) {
    Optional<Task> maybeExisting = service.getTaskById(id);
    if (maybeExisting.isEmpty()) return ResponseEntity.notFound().build();

    Task existing = maybeExisting.get();
    if (updates.containsKey("categoryId")) existing.setCategoryId((String) updates.get("categoryId"));
    if (updates.containsKey("startTime"))  existing.setStartTime((String) updates.get("startTime"));
    if (updates.containsKey("endTime"))    existing.setEndTime((String) updates.get("endTime"));

    return service.updateTask(id, existing)
                  .map(t -> ResponseEntity.ok(new TaskDto(t)))
                  .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
}

    // DELETE /api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") String id) {
        if (service.deleteTask(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
