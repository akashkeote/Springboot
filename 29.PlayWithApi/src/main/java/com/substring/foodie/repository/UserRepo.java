package com.substring.foodie.repository;

import com.substring.foodie.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    // custom query methods
    List<User> findByName(String name);

    Optional<User> findByEmail(String email);

    @Query("select u from User u where lower(u.name) like lower(concat('%', :keyword, '%')) or lower(u.email) like lower(concat('%', :keyword, '%'))")
    List<User> searchByKeyword(String keyword);
}
