package dev.fullstacknam.restcruddemo.dao;

import java.util.List;

import dev.fullstacknam.restcruddemo.entity.Employee;

public interface EmployeeDAO {
  List<Employee> findAll();
}
