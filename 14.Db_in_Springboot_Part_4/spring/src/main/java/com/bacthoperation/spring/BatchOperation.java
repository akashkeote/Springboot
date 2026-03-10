package com.bacthoperation.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bacthoperation.spring.Dao.CourseDao;
import com.bacthoperation.spring.Dao.CategoryDao;
import com.bacthoperation.spring.entities.Course;
import com.bacthoperation.spring.entities.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BatchOperation implements CommandLineRunner {
	@Autowired
	CourseDao cd;

	@Autowired
	CategoryDao catDao;

	@Override
	public void run(String... args) throws Exception {
		// Create and insert a category first
		Category category = new Category(1, "Programming", "Programming courses");
		catDao.set(category);

		// Create a list of courses for batch operation
		List<Course> courses = new ArrayList<>();
		courses.add(new Course(1, "Java Programming", "Learn Java basics", 299, 1));
		courses.add(new Course(2, "Spring Boot", "Master Spring Boot framework", 399, 1));
		courses.add(new Course(3, "Database Design", "SQL and database concepts", 349, 1));

		// Perform batch save operation
		cd.saveCourseInBatch(courses);
		System.out.println("Batch operation completed successfully!");

	}

	public static void main(String[] args) {
		SpringApplication.run(BatchOperation.class, args);
	}
}