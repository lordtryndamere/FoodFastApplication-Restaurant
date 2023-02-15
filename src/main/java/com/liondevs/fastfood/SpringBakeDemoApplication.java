package com.liondevs.fastfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableDiscoveryClient
@SpringBootApplication
public class SpringBakeDemoApplication {

    //para uasr resttemplate
//@Bean
//@LoadBalanced
//    RestTemplate restTemplate(){
//    return new RestTemplate();
//}
    public static void main(String[] args) {
        SpringApplication.run(SpringBakeDemoApplication.class, args);
    }

}
