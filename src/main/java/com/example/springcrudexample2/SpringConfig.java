package com.example.springcrudexample2;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class SpringConfig {
    JdbcTemplate template;

    @Autowired
    public SpringConfig(JdbcTemplate template) {
        this.template = template;
    }

    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserDAO userRepository(){
        return new UserDAO(template);
    }


}
