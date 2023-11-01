package dev.fullstacknam.restcruddemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.fullstacknam.restcruddemo.dao.AppDAO;
import dev.fullstacknam.restcruddemo.entity.Course;
import dev.fullstacknam.restcruddemo.entity.Instructor;
import dev.fullstacknam.restcruddemo.entity.InstructorDetail;
import dev.fullstacknam.restcruddemo.entity.Review;
import dev.fullstacknam.restcruddemo.entity.Student;

@SpringBootApplication
public class RestcruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestcruddemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			appDAO.setVersion(2);
			appDAO.getVersion();
			createInstructorWithDetail(appDAO);
			findInstructorById(appDAO, 2);
		};
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor("John", "Smith");
		InstructorDetail instructorDetail = new InstructorDetail("123 New York", "Go fishing");
		instructor.setInstructorDetail(instructorDetail);

		var course1 = new Course("ENG 1000");
		var course2 = new Course("SOC 3000");
		instructor.addCourse(course1);
		instructor.addCourse(course2);
		appDAO.save(instructor);
	}

	private void createInstructorWithDetail(AppDAO appDAO) {
		Instructor instructor = new Instructor("John", "Smith");
		InstructorDetail instructorDetail = new InstructorDetail("123 New York", "Go fishing");
		instructor.setInstructorDetail(instructorDetail);
		appDAO.save(instructor);
	}

	private void findInstructorById(AppDAO appDAO, int id) {
		var foundInstructor = appDAO.findInstructorById(id);
		System.out.println(foundInstructor);
	}

	private void deletedInstructorById(AppDAO appDAO, int id) {
		appDAO.deleteInstructorById(id);

		var deletedInstructor = appDAO.findInstructorById(id);
		System.out.println(deletedInstructor);
	}

	private void findInstructorDetailById(AppDAO appDAO, int id) {
		var instructorDetail = appDAO.findInstructorDetailById(id);

		System.out.println("Instructor detail: " + instructorDetail);
		System.out.println("Instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructorDetailById(AppDAO appDAO, int id) {
		appDAO.deletedInstructorDetailById(id);
		var deletedInstructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println(deletedInstructorDetail);
	}

	// Only work for eager loading
	// private void findInstructorWithCourse(AppDAO appDAO, int id) {
	// var instructor = appDAO.findInstructorById(id);
	// System.out.println("Instructor: " + instructor);
	// System.out.println("Courses:" + instructor.getCourses());
	// }

	private void findInstructorWithCourse(AppDAO appDAO, int id) {
		var instructor = appDAO.findInstructorById(id);
		System.out.println("Instructor: " + instructor);
		var courses = appDAO.findCoursesByInstructorId(id);
		instructor.setCourses(courses);
		System.out.println("Courses: " + instructor.getCourses());
	}

	private void findInstructorWithCourseJoinFetch(AppDAO appDAO, int id) {
		var instructor = appDAO.findInstructorByIdJoinFetch(id);
		System.out.println("Instructor: " + instructor);
		System.out.println("Courses: " + instructor.getCourses());
	}

	private void updateAnInstructor(AppDAO appDAO) {
		var instructor = appDAO.findInstructorById(3);

		instructor.setAge("99");
		appDAO.updateInstructor(instructor);
	}

	private void updateACourse(AppDAO appDAO) {
		var course = appDAO.findCourseById(1);
		course.setTitle("PHYS 2100");
		appDAO.updateCourse(course);
	}

	private void deleteCourse(AppDAO appDAO, int id) {
		appDAO.deleteCourseById(id);

		System.out.println("Course: " + appDAO.findCourseById(id));
	}

	private void createCourseAndReview(AppDAO appDAO) {
		var course = new Course("BIO 1000 - Introduction to biology");

		course.addReview(new Review("Greate course"));
		course.addReview(new Review("Horrible instructor"));
		course.addReview(new Review("Not bad"));

		appDAO.createCourse(course);
	}

	private void getCourseWithReviews(AppDAO appDAO, int id) {
		System.out.println("Getting course with id: " + id);
		var course = appDAO.getCourseWithReviews(id);
		System.out.println("Course info: " + course);
		for (var reviews : course.getReviews()) {
			System.out.println("Reviews: " + reviews);
		}
	}

	private void createCourseAndStudent(AppDAO appDAO) {
		var course = new Course("ENG 1000");
		course.addStudent(new Student("John", "Doe", "john.com@mail.com"));
		course.addStudent(new Student("Marry", "Jane", "jane.mary@hotmail.com"));

		appDAO.createCourse(course);
	}

	private void findCourseWithStudents(AppDAO appDAO, int courseId) {
		System.out.println("Finding course with Id: " + courseId);
		var course = appDAO.findCourseWithStudentsByCourseId(courseId);
		System.out.println("Course info: " + course);
		System.out.println("Students in this course:");
		for (var student : course.getStudents()) {
			System.err.println(student);
		}
	}

	private void findStudentWithCourses(AppDAO appDAO, int studentId) {
		System.out.println("Finding student with Id: " + studentId);
		var student = appDAO.findStudentWithCoursesByStudentId(studentId);
		System.out.println("Stundent info: " + student);
		System.out.println("Courses that this student's enrolled:");
		for (var course : student.getCourses()) {
			System.err.println(course);
		}
	}

	private void addMoreCourseToStudent(AppDAO appDAO, int studentId, List<Course> courses) {
		var student = appDAO.findStudentWithCoursesByStudentId(studentId);
		for (Course course : courses) {
			student.addCourse(course);
		}
		appDAO.updateStudent(student);
	}
}
