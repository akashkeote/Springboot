package com.transactional.spring.service;

import org.springframework.stereotype.Service;

import com.transactional.spring.Dao.CategoryDao;
import com.transactional.spring.Dao.CourseDao;
import com.transactional.spring.entities.Category;
import com.transactional.spring.entities.Course;

@Service
public class secondayway_transactional {
    private CourseDao courseDao;
    private CategoryDao categoryDao;

    public secondayway_transactional(CourseDao courseDao, CategoryDao categoryDao) {
        this.courseDao = courseDao;
        this.categoryDao = categoryDao;
    }
    public void saveCategoryThanCourse(Category category, Course course) {
        try {
            categoryDao.set(category);
            courseDao.insert(course);
            System.out.println("Both category and course saved successfully.");
        } catch (Exception e) {
            System.out.println("Error occurred while saving category and course: " + e.getMessage());
        }
    }

}
