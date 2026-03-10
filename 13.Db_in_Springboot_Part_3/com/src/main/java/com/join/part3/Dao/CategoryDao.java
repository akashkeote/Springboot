package com.join.part3.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.join.part3.entities.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import jakarta.annotation.PostConstruct;

@Component
@Repository
public class CategoryDao {
    @Autowired
    JdbcTemplate jt;

    // creatae db

    public void initDb() {
        String q = "create database if not exists springbootdb";
        jt.execute(q);
        System.out.println("database created successfully");
    }

    // create table
    @PostConstruct
    public void init() {
        String q = "create table if not exists category(id int primary key,title varchar(100),description varchar(100))";
        jt.update(q);
        System.out.println("table akash created");
    }

    // insert data into the table
    public Category set(Category c) {
        String q = "insert into category (id,title,description) values(?,?,?)";
        int row = jt.update(q, c.getId(), c.getTitle(), c.getDescription());
        System.out.println("Data inserted into category table");
        return c;
    }

    // delete data from table
    public Category delete(Category c, int id) {
        String q = "delete from category where id = ?";
        jt.update(q, id);
        System.out.println("Data deleted successfully " + c.getId() + " " + c.getTitle() + " " + c.getDescription());
        return c;
    }

    // get id select *
    public Category get(int id) {
        String q = "select * from category where id = ?";
        return jt.queryForObject(q, Category.class, id);

    }

    // get all
    public List<Category> getAll() {
        String q = "select * from category";
        return jt.query(q, new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setTitle(rs.getString("title"));
                c.setDescription(rs.getString("description"));
                return c;
            }
        });
    }

    // update
    public Category update(int id, Category nw) {
        String q = "update category set title=? ,description=? where id=?";

        int update = jt.update(q, nw.getTitle(), nw.getDescription(), id);
        System.out.println("updated =>" + update);
        nw.setId(id);
        return nw;

    }

}
