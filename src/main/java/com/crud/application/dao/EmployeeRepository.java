package com.crud.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.application.entity.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

}
