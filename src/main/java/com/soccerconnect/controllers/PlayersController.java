package com.soccerconnect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class PlayersController extends MasterController{

    @GetMapping(value = "/pendingTeamRequests")
    public String viewPendingTeamRequests(Model model)
    {
        HashMap<String, String> requests = db.getRequests(currentUserId);
        model.addAttribute("requests", requests);
        return "viewPendingTeamReqs";
    }

    @GetMapping(value = "/players")
    public String viewPlayers(Model model)
    {
        HashMap<Integer, String> players = db.getPlayers();
        model.addAttribute("players", players);
        return "viewPlayers";
    }

    @RequestMapping(value = "/sendPlayerRequest")
    public String getPlayerRequests(@RequestParam(value = "player") String playerID)
    {
        db.addRequest(MasterController.currentUserId, playerID);
        return welcome();
    }

}
