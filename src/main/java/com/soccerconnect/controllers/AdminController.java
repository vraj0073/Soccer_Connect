package com.soccerconnect.controllers;

import com.soccerconnect.database.queries.AdminQueries;
import com.soccerconnect.database.queries.GroundQueries;
import com.soccerconnect.database.queries.GamesQueries;

import com.soccerconnect.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;


@Controller
public class AdminController extends MasterController{

    AdminQueries aq = new AdminQueries();
    GroundQueries gq = new GroundQueries();
    GamesQueries gaq = new GamesQueries();

    @GetMapping(value = "/viewAllPlayers")
    public String viewPlayers(Model model)
    {
        ArrayList<PlayerModel> players = aq.getAllPlayers();
        model.addAttribute("players", players);
        return "viewAllPlayers";
    }

    @RequestMapping(value = "/adminDeletePlayer")
    public String deletePlayer(@RequestParam(value = "player") String playerId, HttpSession session)
    {
        UserModel user = (UserModel)session.getAttribute("user");
        if(user !=null){
            aq.deleteUser(playerId);

        }
        return welcome();
    }

    @GetMapping(value = "/viewAllTeams")
    public String viewTeams(Model model)
    {
        ArrayList<TeamModel> teams = aq.getAllTeams();
        model.addAttribute("teams", teams);
        return "viewAllTeams";
    }

    @RequestMapping(value = "/adminDeleteTeam")
    public String deleteTeam(@RequestParam(value = "team") String teamId)
    {
        aq.deleteUser(teamId);
        return welcome();
    }

    @GetMapping(value = "/addGround")
    public String addGround()
    {
        return "addGround";
    }

    @RequestMapping(value = "/ground")
    public String addGround(@ModelAttribute GroundModel ground) {
        gq.groundQuery(ground.getGroundName(), ground.getAddress(),
                ground.getPostalCode(), ground.getContact(), ground.getEmail());
        return "welcomeAdmin";
    }

    @GetMapping(value = "/viewAllGrounds")
    public String viewGrounds(Model model)
    {
        ArrayList<GroundModel> grounds = gq.getAllGrounds();
        model.addAttribute("grounds", grounds);
        return "viewAllGrounds";
    }

    @RequestMapping(value = "/adminDeleteGround")
    public String deleteGround(@RequestParam(value = "ground") String groundId)
    {
        gq.deleteGround(groundId);
        return welcome();
    }

    @GetMapping(value = "/organizeGame")
    public String organizeGame(Model model)
    {
        ArrayList<TeamModel> teams = aq.getAllTeams();
        ArrayList<GroundModel> grounds = gq.getAllGrounds();
        model.addAttribute("teams", teams);
        model.addAttribute("grounds", grounds);
        return "organizeGame";
    }

    @RequestMapping(value = "/organize")
    public String organize(@ModelAttribute GameModel game) {
        gaq.organize(game.getCategory(), game.getTeam1Id(), game.getTeam2Id(),
                game.getGroundId(), game.getDate(), game.getTime());
        return "welcomeAdmin";
    }

    @GetMapping(value = "/viewGames")
    public String viewGames(Model model)
    {
        ArrayList<GameModel> games = gaq.getGames();
        HashMap<String, String> teamIdToName = aq.getTeamIdToName();
        HashMap<String, String> groundIdToName = aq.getGroundIdToName();
        model.addAttribute("teamIdToName", teamIdToName);
        model.addAttribute("groundIdToName", groundIdToName);
        model.addAttribute("games", games);
        return "viewGames";
    }

}
