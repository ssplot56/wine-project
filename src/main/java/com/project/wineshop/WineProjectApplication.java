package com.project.wineshop;

import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WineProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WineProjectApplication.class, args);
        try {
            File file = new File("images/pink1.png");
            String absolutePath = file.getAbsolutePath();
            System.out.println(absolutePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
