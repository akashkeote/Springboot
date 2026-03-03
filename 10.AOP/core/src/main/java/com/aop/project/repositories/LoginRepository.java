package com.aop.project.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {
    public void getUser() {
        System.out.println("getting user");
        // database code
    }
}
