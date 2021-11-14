package com.soccerconnect.controllers;

import com.soccerconnect.DBConnectionApp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class TeamsController extends MasterController{

    DBConnectionApp db = new DBConnectionApp();

    @GetMapping(value = "/teams")
    public String viewTeams(Model model)
    {
        HashMap<Integer, String> teams = db.getTeams();
        model.addAttribute("teams", teams);
        return "viewTeams";
    }

    @RequestMapping(value = "/sendTeamRequest")
    public String getTeamRequests(@RequestParam(value = "team") String teamId)
    {
        db.addRequest(MasterController.currentUserId, teamId);
        return welcome();
    }

    @GetMapping(value = "/pendingPlayerRequests")
    public String viewPendingPlayerRequests(Model model)
    {
        HashMap<String, String> requests = db.getRequests(currentUserId);
        model.addAttribute("requests", requests);
        return "viewPendingPlayerReqs";
    }

}
