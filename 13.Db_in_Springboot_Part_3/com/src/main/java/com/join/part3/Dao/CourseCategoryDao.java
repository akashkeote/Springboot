package com.join.part3.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.join.part3.entities.CourseCategoryData;

@Component
@Repository
public class CourseCategoryDao {
    @Autowired
    private JdbcTemplate jt;

    public List<CourseCategoryData> ccd() {
        String q = "SELECT\r\n" + //
                "    category.title AS categoryTitle,\r\n" + //
                "    category.description AS categoryDesc,\r\n" + //
                "    course.courseTitle AS courseTitle,\r\n" + //
                "    course.coursePrice AS coursePrice,\r\n" + //
                "    course.courseDescription AS courseDesc\r\n" + //
                "FROM category\r\n" + //
                "INNER JOIN course\r\n" + //
                "ON category.id = course.categoryId";

        List<CourseCategoryData> courseCategories = jt.query(q, (rs, rowNum) -> {
            CourseCategoryData ccdata = new CourseCategoryData();
            ccdata.setCategoryTitle(rs.getString("categoryTitle"));
            ccdata.setCategoryDes(rs.getString("categoryDesc"));
            ccdata.setCourseTitle(rs.getString("courseTitle"));
            ccdata.setCoursePrice(rs.getString("coursePrice"));
            ccdata.setCourseDesc(rs.getString("courseDesc"));
            return ccdata;
        });
        return courseCategories;
    }
}
