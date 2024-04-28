package com.example.rest_client;

import com.example.rest_client.ApiClient.UserApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestClientApplication {

    public static void main(String[] args) {
//        SpringApplication.run(RestClientApplication.class, args);
        UserApiClient client = new UserApiClient();
        String code = client.performAllTasks();
        System.out.println("Final Code: " + code);

    }

}
