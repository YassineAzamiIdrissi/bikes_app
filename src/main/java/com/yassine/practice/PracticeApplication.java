package com.yassine.practice;

import com.yassine.practice.Authority.Authority;
import com.yassine.practice.Authority.AuthorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "appAuditorAware")
@EnableAsync
public class PracticeApplication {
    public static void main(String[] args) {

        SpringApplication.run(PracticeApplication.class, args);
        System.out.println("Hello World!");
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthorityRepository authRepo) {
        return args -> {
            if (authRepo.findByAuthority("USER").isEmpty()) {
                authRepo.save(Authority.builder().authority("USER").build());
            }
        };
    }


}
