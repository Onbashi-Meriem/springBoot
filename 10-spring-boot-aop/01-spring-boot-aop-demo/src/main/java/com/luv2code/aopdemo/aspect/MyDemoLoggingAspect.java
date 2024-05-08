package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {


    //this is where we add all of our related advices for logging

    // let's start with an @Before advice
    // to match only addAccount in AccountDAO
    // @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")

    // to match all methods which with add starts
    // @Before("execution(public void add*())")

    // to match all returntypes/Boolean  with add starts
    // @Before("execution(Boolean add*())")
    //@Before("execution(* add*())")

    // with specific fully qualified parameter types
   //  @Before("execution(* add*(com.luv2code.aopdemo.Account))")

    // get error
    // @Before("execution(* add*(Account))")

    // with specific fully qualified parameter and more parameter
     // @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")

    // with any type of parameter or no parameter
      @Before("execution(* add*(..))")
    public void beforeAddAccountAdvice(){

        System.out.println("\n =====>>> Executing @Before advice on addAccount()");

    }

}
