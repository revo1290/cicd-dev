package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/api/home")
    public Map<String, String> home(Principal principal) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome " + principal.getName() + "!");
        return response;
    }

}