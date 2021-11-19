package com.soccerconnect.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TeamsModel extends DBConnectionApp{
    public HashMap<Integer, String> getPlayers(String teamId){
        HashMap<Integer, String> players=new HashMap<>();
        String query = "SELECT User_ID,Name from users where Role_Id='1' AND User_ID NOT IN " +
                "(SELECT Player_ID from PlayerStats where Team_ID='"+ teamId +"' UNION SELECT From_ID from " +
                "requests where To_ID='"+ teamId +"');";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                players.put(rs.getInt("User_ID"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return players;
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

    public ArrayList<String> getTeamStats(String teamId){
        ArrayList<String> teamStats = new ArrayList<>();
        String query = "SELECT * from TeamStats WHERE Team_ID='"+teamId+"';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teamStats = new ArrayList<>(Arrays.asList(rs.getString("NOM"),
                        rs.getString("Goals"), rs.getString("Wins"),
                        rs.getString("Losses"), rs.getString("Draws")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teamStats;
    }

    public HashMap<String,String> getTeamPlayers(String teamId){
        HashMap<String,String> teamPlayers = new HashMap<>();
        String query = "SELECT Player_ID,Name from PlayerStats JOIN users ON Player_ID=User_ID AND Team_ID='"+teamId+"';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teamPlayers.put(rs.getString("Player_ID"), rs.getString("Name"));
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
