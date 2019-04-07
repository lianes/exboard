package com.lianes.ex.board.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.lianes.ex.board.dao", "com.lianes.ex.board.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
