package com.vincent.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *@Author: Vincent Xu
 *@Description: Binding student converter to spring configuration
 *@CreatedTime:23:10 2020/11/25
 */
@Configuration
public class PropertiesConfig {
    @Bean
    @ConfigurationPropertiesBinding
    public StudentConverter studentConverter() {
        return new StudentConverter();
    }
}
