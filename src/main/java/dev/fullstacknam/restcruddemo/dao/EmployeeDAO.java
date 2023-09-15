package dev.fullstacknam.restcruddemo.dao;

import java.util.List;

import dev.fullstacknam.restcruddemo.entity.Employee;

public interface EmployeeDAO {
  List<Employee> findAll();

  Employee findById(int id);

  Employee save(Employee employee);

  void deleteById(int id);
}
