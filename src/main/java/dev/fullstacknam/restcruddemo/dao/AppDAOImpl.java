package dev.fullstacknam.restcruddemo.dao;

import org.springframework.stereotype.Repository;

import dev.fullstacknam.restcruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {
    private EntityManager entityManager;

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        var instructor = entityManager.find(Instructor.class, id);
        entityManager.remove(instructor);
    }
}
