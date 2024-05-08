package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Review;
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
			// createCourseAndReviews(appDAO);
			// retrieveCourseAndReviews(appDAO);
			deleteCourseAndReviews(appDAO);

		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int theId=10;

		System.out.println("Deleting course with id: "+theId);
		appDAO.deleteCourseById(theId);

		System.out.println("Done ! ");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		// get the course and reviews
		int theId=10;
		Course tempCourse=appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println("The course with id: "+theId+" is "+tempCourse);
		// print the reviews
		System.out.println("The reviews for the course id: "+theId+" is "+tempCourse.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course tempCourse=new Course("Pacman - How to Score One Million Points");

		// add some reviews
		Review review1=new Review("Great course ... loved it!");
		Review review2=new Review("Cool course, job well done!");
		Review review3=new Review("What a dumb course, you are an idiot!");
		tempCourse.add(review1);
		tempCourse.add(review2);
		tempCourse.add(review3);

		// save the course
		System.out.println("Saving the course"+tempCourse);
		appDAO.save(tempCourse);
		System.out.println("Done !");
	}



}
