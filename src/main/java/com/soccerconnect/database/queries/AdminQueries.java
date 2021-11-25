package com.soccerconnect.database.queries;

import com.soccerconnect.database.DBConnectionApp;
import com.soccerconnect.models.PlayerModel;
import com.soccerconnect.models.TeamModel;

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
}
