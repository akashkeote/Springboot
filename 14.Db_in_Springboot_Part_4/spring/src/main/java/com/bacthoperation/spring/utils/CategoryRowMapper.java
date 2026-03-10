package com.bacthoperation.spring.utils;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bacthoperation.spring.entities.Category;



@Component
public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
      Category c = new Category();
      c.setId(rs.getInt("id"));
      c.setTitle(rs.getString("title"));
      c.setDescription(rs.getString("description"));
      return c;
    }
    
}
