package com.substring.foodie.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.substring.foodie.bunch_of_object.Department;
import com.substring.foodie.bunch_of_object.DummyDataGenerator;
import com.substring.foodie.bunch_of_object.Student;
import com.substring.foodie.bunch_of_object.Subject;

@RestController
@RequestMapping("/student")
//http://localhost:8080/student/stu_details
public class StudentController {
    @RequestMapping("/stu_details")
    public Student getStuDet() {
       Department d = new Department();
         d.setDeptName("Computer Science");
            d.setDeptCode("CS101");

            Subject s1 = new Subject();
            s1.setSubjectName("Data Structures");
            s1.setSubjectCode("CS201");
            Subject s2 = new Subject();
            s2.setSubjectName("Algorithms");
            s2.setSubjectCode("CS202");

            List<Subject> sub = new ArrayList<>();
             sub.add(s1);
            sub.add(s2);

            Student s = new Student();
            s.setName("Alice");
            s.setAge(20);
            s.setDepartment(d);
            s.setSubjects(sub);

            return s;
    }
    @GetMapping("/all")
public List<Student> getAllStudents() {

List<Student >dg = new DummyDataGenerator().generateStudentList();
  return dg;
}

//now lets talk about {messag} URI path vairable

    
}
