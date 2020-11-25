package com.vincent.util;

import com.alibaba.fastjson.JSON;
import com.vincent.entity.Student;
import org.springframework.core.convert.converter.Converter;


/**
 *@Author: Vincent Xu
 *@Description: Converter for student configuration
 *@CreatedTime:22:32 2020/11/25
 */
public class StudentConverter implements Converter<String, Student> {
    @Override
    public Student convert(String s) {
        return JSON.parseObject(s, Student.class);
    }
}
