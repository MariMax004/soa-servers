package com.example.labsoaservice1.application.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {
    @GetMapping("/health")
    public String check(){
        return "Ok";
    }
}
