package com.vincent;


import com.vincent.entity.Student;
import com.vincent.service.StudentService;
import com.vincent.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 *@Author: Vincent Xu
 *@Description spring装配demo
 *@CreatedTime:18:23 2020/11/18
 */
public class SpringDemoAOP {
    @Autowired
    private StudentService studentService3;

    @Resource(type = StudentServiceImpl.class)
    private StudentService studentService4;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        第1种
        Student student1 = (Student)context.getBean("student1");

//        第2种
        StudentService studentService2 = context.getBean(StudentService.class);
        studentService2.createStudent(student1);

//        第3种
        SpringDemoAOP springDemoAOP = context.getBean(SpringDemoAOP.class);
        springDemoAOP.studentService3.createStudent(student1);

//        第4种
        springDemoAOP.studentService4.createStudent(student1);

    }
}
