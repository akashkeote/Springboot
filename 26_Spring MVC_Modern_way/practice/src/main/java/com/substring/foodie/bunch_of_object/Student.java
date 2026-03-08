package com.substring.foodie.bunch_of_object;

import java.util.List;

public class Student {
    private String name;
    private int age;
    private Department department;
   private List<Subject> subjects;
   public Student() {
}
   public String getName() {
    return name;
   }
   public void setName(String name) {
    this.name = name;
   }
   public int getAge() {
    return age;
   }
   public void setAge(int age) {
    this.age = age;
   }
   public Department getDepartment() {
    return department;
   }
   public void setDepartment(Department department) {
    this.department = department;
   }
   public List<Subject> getSubjects() {
    return subjects;
   }
   public void setSubjects(List<Subject> subjects) {
    this.subjects = subjects;
   }
   
}
