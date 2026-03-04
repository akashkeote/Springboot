package com.database.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.database.springboot.Dao.CategoryDao;
import com.database.springboot.entities.Category;

@SpringBootApplication
public class Main implements CommandLineRunner {
	// @Autowired
    // Category category;
	@Autowired
	CategoryDao categoryDao;
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Category c = new Category();
		c.setId(190);
		c.setTitle("ja");
		c.setDescription("this aksh db ");
	Category s=	categoryDao.set(c);
	System.out.println(s.getId() +" "+s.getTitle()+" "+s.getDescription());

	}

}
