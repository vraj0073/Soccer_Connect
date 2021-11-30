package com.soccerconnect.controllers;

import com.soccerconnect.database.queries.LoginQueries;
import com.soccerconnect.models.UserModel;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


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
    public String loginSubmit(@ModelAttribute UserModel user,HttpSession session) {
        if(session != null)
        {
            String passwordDb = lq.getPasswordFromEmail(user.getEmail());
            if (BCrypt.checkpw(user.getPassword(), passwordDb)) {
                MasterController.currentUserId = rq.getUserId(user.getEmail());
                session.setAttribute("current_user",currentUserId);
                return welcome();
            }
            else return "login";
        }
        else return "login";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){

        session.removeAttribute("current_user");
        session.invalidate();
        return "login";
    }
    @GetMapping("/info")
    public String userInfo(HttpSession session) {
        return "user";
    }

}