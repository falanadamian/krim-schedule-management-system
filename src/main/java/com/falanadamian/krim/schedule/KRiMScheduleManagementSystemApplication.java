package com.falanadamian.krim.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class KRiMScheduleManagementSystemApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(KRiMScheduleManagementSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KRiMScheduleManagementSystemApplication.class, args);
    }
}
