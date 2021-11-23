package com.soccerconnect.controllers;

import com.soccerconnect.models.TeamsModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class TeamsController extends UserController{

    TeamsModel tm = new TeamsModel();

    @GetMapping(value = "/players")
    public String viewPlayers(Model model)
    {
        HashMap<Integer, String> players = tm.getPlayers(currentUserId);
        model.addAttribute("players", players);
        return "viewPlayers";
    }

    @RequestMapping(value = "/sendPlayerRequest")
    public String getPlayerRequests(@RequestParam(value = "player") String playerID)
    {
        rqm.addRequest(MasterController.currentUserId, playerID);
        return welcome();
    }

    @RequestMapping(value = "/sendTeamRequest")
    public String getTeamRequests(@RequestParam(value = "team") String teamId)
    {
        rqm.addRequest(MasterController.currentUserId, teamId);
        return welcome();
    }

    @GetMapping(value = "/pendingPlayerRequests")
    public String viewPendingPlayerRequests(Model model)
    {
        HashMap<String, String> requestsSent = rqm.getRequests(currentUserId);
        HashMap<String, String> requestsReceived = rqm.getReceivedRequests(currentUserId);
        model.addAttribute("requestsSent", requestsSent);
        model.addAttribute("requestsReceived", requestsReceived);
        return "viewPendingPlayerReqs";
    }

    @RequestMapping(value = "/acceptPlayerRequest")
    public String acceptTeamRequests(@RequestParam(value = "acceptReqId") String playerId)
    {
        tm.acceptRequest(playerId, MasterController.currentUserId);
        return welcome();
    }

    @RequestMapping(value = "/rejectPlayerRequest")
    public String rejectTeamRequests(@RequestParam(value = "rejectReqId") String playerId)
    {
        tm.rejectRequest(playerId, MasterController.currentUserId);
        return welcome();
    }

    @GetMapping(value = "/teamStats")
    public String getTeamStats(Model model)
    {
        ArrayList<String> teamStats= tm.getTeamStats(currentUserId);
        model.addAttribute("teamStats", teamStats);
        return "viewTeamStats";
    }

    @GetMapping(value = "/viewPlayers")
    public String getPlayers(Model model)
    {
        HashMap<String, String> teamPlayers = tm.getTeamPlayers(MasterController.currentUserId);
        model.addAttribute("teamPlayers", teamPlayers);
        return "viewTeamPlayers";
    }

    @RequestMapping(value = "/deletePlayer")
    public String deletePlayer(@RequestParam(value = "player") String playerId)
    {
        tm.deletePlayer(playerId, MasterController.currentUserId);
        return welcome();
    }

}
