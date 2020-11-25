package com.vincent;

import com.vincent.entity.School;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStarterTest.class)
@SpringBootApplication
@ActiveProfiles("common")
public class SpringBootStarterTest {
    @Resource
    School school;
    @Test
    public void assertStudent(){
        Assert.assertEquals(school.getStudent100().getId(), 11);
        Assert.assertEquals(school.getStudent100().getName(), "Vincent Xu");
    }
}
