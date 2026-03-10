package com.jpa.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa.project.Entity.Users;
import com.jpa.project.Repository.UserRepository;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Users u = new Users();
		u.setName("jay");
		u.setAge(25);
		u.setActive(false);
		u.setUserId(10);
		userRepository.save(u);

	}
}