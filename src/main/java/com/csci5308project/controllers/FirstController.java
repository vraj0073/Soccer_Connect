package com.csci5308project;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class FirstController {
    
    @GetMapping(path = "/first")
    public String initializeApp(){
        return "Hello World.";
    }

}