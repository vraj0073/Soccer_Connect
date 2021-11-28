package com.soccerconnect.controllers;

import com.soccerconnect.models.PlayerStatsModel;
import com.soccerconnect.models.TeamStatsModel;
import com.soccerconnect.models.TeamModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class PlayersController extends UserController{

    @RequestMapping(value = "/viewPlayerStats")
    public String getPlayerStats(Model model)
    {
        PlayerStatsModel playerStats = pq.getPlayerStats(MasterController.currentUserId);
        model.addAttribute("playerStats", playerStats);
        return "playerStats";
    }

    @GetMapping(value = "/teams")
    public String viewTeams(Model model)
    {
        ArrayList<TeamModel> teams = tq.getTeams();
        model.addAttribute("teams", teams);
        return "viewTeams";
    }

    @RequestMapping(value = "/sendTeamRequest")
    public String getTeamRequests(@RequestParam(value = "team") String teamId)
    {
        rqq.addRequest(MasterController.currentUserId, teamId);
        return welcome();
    }

    @GetMapping(value = "/pendingTeamRequests")
    public String viewPendingTeamRequests(Model model)
    {
        HashMap<String, String> requestsSent = rqq.getRequests(currentUserId);
        HashMap<String, String> requestsReceived = rqq.getReceivedRequests(currentUserId);
        model.addAttribute("requestsSent", requestsSent);
        model.addAttribute("requestsReceived", requestsReceived);
        return "viewPendingTeamReqs";
    }
    
    @RequestMapping(value = "/acceptTeamRequest")
    public String acceptPlayerRequest(@RequestParam(value="acceptReqIdTeam") String TeamId)
    {
        pq.acceptRequest(TeamId, MasterController.currentUserId);
        return welcome();
    }

    @RequestMapping(value = "/rejectTeamRequest")
    public String rejectTeamRequests(@RequestParam(value = "rejectReqIdTeam") String TeamId)
    {
        pq.rejectRequest(TeamId, MasterController.currentUserId);
        return welcome();
    }

    @RequestMapping(value = "/playerViewTeamStats")
    public String getPlayerTeamStats(Model model)
    {
        ArrayList<TeamStatsModel> teamStats = pq.getEachTeamStats(MasterController.currentUserId);
        model.addAttribute("teamStats",teamStats);
        return "playerViewTeamStats";
    }

    @GetMapping(value = "/viewPlayerTeams")
    public String getPlayerTeams(Model model)
    {
        ArrayList<TeamModel> playersTeams = pq.getPlayersTeams(MasterController.currentUserId);
        model.addAttribute("playersTeams", playersTeams);
        return "playerLeaveTeam";
    }

    @RequestMapping(value = "/removeTeam")
    public String removeTeam(@RequestParam(value = "team") String teamId)
    {
        pq.removeTeam(teamId, MasterController.currentUserId);
        return welcome();
    }

    
}
