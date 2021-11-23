package com.soccerconnect.controllers;

import com.soccerconnect.models.PlayerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    
    @RequestMapping(value = "/acceptTeamRequest")
    public String acceptPlayerRequest(@RequestParam(value="acceptReqIdTeam") String TeamId){
        pm.acceptRequest(TeamId, MasterController.currentUserId);
        return welcome();
    }

    @GetMapping(value = "/pendingTeamRequests")
    public String viewPendingTeamRequests(Model model)
    {
        HashMap<String, String> requestsSent = rqm.getRequests(currentUserId);
        HashMap<String, String> requestsReceived = rqm.getReceivedRequests(currentUserId);
        model.addAttribute("requestsSent", requestsSent);
        model.addAttribute("requestsReceived", requestsReceived);
        return "viewPendingTeamReqs";
    }

    @RequestMapping(value = "/viewPlayerStats")
    public String getPlayerStats(Model model)
    {
        HashMap<String, String> playerStats = pm.getPlayerStats(MasterController.currentUserId);
        model.addAttribute("playerStats", playerStats);
        return "playerStats";
    }

    @RequestMapping(value = "/playerViewTeamStats")
    public String getPlayerTeamStats(Model model)
    {
        HashMap<String, ArrayList<String>> teamStats = pm.getEachTeamStats(MasterController.currentUserId);
        model.addAttribute("teamStats",teamStats);
        return "playerViewTeamStats";
    }

    @GetMapping(value = "/viewPlayerTeams")
    public String getPlayers(Model model)
    {
        HashMap<String, String> playersTeam = pm.getPlayersTeam(MasterController.currentUserId);
        model.addAttribute("playersTeam", playersTeam);
        return "playerLeaveTeam";
    }

    @RequestMapping(value = "/removeTeam")
    public String removeTeam(@RequestParam(value = "player") String teamId)
    {
        pm.removeTeam(teamId, MasterController.currentUserId);
        return welcome();
    }

    
}
