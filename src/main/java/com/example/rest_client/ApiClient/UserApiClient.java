package com.example.rest_client.ApiClient;


import com.example.rest_client.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserApiClient {
    private static final String API_URL = "http://94.198.50.185:7081/api/users";
    private RestTemplate restTemplate = new RestTemplate();
    private String sessionCookie;

    public void getAllUsers() {
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);
        System.out.println(response);
        sessionCookie = response.getHeaders().getFirst("Set-Cookie");
        System.out.println(sessionCookie);
    }


    public String addUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionCookie);
        User user = new User(3L, "James", "Brown", (byte) 33);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);
        return  response.getBody();
    }

    public String updateUser () {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionCookie);
        User user = new User(3L, "Thomas", "Grey", (byte) 25);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.PUT, entity, String.class);
        return response.getBody();
    }

    public String deleteUser (Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionCookie);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL +"/" + id, HttpMethod.DELETE, entity, String.class);
        return response.getBody();
    }

    public String performAllTasks() {
        StringBuilder code = new StringBuilder();

        // Получение списка всех пользователей
        getAllUsers();

        // Добавление пользователя
        code.append(addUser());

        // Изменение пользователя
        code.append(updateUser());

        // Удаление пользователя
        code.append(deleteUser(3L));

        return code.toString();
    }


}
