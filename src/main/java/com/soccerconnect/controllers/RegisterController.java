package com.soccerconnect.controllers;

import com.soccerconnect.database.queries.access.RegistrationQueries;
import com.soccerconnect.database.queries.access.RegistrationQueriesInterface;
import com.soccerconnect.models.user.UserModel;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController extends MasterController{

    RegistrationQueriesInterface req = new RegistrationQueries(conn);

    @GetMapping(value = "/register")
    public String registerForm()
    {
        return "register";
    }

    @RequestMapping(value = "/registersubmission")
    public String registerSubmit(@ModelAttribute UserModel user)
    {
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        req.registrationQuery(user.getRole(), user.getEmail(), user.getName(),
                user.getMobile(), password, user.getCategory());
        if (Integer.parseInt(user.getRole())==2) {
            String teamId = rq.getUserId(user.getEmail());
            req.addTeamStats(teamId);
        }
        return "login";
    }
}
