package me.codebase.rest.mock.entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by chendong on 2017/11/30.
 */
@SpringBootApplication
@ComponentScan("me.codebase.rest.mock")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
