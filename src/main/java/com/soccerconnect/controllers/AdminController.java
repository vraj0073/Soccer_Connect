package com.soccerconnect.controllers;

import com.soccerconnect.models.AdminModel;
import com.soccerconnect.models.AddGround;
import com.soccerconnect.models.DBConnectionApp;

import com.soccerconnect.models.Organize;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

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

    @GetMapping(value = "/ground")
    public String addGround()
    {
        return "ground";
    }

    @GetMapping(value = "/organize")
    public String organize(){return "organize"; }


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true, 10));
    }
    @RequestMapping(value = "/ground")
    public String addGround(        @RequestParam(value = "stadiumName") String stadiumName,
                                    @RequestParam(value = "address") String address,
                                    @RequestParam(value = "postalCode") String postalCode,
                                    @RequestParam(value = "phone") String phone,
                                    @RequestParam(value = "email") String email){
        addg.groundQuery(stadiumName, address, postalCode, phone, email);
        return "welcomeAdmin";
    }
    @RequestMapping(value = "/organize")
    public String organize(    @RequestParam(value = "category") String category,
                                    @RequestParam(value = "Team1") String Team1,
                                    @RequestParam(value = "Team2") String Team2,
                                    @RequestParam(value = "stadium") String stadium,
                                    @RequestParam(value = "date") String  date,
                                    @RequestParam(value = "Time") String Time){
        System.out.println(category +Team1 + Team2 + stadium +date +Time );
        orgGame.organize(category,Team1,Team2, stadium, date, Time);
        return "welcomeAdmin";
    }
}
