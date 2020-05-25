package com.chohongjae.boardweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardwebApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BoardwebApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);
    }
}
