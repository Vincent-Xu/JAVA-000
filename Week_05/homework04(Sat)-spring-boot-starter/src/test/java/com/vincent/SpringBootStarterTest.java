package com.vincent;

import com.vincent.entity.Klass;
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
    Klass klass;
    @Test
    public void assertStudent(){
        Assert.assertEquals(11, klass.getStudents().get(0).getId());
        Assert.assertEquals("Vincent Xu",klass.getStudents().get(0).getName());
    }
}
