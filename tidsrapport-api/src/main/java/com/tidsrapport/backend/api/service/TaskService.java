package com.tidsrapport.backend.api.service;

import com.tidsrapport.backend.api.model.Task;
import com.tidsrapport.backend.api.repository.TaskRepository;
import com.tidsrapport.backend.api.repository.TaskCategoryRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskCategoryRepository categoryRepository;

    public TaskService(TaskRepository repository, TaskCategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    // Hämta alla tasks
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // Hämta task med id
    public Optional<Task> getTaskById(String id) {
        return repository.findById(id);
    }

    // Skapa en ny task
    public Task createTask(Task task) {
        OffsetDateTime start = OffsetDateTime.parse(task.getStartTime());
        OffsetDateTime end = OffsetDateTime.parse(task.getEndTime());
        long durationInSeconds = Duration.between(start, end).getSeconds();
        task.setDuration(durationInSeconds);

        //  Lägg till kategori-namn från categoryId
        if (task.getCategoryId() != null) {
            categoryRepository.findById(task.getCategoryId())
                .ifPresent(category -> task.setCategoryName(category.getName()));
        }

        return repository.save(task);
    }

    // Uppdatera en task
    public Optional<Task> updateTask(String id, Task updatedTask) {
        return repository.findById(id).map(existingTask -> {
            existingTask.setCategoryId(updatedTask.getCategoryId());

            //  Uppdatera kategori-namnet
            if (updatedTask.getCategoryId() != null) {
                categoryRepository.findById(updatedTask.getCategoryId())
                    .ifPresent(category -> existingTask.setCategoryName(category.getName()));
            }

            existingTask.setStartTime(updatedTask.getStartTime());
            existingTask.setEndTime(updatedTask.getEndTime());

            OffsetDateTime start = OffsetDateTime.parse(updatedTask.getStartTime());
            OffsetDateTime end = OffsetDateTime.parse(updatedTask.getEndTime());
            long durationInSeconds = Duration.between(start, end).getSeconds();
            existingTask.setDuration(durationInSeconds);

            return repository.save(existingTask);
        });
    }

    // Ta bort en task
    public boolean deleteTask(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
