package com.database.springboot_part;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.database.springboot_part.Dao.CategoryDao;
import com.database.springboot_part.entities.Category;

import com.database.springboot_part.entities.Course;

import com.database.springboot_part.Dao.CourseDao;

@SpringBootApplication
public class akash implements CommandLineRunner {
	// @Autowired
	// Category category;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	CourseDao cD;

	public static void main(String[] args) {
		SpringApplication.run(akash.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Category c = new Category();
		// c.setId(190);
		// c.setTitle("ja");
		// c.setDescription("this aksh db ");
		// Category s= categoryDao.set(c);
		// System.out.println(s.getId() +" "+s.getTitle()+" "+s.getDescription());

		// courses
		//Course c = new Course();
		// c.setCourseId(101);
		// c.setCourseTitle("java");
		// c.setCourseDescription("this is java course");
		// c.setCoursePrice(1000);
		// c.setCategoryId(9);
		// Course s = cD.insert(c);
		// System.out.println(s.getCourseId() + " " + s.getCourseTitle() + " " + s.getCourseDescription() + " "
		// 		+ s.getCoursePrice() + " " + s.getCategoryId());
		List<Course> c1=cD.getAllCou();
		Course c = new Course();
		c1.forEach(ak->System.out.println(ak.getCourseId()));


	
			}

}
