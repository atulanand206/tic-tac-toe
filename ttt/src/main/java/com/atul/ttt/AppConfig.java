package com.atul.ttt;

import com.atul.ttt.repository.Repo;
import com.atul.ttt.repository.InMemoryRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    Repo repo() {
        return new InMemoryRepo();
    }
}
