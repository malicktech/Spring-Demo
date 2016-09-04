package org.jdw.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class ReceiverMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ReceiverMain.class, args);
    }

}
