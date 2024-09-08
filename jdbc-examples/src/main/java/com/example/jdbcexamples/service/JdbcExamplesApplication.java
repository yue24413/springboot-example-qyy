package com.example.jdbcexamples.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcExamplesApplication {
//JdbcExamplesApplication是SpringBootApplication的配置类
//包含了@EnableAutoconfiguration(支持启动自动配置)和ComponentScan(组件的扫描)
    public static void main(String[] args) {
        //启动SpringBoot,基于SpringBoot的配置类,即SpringbootExamplesApplication
        SpringApplication.run(JdbcExamplesApplication.class, args);
    }
}
