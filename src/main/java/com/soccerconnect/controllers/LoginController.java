package com.soccerconnect.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController extends MasterController{

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

        MasterController.currentUserId = rm.getUserId(email);
        int RoleId = rm.getRoleFromEmail(email);
        if (RoleId == 0) {
            return "welcomeAdmin";
        } else if (RoleId == 1) {
            return "welcomePlayer";
        } else if (RoleId == 2){
            return "welcomeTeam";
        }else{
            return "login";
        }
    }
}