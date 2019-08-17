package com.carros.api.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String get(){
        return "hello";
    }
    @PostMapping
    public String post(){
        return "hello post";
    }
    @PutMapping
    public String put(){
        return "hello put";
    }
    @DeleteMapping
    public String delete(){
        return "hello delete";
    }

}

