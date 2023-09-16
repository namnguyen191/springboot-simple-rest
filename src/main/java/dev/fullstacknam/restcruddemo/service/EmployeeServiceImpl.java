// package dev.fullstacknam.restcruddemo.service;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import dev.fullstacknam.restcruddemo.dao.EmployeeDAO;
// import dev.fullstacknam.restcruddemo.dao.EmployeeRepository;
// import dev.fullstacknam.restcruddemo.entity.Employee;
// import jakarta.transaction.Transactional;

// @Service
// public class EmployeeServiceImpl implements EmployeeService {
// EmployeeRepository employeeRepository;

// public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
// this.employeeRepository = employeeRepository;
// }

// @Override
// public List<Employee> findAll() {
// return this.employeeRepository.findAll();
// }

// @Override
// public Employee findById(int id) {
// var result = this.employeeRepository.findById(id);

// Employee employee = null;

// if (result.isPresent()) {
// employee = result.get();
// } else {
// throw new RuntimeException("Cannot find employee id of " + id);
// }

// return employee;
// }

// @Override
// public Employee save(Employee employee) {
// return this.employeeRepository.save(employee);
// }

// @Override
// public void deleteById(int id) {
// this.employeeRepository.deleteById(id);
// }

// }
