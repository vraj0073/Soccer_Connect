package com.soccerconnect.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
