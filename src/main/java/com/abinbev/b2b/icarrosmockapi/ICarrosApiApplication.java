package com.abinbev.b2b.icarrosmockapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(
    exclude = {
      MultipartAutoConfiguration.class,
      JmxAutoConfiguration.class,
    })
@ConfigurationPropertiesScan
public class ICarrosApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ICarrosApiApplication.class, args);
  }
}
