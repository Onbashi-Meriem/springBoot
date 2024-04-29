package com.example.springcoredemo.config;

import com.example.springcoredemo.commen.Coach;
import com.example.springcoredemo.commen.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
