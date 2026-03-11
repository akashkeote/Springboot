package com.substring.foodie.service;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.*;

import javax.swing.ListModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.substring.foodie.dto_with_respect_to_entity.UserDto;
import com.substring.foodie.entity.User;

public interface UserService {
    //pagable and sorting bhi add karenge aage chalke
   Page<UserDto> getAllUsers(Pageable page);

    UserDto saveUser(UserDto userdto);

    public UserDto updateUser(User user, String userId);

    List<UserDto> getAllUsers();

    List<UserDto> getUserByName(String name);

    UserDto getUserByEmail(String email);

    UserDto getUserById(String userId);

    void deleteUserById(String userId);

    List<UserDto> searchUsers(String keyword);
    // public void testUserRole();
}
