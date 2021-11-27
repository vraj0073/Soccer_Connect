package com.soccerconnect.controllers;

import com.soccerconnect.database.queries.RolesQueries;
import org.springframework.stereotype.Controller;

@Controller
public class MasterController {

    public static String currentUserId;
    RolesQueries rq = new RolesQueries();

    public String welcome() {
        if (currentUserId != null) {
            int RoleId = rq.getRoleFromUserId(currentUserId);
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
