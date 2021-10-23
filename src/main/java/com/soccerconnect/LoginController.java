package com.soccerconnect;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping(value = "/")
    public String index()
    {
        return "index";
    }
    
    @GetMapping(value = "/login")
    public String login()
    {
        return "login";
    }

}