package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    //define field for the entityManager

    //set up constructor injection
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery=entityManager.createQuery("FROM Employee",Employee.class);

        // execute the query and get result list
        List<Employee> results=theQuery.getResultList();
        // return result list
        return results;
    }

    @Override
    public Employee findById(int id) {
        // TypedQuery<Employee> theQuery=entityManager.createQuery("FROM Employee WHERE id=:id",Employee.class);
        // theQuery.setParameter("id",id);
        // return theQuery.getSingleResult();

        // simple method
        Employee employee=entityManager.find(Employee.class,id);
        return employee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //save employee
        Employee dbEmployee=entityManager.merge(theEmployee);

        //return the dbEmployee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        // find employee by id
        Employee theEmployee=entityManager.find(Employee.class,theId);
        //remove employee
        entityManager.remove(theEmployee);
    }
}
