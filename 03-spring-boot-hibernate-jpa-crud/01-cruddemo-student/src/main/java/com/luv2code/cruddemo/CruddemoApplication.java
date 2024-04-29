package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			// createStudent(studentDAO);
			 createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
			// queryForStudent(studentDAO);
			// queryForStudentByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			// deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numRowsDeleted=studentDAO.deleteAll();
		System.out.println("Deleted row count : "+numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId=3;
		System.out.println("Deleting student id: "+studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on the id: primary key
		int studentId=4;
		System.out.println("Getting student with id: "+studentId);
		Student theStudent=studentDAO.findById(studentId);
		// change first name to 'John'
		System.out.println("Updating student ...");
		// theStudent.setFirstName("John");
		theStudent.setEmail("caner@test.com");

		// update the student
		studentDAO.update(theStudent);
		// display the updated student
		System.out.println("updated Student: "+ theStudent);
	}

	private void queryForStudentByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents=studentDAO.findByLastName("Doe");
		//display list of students
		for (Student theTempStudent:theStudents){
			System.out.println(theTempStudent);
		}
	}

	private void queryForStudent(StudentDAO studentDAO) {
		//get a list of students
		List<Student> theStudents=studentDAO.findAll();
		//displaylist of students
		for (Student tempStudent:theStudents){
			System.out.println(tempStudent);
		}

	}

	private void readStudent(StudentDAO studentDAO) {
		//create the student object
		System.out.println("Creating new student object ...");
		Student newStudent=new Student("Meryem","Onbashi-Ugurlu","meryem@test.com");

		//save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(newStudent);

		//display id of the saved student
		int theId=newStudent.getId();
		System.out.println("Saved student. Generated id: "+theId);

		//retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: "+theId);
		Student myStudent=studentDAO.findById(theId);

		//display student
		System.out.println("Found the student: "+myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		//create multiple students
		System.out.println("Creating 3 students object ...");
		Student newStudent=new Student("John","Doe","john@test.com");
		Student newStudent1=new Student("Mary","Public","mary@test.com");
		Student newStudent2=new Student("Bonita","Applebum","bonita@test.com");

		//save the student objects
		System.out.println("Saving the students ...");
		studentDAO.save(newStudent);
		studentDAO.save(newStudent1);
		studentDAO.save(newStudent2);
	}

	private void createStudent(StudentDAO studentDAO) {
		//create the student object
		System.out.println("Creating new student object ...");
		Student newStudent=new Student("Meryem","Onbashi-Ugurlu","test@test.com");

		//save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(newStudent);

		//display id of the saved student
		System.out.println("Saved student. Generated id: "+newStudent.getId());
	}
}
