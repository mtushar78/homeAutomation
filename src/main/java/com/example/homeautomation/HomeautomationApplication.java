package com.example.homeautomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

@SpringBootApplication
public class HomeautomationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeautomationApplication.class, args);
    }
    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder){

        return builder.build();
    }
}
