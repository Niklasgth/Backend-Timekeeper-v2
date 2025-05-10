package com.tidsrapport.backend.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tidsrapport.backend.api.service.TaskService;
import com.tidsrapport.backend.api.model.Task;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // GET /api/tasks
    @GetMapping
    public List<Task> getTasks() {
        return service.getAllTasks();
    }

    // GET /api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") String id) {
        return service.getTaskById(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/tasks
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.createTask(task);
    }

    // PUT /api/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable("id") String id,
            @RequestBody Task updatedTask
    ) {
        return service.updateTask(id, updatedTask)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // PATCH /api/tasks/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<Task> patchTask(
            @PathVariable("id") String id,
            @RequestBody Map<String, Object> updates
    ) {
        // 1) Hämta eventuell Task
        Optional<Task> maybeExisting = service.getTaskById(id);
        if (maybeExisting.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Task existing = maybeExisting.get();

        // 2) Applicera bara de fält som finns i request-body
        if (updates.containsKey("categoryId")) {
            existing.setCategoryId((String) updates.get("categoryId"));
        }
        if (updates.containsKey("startTime")) {
            // Vi förutsätter att klienten skickar en ISO-tidssträng, t.ex. "2025-05-09T10:15:30+02:00"
            existing.setStartTime((String) updates.get("startTime"));
        }
        if (updates.containsKey("endTime")) {
            existing.setEndTime((String) updates.get("endTime"));
        }

        // 3) Spara via din updateTask-metod
        Optional<Task> maybePatched = service.updateTask(id, existing);
        if (maybePatched.isPresent()) {
            return ResponseEntity.ok(maybePatched.get());
        } else {
            // Om något gick fel vid uppdateringen
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
