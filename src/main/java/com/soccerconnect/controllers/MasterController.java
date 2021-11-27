package com.soccerconnect.controllers;

import com.soccerconnect.database.queries.RolesQueries;
import org.apache.catalina.Session;
import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;

import javax.security.auth.message.callback.PrivateKeyCallback;
import javax.servlet.http.HttpSession;

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
