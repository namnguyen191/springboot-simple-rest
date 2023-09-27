package dev.fullstacknam.restcruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.fullstacknam.restcruddemo.dao.AppDAO;
import dev.fullstacknam.restcruddemo.entity.Instructor;
import dev.fullstacknam.restcruddemo.entity.InstructorDetail;

@SpringBootApplication
public class RestcruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestcruddemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			createInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {
		// Instructor instructor = new Instructor("John", "Smith");
		// InstructorDetail instructorDetail = new InstructorDetail("123 New York", "Go
		// fishing");
		// instructor.setInstructorDetail(instructorDetail);
		// appDAO.save(instructor);

		// var foundInstructor = appDAO.findInstructorById(1);
		// System.out.println(foundInstructor);

		// appDAO.deleteInstructorById(1);

		// var deletedInstructor = appDAO.findInstructorById(1);
		// System.out.println(deletedInstructor);
	}
}
