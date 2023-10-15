package dev.fullstacknam.restcruddemo.dao;

import java.util.List;

import dev.fullstacknam.restcruddemo.entity.Course;
import dev.fullstacknam.restcruddemo.entity.Instructor;
import dev.fullstacknam.restcruddemo.entity.InstructorDetail;
import dev.fullstacknam.restcruddemo.entity.Student;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void updateInstructor(Instructor instructor);

    void updateCourse(Course course);

    Course findCourseById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deletedInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int instructorId);

    void deleteCourseById(int id);

    void createCourse(Course course);

    Course getCourseWithReviews(int id);

    Course findCourseWithStudentsByCourseId(int courseId);

    Student findStudentWithCoursesByStudentId(int studentId);

    void updateStudent(Student student);

    void deleteStudentById(int id);
}
