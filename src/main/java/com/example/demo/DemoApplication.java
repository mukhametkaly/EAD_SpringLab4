package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Lab4SpringConfigs.class);
        Payroll payrollSystem = context.getBean("payrollSystem", Payroll.class);
        payrollSystem.run();

    }
}
