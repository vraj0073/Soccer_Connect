package com.soccerconnect.controllers;

import com.soccerconnect.DBConnectionApp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
        HashMap<String, String> requestsSent = db.getRequests(currentUserId);
        HashMap<String, String> requestsReceived = db.getReceivedRequests(currentUserId);
        model.addAttribute("requestsSent", requestsSent);
        model.addAttribute("requestsReceived", requestsReceived);
        return "viewPendingPlayerReqs";
    }

    @RequestMapping(value = "/acceptPlayerRequest")
    public String acceptTeamRequests(@RequestParam(value = "acceptReqId") String playerId)
    {
        db.acceptRequest(playerId, MasterController.currentUserId);
        return welcome();
    }

    @GetMapping(value = "/teamStats")
    public String getTeamStats(Model model)
    {
        ArrayList<String> teamStats= db.getTeamStats(currentUserId);
        model.addAttribute("teamStats", teamStats);
        return "viewTeamStats";
    }

    @GetMapping(value = "/viewPlayers")
    public String getPlayers(Model model)
    {
        HashMap<String, String> teamPlayers = db.getTeamPlayers(MasterController.currentUserId);
        model.addAttribute("teamPlayers", teamPlayers);
        return "viewTeamPlayers";
    }

    @RequestMapping(value = "/deletePlayer")
    public String deletePlayer(@RequestParam(value = "player") String playerId)
    {
        db.deletePlayer(playerId, MasterController.currentUserId);
        return welcome();
    }

}
