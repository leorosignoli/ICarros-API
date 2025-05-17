package com.abinbev.b2b.icarrosmockapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(
    exclude = {
      MultipartAutoConfiguration.class,
      JmxAutoConfiguration.class,
    })
public class ICarrosApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ICarrosApiApplication.class, args);
  }
}
