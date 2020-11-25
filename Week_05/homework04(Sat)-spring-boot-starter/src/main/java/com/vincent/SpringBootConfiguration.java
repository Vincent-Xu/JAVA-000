package com.vincent;


import com.vincent.entity.School;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 *@Author: Vincent Xu
 *@Description
 *@CreatedTime:18:23 2020/11/18
 */
@Configuration
@EnableConfigurationProperties(School.class)
@ConditionalOnProperty(prefix = "spring.iStarter", name = "enabled", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
public class SpringBootConfiguration implements EnvironmentAware {
    private final School school;

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println(school);
        System.out.println(environment);
    }
}
