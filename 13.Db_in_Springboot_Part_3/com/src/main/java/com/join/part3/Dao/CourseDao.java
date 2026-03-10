package com.join.part3.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.join.part3.entities.Course;



@Component
@Repository
public class CourseDao {
       @Autowired
       JdbcTemplate jt;

       public Course insert(Course c) {
              String query = "insert into course (courseId, courseTitle, courseDescription,coursePrice,categoryId) values(?,?,?,?,?)";
              int row = jt.update(query,
                            c.getCourseId(),
                            c.getCourseTitle(),
                            c.getCourseDescription(),
                            c.getCoursePrice(),
                            c.getCategoryId());

              System.out.println("Course inserted successfully " + row);
              return c;
       }

       public Course get(int id) {
              String q = "select * from course where courseId = ?";
              return jt.queryForObject(q, (rs, rowNum) -> {
                     Course c = new Course();
                     c.setCourseId(rs.getInt("courseId"));
                     c.setCourseTitle(rs.getString("courseTitle"));
                     c.setCourseDescription(rs.getString("courseDescription"));
                     c.setCoursePrice(rs.getInt("coursePrice"));
                     c.setCategoryId(rs.getInt("categoryId"));
                     return c;
              }, id);
       }

       public List<Course> getAllCou() {
              String q = "select * from course";
              List<Course> list = jt.query(q, (rs, rowNum) -> {
                     Course c = new Course();
                     c.setCourseId(rs.getInt("courseId"));
                     c.setCourseTitle(rs.getString("courseTitle"));
                     c.setCourseDescription(rs.getString("courseDescription"));
                     c.setCoursePrice(rs.getInt("coursePrice"));
                     c.setCategoryId(rs.getInt("categoryId"));
                     return c;
              });
              return list;

       }

       public Course update(Course c) {
              String q = "update course set courseTitle=?,courseDescription=?,coursePrice=?,categoryId=? where courseId=?";
              int row = jt.update(q, c.getCourseTitle(), c.getCourseDescription(), c.getCoursePrice(),
                            c.getCategoryId(), c.getCourseId());
              System.out.println("Data updated successfully " + row);
              return c;
       }

       public Course delete(Course c, int id) {
              String q = "delete from course where courseId = ?";
              jt.update(q, id);
              System.out.println("Data deleted successfully " + c.getCourseId() + " " + c.getCourseTitle() + " "
                            + c.getCourseDescription() + " " + c.getCoursePrice() + " " + c.getCategoryId());
              return c;
       }

       public List<Course> getCourseByCategoryId(int catid) {
              String q = "select * from course where categoryId=?";
              List<Course> list = jt.query(q, (rs, rowNum) -> {
                     Course c = new Course();
                     c.setCourseId(rs.getInt("courseId"));
                     c.setCourseTitle(rs.getString("courseTitle"));
                     c.setCourseDescription(rs.getString("courseDescription"));
                     c.setCoursePrice(rs.getInt("coursePrice"));
                     c.setCategoryId(rs.getInt("categoryId"));
                     return c;
              }, catid);
              return list;
       }

}
