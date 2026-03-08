package com.substring.foodie.bunch_of_object;

import java.util.ArrayList;
import java.util.List;

public class DummyDataGenerator {
    
        public List<Student> generateStudentList() {
            List<Student> students = new ArrayList<>();
            
            Department d = new Department();
            d.setDeptName("Computer Science");
            d.setDeptCode("CS001");

            Subject s1 = new Subject();
            s1.setSubjectName("Data Structures");
            s1.setSubjectCode("CS201");
            Subject s2 = new Subject();
            s2.setSubjectName("Web Development");
            s2.setSubjectCode("CS202");
            Subject s3 = new Subject();
            s3.setSubjectName("Database Management");
            s3.setSubjectCode("CS203");

            List<Subject> sub = new ArrayList<>();
            sub.add(s1);
            sub.add(s2);
            sub.add(s3);

            String[] names = {"Aarjun Kumar", "Priya Sharma", "Rohan Patel", "Divya Singh", 
                              "Aditya Gupta", "Neha Verma", "Vasant Reddy", "Sanya Desai", 
                              "Arjun Nair", "Anjali Bhat"};
            
            for (int i = 0; i < names.length; i++) {
                Student student = new Student();
                student.setName(names[i]);
                student.setAge(19 + i);
                student.setDepartment(d);
                student.setSubjects(sub);
                students.add(student);
            }

            return students;
        }
}
