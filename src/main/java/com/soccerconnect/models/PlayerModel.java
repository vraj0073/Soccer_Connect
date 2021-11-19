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

}
