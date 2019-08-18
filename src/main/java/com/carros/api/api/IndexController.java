package com.carros.api.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String get(){
        return "hello";
    }
    @GetMapping("/login/{login}/senha/{senha}")
    // request = http://localhost:8080/login/allef/senha/sousa
   public String login (@PathVariable("login") String login,@PathVariable("senha") String senha){
        return "login " + login + ", senha " + senha;
    }

}

