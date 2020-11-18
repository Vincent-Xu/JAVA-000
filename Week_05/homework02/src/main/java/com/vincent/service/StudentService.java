package com.vincent.service;

import com.vincent.entity.Student;

/**
 * @author Vincent Xu
 */
public interface StudentService {
    /**
     * 创建学生记录
     * @param student
     */
    void createStudent(Student student);
}
