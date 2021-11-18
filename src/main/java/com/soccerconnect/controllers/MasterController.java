package com.soccerconnect.controllers;

import com.soccerconnect.DBConnectionApp;
import org.springframework.stereotype.Controller;

@Controller
public class MasterController {

    public static String currentUserId;
    DBConnectionApp db = new DBConnectionApp();

    public String welcome() {
        if (currentUserId != null) {
            int RoleId = db.getRoleFromUserId(currentUserId);
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
