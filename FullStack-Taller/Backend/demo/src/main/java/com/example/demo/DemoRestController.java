package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoRestController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/hello")
    public String hello(String email, String token) {
        System.out.println("email= " + email);
        System.out.println("emailtoken= " + token);
        User u = new User(email, email);
        System.out.println("u.token= " + u.token);
        if (u.token.equals(token)) {
            return "Hello World!";
        } else {
            return "Hello World! token not match";
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/hellolist")
    public List<String> helloList() {
        List<String> list = List.of("Hello", "World!");
        return list;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public User login(String user, String passwd) {

        User u = new User(user, passwd);

        return u;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/login")
    public User login() {

        User u = new User("user", "passwd");

        return u;
    }

}
