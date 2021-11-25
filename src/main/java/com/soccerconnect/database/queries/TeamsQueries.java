package com.soccerconnect.database.queries;

import com.soccerconnect.database.DBConnectionApp;
import com.soccerconnect.models.PlayerModel;
import com.soccerconnect.models.TeamStatsModel;
import com.soccerconnect.models.TeamModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeamsQueries extends DBConnectionApp {
    public ArrayList<TeamModel> getTeams(){
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

    public void acceptRequest(String playerId, String teamId){
        String query = "INSERT INTO PlayerStats (Player_ID, Team_ID) " +
                "VALUES ('" + playerId + "','" + teamId + "');";
        String deleteQuery = "DELETE FROM requests where To_ID='" + teamId + "' AND From_ID='" + playerId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.executeUpdate(deleteQuery);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void rejectRequest(String playerId, String teamId) {

        String rejectQuery = "DELETE FROM requests where To_ID='" + teamId + "' AND From_ID='" + playerId + "';";

        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(rejectQuery);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public TeamStatsModel getTeamStats(String teamId) {

        TeamStatsModel teamStats = null;
        String query = "SELECT * from TeamStats WHERE Team_ID='" + teamId + "';";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teamStats = new TeamStatsModel(teamId, rs.getString("NOM"),
                        rs.getString("Goals"), rs.getString("Wins"),
                        rs.getString("Losses"), rs.getString("Draws"), null);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teamStats;
    }

    public ArrayList<PlayerModel> getTeamPlayers(String teamId){
        ArrayList<PlayerModel> teamPlayers = new ArrayList<>();
        String query = "SELECT Player_ID,Name from PlayerStats JOIN users ON Player_ID=User_ID AND Team_ID='"+teamId+"';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teamPlayers.add(new PlayerModel(rs.getString("Player_ID"), rs.getString("Name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teamPlayers;
    }

    public void deletePlayer(String playerId, String teamId){
        String query = "DELETE FROM PlayerStats " +
                "WHERE PLAYER_ID='" + playerId + "' AND TEAM_ID='" + teamId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
}
