package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner->{
			// createInstructor(appDAO);
			// findInstructor(appDAO);
			// deleteInstructor(appDAO);
			// findInstructorDetail(appDAO);
			// deleteInstructorDetail(appDAO);
			// createInstructorWithCourses(appDAO);
			// findInstructorWithCourses(appDAO);
			// findCoursesForInstructor(appDAO);
			// findInstructorWithCoursesJoinFetch(appDAO);
			// updateInstructor(appDAO);
			// updateCourse(appDAO);
			// deleteInstructor(appDAO);
			deleteCourse(appDAO);
		};
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId=10;

		System.out.println("Deleting course id: "+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done !");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId=10;

		// find Course
		Course tempCourse=appDAO.findCourseById(theId);

		System.out.println("updating Course...");
		tempCourse.setTitle("Enjoy the Simple Things");
		appDAO.updateCourse(tempCourse);
		System.out.println("updated Course : "+tempCourse);
		System.out.println("Done !");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId=1;

		// find Instructor
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		tempInstructor.setLastName("TESTER");

		System.out.println("updating Instructor...");
		appDAO.update(tempInstructor);
		System.out.println("updated Instructor : "+tempInstructor);
		System.out.println("Done !");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId=1;
		// find the Instructor
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("The Instructor : "+tempInstructor);
		System.out.println("the associated courses: "+tempInstructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("The Instructor : "+tempInstructor);

		System.out.println("Finding courses for instructor id: "+theId);
		List<Course> courseList=appDAO.findCoursesByInstructorId(theId);
		tempInstructor.setCourses(courseList);
		System.out.println("The courses: "+tempInstructor.getCourses()+" for instructor: "+ tempInstructor);
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("The Instructor : "+tempInstructor);
		System.out.println("the associated instructor courses only: " + tempInstructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor=new Instructor("Susan","Public","susan.public@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.luv2code.com/youtube","Video Games");

/*
		// create the instructor
		Instructor tempInstructor=new Instructor("Madhu","Patel","madhu@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.luv2code.com/youtube","Guitar");

 */

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse1= new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2= new Course("The Pinball Masterclass");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		//
		// NOTE: this will also save the courses
		// because of CascadeType.PERSIST
		//
		System.out.println("Saving instructor..."+tempInstructor);
		System.out.println("The courses: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId=2;
		System.out.println("Deleting instructor id: "+theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId=2;
		System.out.println("Finding instructor Detail id: "+theId);
		InstructorDetail tempInstructorDetail=appDAO.findInstructorDetailById(theId);
		System.out.println("The InstructorDetail : "+tempInstructorDetail);
		System.out.println("the associated instructor only: " + tempInstructorDetail.getInstructor());
	}

	private void findInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("The Instructor : "+tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Deleting instructor id: "+theId);
		appDAO.deleteById(theId);
		System.out.println("Done!");

	}

	private void createInstructor(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor=new Instructor("Chad","Darby","darby@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.luv2code.com/youtube","Luv 2 code!!!");

/*
		// create the instructor
		Instructor tempInstructor=new Instructor("Madhu","Patel","madhu@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.luv2code.com/youtube","Guitar");

 */

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		// NOTE: this will also save the details object
		// because of CascadeType.ALL
		//
		System.out.println("Saving instructor..."+tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}

}
