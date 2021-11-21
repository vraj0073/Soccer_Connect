package com.soccerconnect.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PlayerModel extends DBConnectionApp{

    public HashMap<Integer, String> getTeams(){
        HashMap<Integer, String> teams=new HashMap<>();
        String query = "SELECT User_ID,Name from users where Role_Id='2';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                teams.put(rs.getInt("User_ID"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teams;
    }

    public HashMap<String, String> getPlayerStats(String id){
        System.out.println("id: "+id);
        HashMap<String, String> playerStats=new HashMap<>();
        String query = "SELECT sum(goals) as totalGoals,sum(assists) as totalAssists,sum(goals_saved) as totalGoalsSaved," +
                "sum(yellow_Card) as totalYellowCard,sum(red_card) as totalRedCard,sum(MOM) as totalMOM, sum(NOM) as totalNOM" +
                " from PlayerStats where player_id ="+id +";";
        String query2 = "SELECT Name from users where user_id ="+id +";";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query2);
            rs.next();
            rs2.next();
            playerStats.put("Name",rs2.getString(1));
            playerStats.put("Goals",rs.getString(1));
            playerStats.put("Assists",rs.getString(2));
            playerStats.put("Goals saved",rs.getString(3));
            playerStats.put("Yellow cards",rs.getString(4));
            playerStats.put("Red cards",rs.getString(5));
            playerStats.put("Man of the match",rs.getString(6));
            playerStats.put("No. of matches",rs.getString(7));

        } catch (SQLException e) {
            System.out.println(e);
        }
        return playerStats;
    }

    public HashMap<String, ArrayList<String>> getEachTeamStats(String id){
        HashMap<String, ArrayList<String>> teamStats=new HashMap<>();
        ArrayList<String> teamStatsList = new ArrayList<>();

        String query = "SELECT TeamStats.Team_ID, TeamStats.NOM, TeamStats.Goals, Wins, Losses, Draws " +
                "FROM TeamStats INNER JOIN PlayerStats ON PlayerStats.Team_ID=TeamStats.Team_ID where PlayerStats.Player_ID = "+ id + ";";

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                teamStatsList = new ArrayList<>(Arrays.asList(rs.getString("NOM"),
                        rs.getString("Goals"), rs.getString("Wins"),
                        rs.getString("Losses"), rs.getString("Draws")));

                String query2 = "SELECT Name from users where user_id ="+rs.getString("Team_ID") +";";
                Statement stmt2 = conn.createStatement();
                ResultSet rs2 = stmt2.executeQuery(query2);
                rs2.next();

                teamStats.put(rs2.getString(1),teamStatsList);
            }


        } catch (SQLException e) {
            System.out.println(e);
        }
        return teamStats;
    }

    public HashMap<String, String> getPlayersTeam(String id){
        HashMap<String,String> playerTeams = new HashMap<>();
        String query = "SELECT Player_ID,Team_id,users.Name from PlayerStats JOIN users ON Team_id=User_ID AND Player_ID='"+id+"';";

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                playerTeams.put(rs.getString("Team_id"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return playerTeams;
    }

    public void removeTeam(String teamId, String playerId){
        String query = "DELETE FROM PlayerStats " +
                "WHERE PLAYER_ID='" + playerId + "' AND TEAM_ID='" + teamId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
