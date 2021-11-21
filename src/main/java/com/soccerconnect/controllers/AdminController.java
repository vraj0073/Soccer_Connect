package com.soccerconnect.controllers;

import com.soccerconnect.models.AdminModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class AdminController extends MasterController{

    AdminModel am = new AdminModel();

    @GetMapping(value = "/viewAllPlayers")
    public String viewPlayers(Model model)
    {
        HashMap<Integer, String> players = am.getAllPlayers();
        model.addAttribute("players", players);
        return "viewAllPlayers";
    }

    @RequestMapping(value = "/adminDeletePlayer")
    public String deletePlayer(@RequestParam(value = "player") String playerId)
    {
        am.deleteUser(playerId);
        return welcome();
    }

    @GetMapping(value = "/viewAllTeams")
    public String viewTeams(Model model)
    {
        HashMap<Integer, String> teams = am.getAllTeams();
        model.addAttribute("teams", teams);
        return "viewAllTeams";
    }

    @RequestMapping(value = "/adminDeleteTeam")
    public String deleteTeam(@RequestParam(value = "team") String teamId)
    {
        am.deleteUser(teamId);
        return welcome();
    }
}
