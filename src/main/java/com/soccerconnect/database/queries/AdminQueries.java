package com.soccerconnect.database.queries;

import com.soccerconnect.database.DBConnectionApp;
import com.soccerconnect.models.PlayerModel;
import com.soccerconnect.models.PlayerStatsModel;
import com.soccerconnect.models.TeamModel;
import com.soccerconnect.models.TeamStatsModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminQueries extends DBConnectionApp {

    public ArrayList<PlayerModel> getAllPlayers() {
        ArrayList<PlayerModel> players = new ArrayList<>();
        String query = "SELECT User_ID,Name from users where Role_Id='1';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                players.add(new PlayerModel(rs.getString("User_ID"), rs.getString("Name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return players;
    }

    public void deleteUser(String userId) {
        String query = "DELETE FROM users WHERE User_ID='" + userId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<TeamModel> getAllTeams() {
        ArrayList<TeamModel> teams=new ArrayList<>();
        String query = "SELECT User_ID,Name from users where Role_Id='2';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                teams.add(new TeamModel(rs.getString("User_ID"), rs.getString("Name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teams;
    }

    public HashMap<String, String> getTeamIdToName(){
        HashMap<String, String> teamIdToName = new HashMap<>();
        String query = "SELECT User_ID,Name from users where Role_Id='2';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                teamIdToName.put(rs.getString("User_ID"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teamIdToName;
    }

    public HashMap<String, String> getGroundIdToName(){
        HashMap<String, String> groundIdToName = new HashMap<>();
        String query = "SELECT Ground_ID,Ground_Name from Grounds;";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                groundIdToName.put(rs.getString("Ground_ID"), rs.getString("Ground_Name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return groundIdToName;
    }

    public void deleteGame(String deleteGameId) {
        String query = "DELETE FROM games WHERE game_ID='" + deleteGameId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateTeamStats(TeamStatsModel team1Stats) {
        String query = "UPDATE TeamStats SET NOM='"+team1Stats.getNom()+"', Goals='"+
                team1Stats.getGoals()+"', Wins='"+team1Stats.getWins()+"', Losses='"+
                team1Stats.getLosses()+"', Draws='"+team1Stats.getDraws()+"' WHERE Team_ID='"+
                team1Stats.getTeamId()+"';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public PlayerStatsModel getPlayerStatsByTeam(String playerId, String teamId){

        PlayerStatsModel playerStats=null;
        String query = "SELECT NOM, goals, assists, goals_saved, yellow_Card, red_card, " +
                "MOM from PlayerStats where player_id ="+playerId +" and team_id="+teamId+";";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            playerStats = new PlayerStatsModel(playerId, rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return playerStats;
    }

    public void updatePlayerStats(PlayerStatsModel existingPlayerStat, String teamId) {
        String query = "UPDATE PlayerStats SET NOM='"+existingPlayerStat.getNom()+"', Goals='"+
                existingPlayerStat.getGoals()+"', Assists='"+existingPlayerStat.getAsst()+"', Goals_Saved='"+
                existingPlayerStat.getGoalsSaved()+"', Yellow_Card='"+existingPlayerStat.getYellowCards()+
                "', Red_Card='"+ existingPlayerStat.getRedCards() +"', MOM='"+existingPlayerStat.getMom()+
                "' WHERE Player_ID='"+ existingPlayerStat.getPlayerId()+"' and Team_ID='"+ teamId +"';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
