package com.atul.ttt;

import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@EnableAutoConfiguration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                value = CommandLineRunner.class))
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.main.allow-bean-definition-overriding=true",
        classes = {AppConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBase {
}