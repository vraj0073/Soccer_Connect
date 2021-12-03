package com.soccerconnect.controllers;

import com.soccerconnect.database.queries.AdminQueries;
import com.soccerconnect.database.queries.GroundQueries;
import com.soccerconnect.database.queries.GamesQueries;
import com.soccerconnect.models.*;
import com.soccerconnect.database.queries.TeamsQueries;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;


@Controller
public class AdminController extends MasterController{

    AdminQueries aq = new AdminQueries();
    GroundQueries gq = new GroundQueries();
    GamesQueries gaq = new GamesQueries();
    TeamsQueries tq = new TeamsQueries();

    @GetMapping(value = "/viewAllPlayers")
    public String viewPlayers(Model model)
    {
        ArrayList<PlayerModel> players = aq.getAllPlayers();
        model.addAttribute("players", players);
        return "viewAllPlayers";
    }

    @RequestMapping(value = "/adminDeletePlayer")
    public String deletePlayer(@RequestParam(value = "player") String playerId)
    {
        aq.deleteUser(playerId);
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
    public String groundForm()
    {
        return "addGround";
    }

    @RequestMapping(value = "/groundSubmission")
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
        ArrayList<GameModel> games = gaq.getGames(aq);
        model.addAttribute("games", games);
        return "viewGames";
    }

    @RequestMapping(value = "/scoreGame")
    public String scoreGame(Model model,
                            @RequestParam(value = "score", defaultValue = "") String scoreGameId,
                            @RequestParam(value = "delete", defaultValue = "") String deleteGameId)
    {
        if (deleteGameId.isEmpty()){
            GameModel game = gaq.getGameDetails(scoreGameId, aq);
            ArrayList<PlayerModel> team1Players = tq.getTeamPlayers(game.getTeam1Id());
            ArrayList<PlayerModel> team2Players = tq.getTeamPlayers(game.getTeam2Id());

            ArrayList<PlayerStatsModel> team1PlayerStats = new ArrayList<>();
            for(PlayerModel player: team1Players){
                team1PlayerStats.add(new PlayerStatsModel(player.getUserId(), player.getName()));
            }
            ArrayList<PlayerStatsModel> team2PlayerStats = new ArrayList<>();
            for(PlayerModel player: team2Players){
                team2PlayerStats.add(new PlayerStatsModel(player.getUserId(), player.getName()));
            }
            StatsModel teamStats = new StatsModel(team1PlayerStats, team2PlayerStats);
            teamStats.setTeam1Id(game.getTeam1Id());
            teamStats.setTeam2Id(game.getTeam2Id());

            model.addAttribute("game", game);
            model.addAttribute("teamStats", teamStats);
            return "scoreGame";
        }
        else{
            aq.deleteGame(deleteGameId);
            return welcome();
        }
    }

    @RequestMapping(value = "/score")
    public String manageStats(@ModelAttribute StatsModel teamStats)
    {
        processTeamStats(teamStats);
        processTeamPlayerStats(teamStats.team1Id, teamStats.team1PlayersStats);
        processTeamPlayerStats(teamStats.team2Id, teamStats.team2PlayersStats);
        return welcome();

    }

    private void processTeamPlayerStats(String teamId, ArrayList<PlayerStatsModel> teamPlayerStats) {
        for (PlayerStatsModel playerStat : teamPlayerStats) {
            PlayerStatsModel existingPlayerStat = aq.getPlayerStatsByTeam(playerStat.getPlayerId(), teamId);
            existingPlayerStat.setNom(String.valueOf(Integer.parseInt(existingPlayerStat.getNom()) + 1));
            existingPlayerStat.setGoals(String.valueOf(Integer.parseInt(
                    existingPlayerStat.getGoals()) + Integer.parseInt(playerStat.getGoals())));
            existingPlayerStat.setAsst(String.valueOf(Integer.parseInt(
                    existingPlayerStat.getAsst()) + Integer.parseInt(playerStat.getAsst())));
            existingPlayerStat.setGoalsSaved(String.valueOf(Integer.parseInt(
                    existingPlayerStat.getGoalsSaved()) + Integer.parseInt(playerStat.getGoalsSaved())));
            existingPlayerStat.setYellowCards(String.valueOf(Integer.parseInt(
                    existingPlayerStat.getYellowCards()) + Integer.parseInt(playerStat.getYellowCards())));
            existingPlayerStat.setRedCards(String.valueOf(Integer.parseInt(
                    existingPlayerStat.getRedCards()) + Integer.parseInt(playerStat.getRedCards())));
            existingPlayerStat.setMom(String.valueOf(Integer.parseInt(
                    existingPlayerStat.getMom()) + Integer.parseInt(playerStat.getMom())));
            aq.updatePlayerStats(existingPlayerStat, teamId);
        }
    }

    private void processTeamStats(StatsModel teamStats) {
        TeamStatsModel team1Stats = tq.getTeamStats(teamStats.getTeam1Id());
        TeamStatsModel team2Stats = tq.getTeamStats(teamStats.getTeam2Id());

        int team1goals = Integer.parseInt(teamStats.getTeam1Goals());
        int team2goals = Integer.parseInt(teamStats.getTeam2Goals());

        team1Stats.setNom(String.valueOf(Integer.parseInt(team1Stats.getNom()) + 1));
        team2Stats.setNom(String.valueOf(Integer.parseInt(team2Stats.getNom()) + 1));

        team1Stats.setGoals(String.valueOf(Integer.parseInt(team1Stats.getGoals()) + team1goals));
        team2Stats.setGoals(String.valueOf(Integer.parseInt(team2Stats.getGoals()) + team2goals));

        if(team1goals>team2goals){
            team1Stats.setWins(String.valueOf(Integer.parseInt(team1Stats.getWins()) + 1));
            team2Stats.setLosses(String.valueOf(Integer.parseInt(team2Stats.getLosses()) + 1));
        }else if(team2goals>team1goals){
            team2Stats.setWins(String.valueOf(Integer.parseInt(team2Stats.getWins()) + 1));
            team1Stats.setLosses(String.valueOf(Integer.parseInt(team1Stats.getLosses()) + 1));
        }else{
            team1Stats.setDraws(String.valueOf(Integer.parseInt(team1Stats.getDraws()) + 1));
            team2Stats.setDraws(String.valueOf(Integer.parseInt(team2Stats.getDraws()) + 1));
        }

        aq.updateTeamStats(team1Stats);
        aq.updateTeamStats(team2Stats);
    }
    @GetMapping(value = "/teamRanking")
    public String teamRanking(Model model)
    {
        ArrayList<StatsModel> rank = aq.getTeamRanking();
        rank.sort(Comparator.comparing(StatsModel::getWins).thenComparing(StatsModel::getDraws).reversed());
        model.addAttribute("rank", rank);
        return "teamRanking";
    }

}
