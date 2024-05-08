package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
        // break bi-directional link

        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }
}
