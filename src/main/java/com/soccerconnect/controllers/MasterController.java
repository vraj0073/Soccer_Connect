package com.soccerconnect.controllers;

import com.soccerconnect.database.DBConnectionApp;
import com.soccerconnect.database.queries.access.RolesQueries;
import com.soccerconnect.database.queries.access.RolesQueriesInterface;
import com.soccerconnect.utils.ConfigReader;
import com.soccerconnect.utils.ConfigReaderInterface;
import org.springframework.stereotype.Controller;

import java.sql.Connection;


@Controller
public class MasterController {

    String configFileName = "application.properties";
    ConfigReaderInterface configReader = new ConfigReader(configFileName);
    DBConnectionApp db = DBConnectionApp.getDbApp(configReader);
    protected Connection conn = db.getConnection();

    protected static String currentUserId;
    protected RolesQueriesInterface rq = new RolesQueries(conn);


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
