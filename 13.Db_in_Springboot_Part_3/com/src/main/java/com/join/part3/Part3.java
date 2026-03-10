package com.join.part3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.join.part3.Dao.CategoryDao;
import com.join.part3.Dao.CourseCategoryDao;
import com.join.part3.Dao.CourseDao;
import com.join.part3.entities.Course;
import com.join.part3.entities.CourseCategoryData;

@SpringBootApplication
public class Part3 implements CommandLineRunner {
	@Autowired
	CategoryDao cDao;
	@Autowired
    CourseDao cd;
	@Autowired
	CourseCategoryDao ccdao;
	public static void main(String[] args) {
		SpringApplication.run(Part3.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// // TODO Auto-generated method stub
		// Course c = new Course();
		// c.setCourseId(8);
		// c.setCourseTitle("akash ke pravachan");
		// c.setCoursePrice(100000);
		// c.setCourseDescription("null zindagi");
		// c.setCategoryId(8);
		// Course s=cd.insert(c);
		// System.out.println(s.getCategoryId()+" "+s.getCourseId());
List<CourseCategoryData> ccd=ccdao.ccd();
ccd.forEach(ak->System.out.println(ak.getCategoryTitle()+" "+ak.getCategoryDes()+" "+ak.getCourseTitle()+" "+ak.getCoursePrice()+" "+ak.getCourseDesc()));

	}

}
