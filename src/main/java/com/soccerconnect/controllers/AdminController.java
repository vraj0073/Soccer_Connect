package com.soccerconnect.controllers;

import com.soccerconnect.models.AdminModel;
import com.soccerconnect.models.AddGround;

import com.soccerconnect.models.Organize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;


@Controller
public class AdminController extends MasterController{

    AdminModel am = new AdminModel();
    AddGround addg = new AddGround();
    Organize orgGame = new Organize();

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

    @GetMapping(value = "/addGround")
    public String addGround()
    {
        return "addGround";
    }

    @GetMapping(value = "/organizeGame")
    public String organize(Model model)
    {
        HashMap<Integer, String> teams = am.getAllTeams();
        HashMap<Integer, String> grounds = addg.getAllGrounds();
        model.addAttribute("teams", teams);
        model.addAttribute("grounds", grounds);
        return "organizeGame";
    }

    @RequestMapping(value = "/ground")
    public String addGround(@RequestParam(value = "stadiumName") String groundName,
                            @RequestParam(value = "address") String address,
                            @RequestParam(value = "postalCode") String postalCode,
                            @RequestParam(value = "phone") String phone,
                            @RequestParam(value = "email") String email) {
        addg.groundQuery(groundName, address, postalCode, phone, email);
        return "welcomeAdmin";
    }

    @RequestMapping(value = "/organize")
    public String organize(@RequestParam(value = "category") String category,
                           @RequestParam(value = "team1") String team1,
                           @RequestParam(value = "team2") String team2,
                           @RequestParam(value = "ground") String ground,
                           @RequestParam(value = "date") String date,
                           @RequestParam(value = "time") String time) {
        orgGame.organize(category, team1, team2, ground, date, time);
        return "welcomeAdmin";
    }
}
