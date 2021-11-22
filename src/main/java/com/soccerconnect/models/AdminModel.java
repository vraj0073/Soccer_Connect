package com.soccerconnect.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class AdminModel extends DBConnectionApp{

    public HashMap<Integer, String> getAllPlayers() {
        HashMap<Integer,String> teamPlayers = new HashMap<>();
        String query = "SELECT User_ID,Name from users where Role_Id='1';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teamPlayers.put(rs.getInt("User_ID"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teamPlayers;
    }

    public void deleteUser(String playerId) {
        String query = "DELETE FROM users WHERE User_ID='" + playerId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public HashMap<Integer, String> getAllTeams() {
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

}
