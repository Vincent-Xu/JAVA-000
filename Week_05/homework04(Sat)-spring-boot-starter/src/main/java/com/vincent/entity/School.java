package com.vincent.entity;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "student")
public class School{

    Student student100;

    public void ding(){
        System.out.println("Students and one is " + this.student100);
    }
    
}
