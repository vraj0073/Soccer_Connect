package com.csci5308project;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FirstController {
    
    @GetMapping(value = "/first")
    public String initializeApp(){
        return "firstpage";
    }

}