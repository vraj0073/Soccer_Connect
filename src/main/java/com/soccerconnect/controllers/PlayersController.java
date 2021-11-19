package com.soccerconnect.controllers;

import com.soccerconnect.models.PlayerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
public class PlayersController extends UserController{

    PlayerModel pm = new PlayerModel();

    @GetMapping(value = "/teams")
    public String viewTeams(Model model)
    {
        HashMap<Integer, String> teams = pm.getTeams();
        model.addAttribute("teams", teams);
        return "viewTeams";
    }

    @GetMapping(value = "/pendingTeamRequests")
    public String viewPendingTeamRequests(Model model)
    {
        HashMap<String, String> requests = rqm.getRequests(currentUserId);
        model.addAttribute("requests", requests);
        return "viewPendingTeamReqs";
    }

}
