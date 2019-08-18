package com.carros.api.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String get(){
        return "hello";
    }


}

