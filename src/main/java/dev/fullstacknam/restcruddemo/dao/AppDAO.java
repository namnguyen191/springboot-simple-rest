package dev.fullstacknam.restcruddemo.dao;

import dev.fullstacknam.restcruddemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);
}
