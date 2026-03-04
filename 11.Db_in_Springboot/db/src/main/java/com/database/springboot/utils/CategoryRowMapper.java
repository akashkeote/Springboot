package com.database.springboot.utils;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.database.springboot.entities.Category;

public class CategoryRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
      Category c = new Category();
      c.setId(rs.getInt("id"));
      c.setTitle(rs.getString("title"));
      c.setDescription(rs.getString("description"));
      return c;
    }
    
}
