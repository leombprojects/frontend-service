package org.leombprojects.frontend.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackageClasses = Application.class)
@EnableFeignClients(basePackages = "org.leombprojects.frontend.clients")
@ComponentScan(basePackages = "org.leombprojects")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}