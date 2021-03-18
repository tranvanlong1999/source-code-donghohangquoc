package com.example.thuctaptotnghiep.donghohanquoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
public class DonghohanquocApplication {
    public static void main(String[] args) {
        SpringApplication.run(DonghohanquocApplication.class, args);
    }
}
