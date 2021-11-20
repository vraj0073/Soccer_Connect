package com.soccerconnect.controllers;


import com.soccerconnect.models.LoginModel;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController extends MasterController{

    LoginModel lm = new LoginModel();

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
                                  @RequestParam(value = "password") String password) {

        String passwordDb = lm.getPasswordFromEmail(email);
        if (BCrypt.checkpw(password, passwordDb)) {
            MasterController.currentUserId = rm.getUserId(email);
            int RoleId = rm.getRoleFromEmail(email);
            if (RoleId == 0) {
                return "welcomeAdmin";
            } else if (RoleId == 1) {
                return "welcomePlayer";
            } else if (RoleId == 2) {
                return "welcomeTeam";
            } else {
                return "login";
            }
        }
        else return "login";
    }
}