package com.example.demo;

import com.example.demo.spi.SpiService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ServiceLoader;

@SpringBootApplication
@MapperScan(basePackages= "com.example.demo.dao.**")
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  public void spiAuto() {
    ServiceLoader<SpiService> load = ServiceLoader.load(SpiService.class);
    for (SpiService spiService : load) {
      spiService.eat();
    }
  }

}
