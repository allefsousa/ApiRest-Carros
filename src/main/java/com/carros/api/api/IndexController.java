package com.carros.api.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String get(){
        return "hello";
    }
    @GetMapping("/login")
   public String login (@RequestParam("login") String login,@RequestParam("senha") String senha){
        return "login " + login + ", senha " + senha;
    }

}

