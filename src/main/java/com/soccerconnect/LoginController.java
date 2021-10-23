package com.soccerconnect;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @GetMapping(value = "/")
    public String index()
    {
        return "index";
    }
    
    @GetMapping(value = "/login")
    public String loginForm()
    {
        return "login";
    }

    @RequestMapping(value = "/loginsubmission")
    public String loginSubmit(    @RequestParam(value = "email") String email,
                                  @RequestParam(value = "password") String password,
                                  Model model)
    {
//        System.out.println("Email: "+ email);
//        System.out.println("Password: "+ password);
        return "welcome";
    }



}