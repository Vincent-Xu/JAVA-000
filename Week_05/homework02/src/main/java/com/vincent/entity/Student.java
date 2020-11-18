package com.vincent.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *@Author: Vincent Xu
 *@Description:
 *@CreatedTime:21:49 2020/11/18
 */
@Data
public class Student {
    private Integer age;
    private String Name;

    public void read() {
        System.out.println("student reading...");
    }
}
