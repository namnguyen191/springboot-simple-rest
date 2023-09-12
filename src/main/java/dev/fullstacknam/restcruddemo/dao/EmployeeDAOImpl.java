package dev.fullstacknam.restcruddemo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.fullstacknam.restcruddemo.entity.Employee;
import jakarta.persistence.EntityManager;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
  private EntityManager entityManager;

  EmployeeDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Employee> findAll() {
    var query = entityManager.createQuery("from Employee", Employee.class);

    var employees = query.getResultList();

    return employees;
  }

}
