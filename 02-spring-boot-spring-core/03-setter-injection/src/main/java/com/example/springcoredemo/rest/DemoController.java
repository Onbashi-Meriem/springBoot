package com.example.springcoredemo.rest;

import com.example.springcoredemo.commen.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //define a private field for the dependency
    private Coach myCoach;

    //define setter method for the dependency injection. Method herhangi bir isim alabilir
    @Autowired
    // public void setCoach(Coach theCoach) {
    public void doSomeStuff(Coach theCoach) {
        this.myCoach = theCoach;
    }
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return this.myCoach.getDailyWorkout();
    }
}
