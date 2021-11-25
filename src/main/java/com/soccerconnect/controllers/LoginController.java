package com.soccerconnect.controllers;

import com.soccerconnect.database.queries.LoginQueries;
import com.soccerconnect.models.UserModel;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController extends MasterController{

    LoginQueries lq = new LoginQueries();

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
    public String loginSubmit(@ModelAttribute UserModel user) {

        String passwordDb = lq.getPasswordFromEmail(user.getEmail());
        if (BCrypt.checkpw(user.getPassword(), passwordDb)) {
            MasterController.currentUserId = rq.getUserId(user.getEmail());
            return welcome();
        }
        else return "login";
    }
}