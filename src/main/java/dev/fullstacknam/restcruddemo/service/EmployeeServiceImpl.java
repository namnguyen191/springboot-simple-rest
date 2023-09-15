package dev.fullstacknam.restcruddemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.fullstacknam.restcruddemo.dao.EmployeeDAO;
import dev.fullstacknam.restcruddemo.entity.Employee;
import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return this.employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return this.employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        this.employeeDAO.deleteById(id);
    }

}
