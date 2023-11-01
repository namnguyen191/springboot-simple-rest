package dev.fullstacknam.restcruddemo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.fullstacknam.restcruddemo.entity.Course;
import dev.fullstacknam.restcruddemo.entity.Instructor;
import dev.fullstacknam.restcruddemo.entity.InstructorDetail;
import dev.fullstacknam.restcruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {
    private EntityManager entityManager;
    private int version = 1;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

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
        try {
            var instructor = entityManager.find(Instructor.class, id);
            if (instructor == null) {
                throw new RuntimeException("Cannot find instructor");
            }
            return instructor;
        } catch (Exception e) {
            throw new RuntimeException("Cannot find instructor");
        }
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        var instructor = entityManager.find(Instructor.class, id);

        for (var course : instructor.getCourses()) {
            course.setInstructor(null);
        }

        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deletedInstructorDetailById(int id) {
        var instructorDetail = entityManager.find(InstructorDetail.class, id);
        // Break bi-direction link
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {
        var query = entityManager.createQuery("from Course where instructor.id = :id", Course.class);
        query.setParameter("id", instructorId);

        var courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        var query = entityManager.createQuery("select i from Instructor i JOIN FETCH i.courses WHERE i.id = :id",
                Instructor.class);

        query.setParameter("id", id);
        var instructorWithCourses = query.getSingleResult();

        return instructorWithCourses;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        var course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void createCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course getCourseWithReviews(int id) {
        var query = entityManager.createQuery("SELECT c from Course c JOIN FETCH c.reviews WHERE c.id = :id",
                Course.class);
        query.setParameter("id", id);

        var course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseWithStudentsByCourseId(int courseId) {
        var query = entityManager.createQuery("SELECT c FROM Course c JOIN FETCH c.students WHERE c.id = :id",
                Course.class);
        query.setParameter("id", courseId);
        var course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentWithCoursesByStudentId(int studentId) {
        var query = entityManager.createQuery("SELECT s FROM Student s JOIN FETCH s.courses WHERE s.id = :id",
                Student.class);
        query.setParameter("id", studentId);
        var student = query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        var student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }
}
