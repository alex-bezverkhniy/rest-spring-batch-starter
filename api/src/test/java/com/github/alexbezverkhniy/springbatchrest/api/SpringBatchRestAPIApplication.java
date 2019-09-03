package com.github.alexbezverkhniy.springbatchrest.api;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchRestAPIApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringBatchRestAPIApplication.class, args);
  }
}
