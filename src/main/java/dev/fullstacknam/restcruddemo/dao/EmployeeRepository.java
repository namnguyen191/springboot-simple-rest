package dev.fullstacknam.restcruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fullstacknam.restcruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
