package com.xinnn.tour;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.xinnn.tour.mapper"})
public class Tour {
    public static void main(String[] args) {
        SpringApplication.run(Tour.class);
    }
}