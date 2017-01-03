package com.links86.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.links86", "com.alipay", "com.ebay"})
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("logback {}", "INFO ( TRACE < DEBUG < INFO < WARN < ERROR )");
        SpringApplication.run(new Object[]{Application.class}, args);
    }

}
