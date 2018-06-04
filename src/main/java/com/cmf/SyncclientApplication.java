package com.cmf;

import com.cmf.config.ClientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ClientProperties.class})
public class SyncclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyncclientApplication.class, args);
    }


}
