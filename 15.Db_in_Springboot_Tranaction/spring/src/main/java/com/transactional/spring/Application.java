package com.transactional.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.transactional.spring.Dao.CategoryDao;
import com.transactional.spring.Dao.CourseDao;
import com.transactional.spring.entities.Category;
import com.transactional.spring.entities.Course;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	CourseDao courseDao;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// saveCategoryThanCourse
		Category category = new Category();
		category.setId(101);
		category.setTitle("Programming");
		category.setDescription("Courses related to software development");

		Course c = new Course();
		c.setCourseId(1001);
		c.setCourseTitle("Spring JDBC Masterclass");
		c.setCourseDescription("Learn JDBC operations with Spring");
		c.setCoursePrice(1499);
		c.setCategoryId(category.getId());

		courseDao.saveCategoryThanCourse(category, c);
	}

}
