package com.soccerconnect.controllers;

import com.soccerconnect.models.DBConnectionApp;
import com.soccerconnect.models.RolesModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MasterController {

    public static String currentUserId;
    DBConnectionApp db = new DBConnectionApp();
    RolesModel rm = new RolesModel();
    public String welcome() {
        if (currentUserId != null) {
            int RoleId = rm.getRoleFromEmail(currentUserId);
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
        else{
            return "login";
        }
    }
}
