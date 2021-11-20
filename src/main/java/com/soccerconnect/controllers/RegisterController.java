package com.soccerconnect.controllers;

import com.soccerconnect.models.RegistrationModel;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController extends MasterController{

    RegistrationModel rem = new RegistrationModel();

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
                                  @RequestParam(value = "category") String category)
    {
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        rem.registrationQuery(role, email, name,
                mobile, password, category);
        return "login";
    }
}
