
//Till för att generera lite modeller om inga finns vid uppstart

package com.tidsrapport.backend.api.service;

import com.tidsrapport.backend.api.model.TaskCategory;
import com.tidsrapport.backend.api.repository.TaskCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(TaskCategoryRepository taskCategoryRepository) {
        return args -> {
            // Kontrollera om det finns några kategorier i databasen
            if (taskCategoryRepository.count() == 0) {
                // Om inga kategorier finns, lägg till standardkategorier
                TaskCategory category1 = new TaskCategory("Läsa");
                TaskCategory category2 = new TaskCategory("Programmera");
                TaskCategory category3 = new TaskCategory("Lunch");
                TaskCategory category4 = new TaskCategory("Paus");

                // Spara dem i databasen
                taskCategoryRepository.saveAll(List.of(category1, category2, category3, category4));
                System.out.println("Standardkategorier har lagts till i databasen.");
            }
        };
    }
}