package com.jpa.more.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.more.entity.Department;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}