package com.transactional.spring.entities;

public class Course {
    int courseId;
    String courseTitle;
    String courseDescription;
    int coursePrice;
    int categoryId;

    public Course(int courseId, String courseTitle, String courseDescription, int coursePrice, int categoryId) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.coursePrice = coursePrice;
        this.categoryId = categoryId;
    }

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public int getCoursePrice() {
        return coursePrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public void setCoursePrice(int coursePrice) {
        this.coursePrice = coursePrice;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
