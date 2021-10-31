package com.soccerconnect.controllers;

import com.soccerconnect.DBConnectionApp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @GetMapping(value = "/register")
    public String registerForm()
    {
        return "register";
    }

    @RequestMapping(value = "/registersubmission")
    public String registerSubmit( @RequestParam(value = "role") String role,
                                  @RequestParam(value = "email") String email,
                                  @RequestParam(value = "name") String name,
                                  @RequestParam(value = "mobile") String mobile,
                                  @RequestParam(value = "password") String password,
                                  @RequestParam(value = "repassword") String repassword,
                                  @RequestParam(value = "category") String category,
                                  DBConnectionApp db)
    {
//        System.out.println("Role: "+ role);
//        System.out.println("Email: "+ email);
//        System.out.println("name: "+ name);
//        System.out.println("mobile: "+ mobile);
//        System.out.println("Password: "+ password);
//        System.out.println("repassword: "+ repassword);
//        System.out.println("category: "+ category);
        db.registrationQuery(role, email, name,
                mobile, password, category);
        return "login";
    }
}
