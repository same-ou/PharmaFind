package com.ensam.pharmafind;

import com.ensam.pharmafind.entities.Role;
import com.ensam.pharmafind.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
		return args -> {
			// create RULES
			if(roleRepository.findByName("USER").isEmpty())
				roleRepository.save( Role.builder().name("USER").build());
			if(roleRepository.findByName("PHARMACIST").isEmpty())
				roleRepository.save( Role.builder().name("PHARMACIST").build());
			if(roleRepository.findByName("ADMIN").isEmpty())
				roleRepository.save( Role.builder().name("ADMIN").build());
		};
	}
}
