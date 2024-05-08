package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    // define field for entity manager
    private EntityManager entityManager;
    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theInstructorId) {
        Instructor theInstructor=entityManager.find(Instructor.class,theInstructorId);
        return theInstructor;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        // retrieve the Instructor
        Instructor theInstructor=findInstructorById(id);

        // get the courses
        List<Course> courses=theInstructor.getCourses();

        // break the association of all courses for the instructor
        for(Course course:courses){
           course.setInstructor(null);
        }

        // delete the Instructor
        entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        InstructorDetail tempInstructorDetail=entityManager.find(InstructorDetail.class,theId);
        return tempInstructorDetail;
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // retrieve the Instructor detail
        InstructorDetail tempInstructorDetail=findInstructorDetailById(theId);

        // remove the associated object reference
        // break bidirectional link

        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // create query
        TypedQuery<Course> theQuery=entityManager.createQuery("from Course where instructor.id=:data", Course.class);
        theQuery.setParameter("data",theId);
        return theQuery.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // create query
        TypedQuery<Instructor> theQuery=entityManager.createQuery("select i from Instructor i "
                                                                        +"JOIN FETCH i.courses "
                                                                        +"JOIN FETCH i.instructorDetail "
                                                                        +"where i.id = :data", Instructor.class);
        // set parameter
        theQuery.setParameter("data",theId);
        return theQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    public Course findCourseById(int theId) {
        Course tempCourse=entityManager.find(Course.class,theId);
        return tempCourse;
    }

    @Override
    @Transactional
    public void updateCourse(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse=entityManager.find(Course.class,theId);
        entityManager.remove(tempCourse);
    }

}
