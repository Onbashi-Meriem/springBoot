package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
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
}
