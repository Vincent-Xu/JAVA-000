package com.vincent.service.impl;

import com.vincent.entity.Student;
import com.vincent.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Xu
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public void createStudent(Student student) {
        System.out.println(student.getName() + " student created");
    }
}
