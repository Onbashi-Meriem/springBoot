package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Review;
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
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            // createCourseAndStudents(appDAO);
            // findCourseAndStudents(appDAO);
            // findStudentAndCourses(appDAO);
            // addMoreCoursesForStudent(appDAO);
            // deleteCourse(appDAO);
            deleteStudent(appDAO);

        };
    }

    private void deleteStudent(AppDAO appDAO) {
        int theId=1;
        System.out.println("Deleting student id: "+theId);
        appDAO.deleteStudentById(theId);
        System.out.println("Done!");
    }

    private void deleteCourse(AppDAO appDAO) {
        int theId=10;
        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
        int theId=2;
        Student tempStudent=appDAO.findStudentAndCoursesByStudentId(theId);

        // create more courses
        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        // add courses to student
        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        System.out.println("Updating student : " + tempStudent);
        System.out.println("associated courses : " +tempStudent.getCourses());

        appDAO.update(tempStudent);

        System.out.println("Done!");

    }

    private void findStudentAndCourses(AppDAO appDAO) {
        int studentId = 1;
        Student theStudent = appDAO.findStudentAndCoursesByStudentId(studentId);

        System.out.println("Loaded student : " + theStudent);
        System.out.println("Courses : " +theStudent.getCourses());

        System.out.println("Done!");
    }

    private void findCourseAndStudents(AppDAO appDAO) {
        int courseId = 10;
        Course course = appDAO.findCourseAndStudentsByCourseId(courseId);

        System.out.println("Loaded course : " + course);
        System.out.println("Students : " + course.getStudents());

		System.out.println("Done!");
    }

    private void createCourseAndStudents(AppDAO appDAO) {

        // create a course
        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        // create the students
        Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
        // add students to the course
        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        // save the course and associated students
        System.out.println("Saving the Course: " + tempCourse);
        System.out.println("associated students: " + tempCourse.getStudents());

        appDAO.save(tempCourse);

        System.out.println("Done!");
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {

        int theId = 10;

        System.out.println("Deleting course with id: " + theId);
        appDAO.deleteCourseById(theId);

        System.out.println("Done ! ");
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        // get the course and reviews
        int theId = 10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        // print the course
        System.out.println("The course with id: " + theId + " is " + tempCourse);
        // print the reviews
        System.out.println("The reviews for the course id: " + theId + " is " + tempCourse.getReviews());
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        // create a course
        Course tempCourse = new Course("Pacman - How to Score One Million Points");

        // add some reviews
        Review review1 = new Review("Great course ... loved it!");
        Review review2 = new Review("Cool course, job well done!");
        Review review3 = new Review("What a dumb course, you are an idiot!");
        tempCourse.add(review1);
        tempCourse.add(review2);
        tempCourse.add(review3);

        // save the course
        System.out.println("Saving the course" + tempCourse);
        appDAO.save(tempCourse);
        System.out.println("Done !");
    }


}
