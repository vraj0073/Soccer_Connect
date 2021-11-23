package com.soccerconnect.models;

import java.sql.SQLException;
import java.sql.Statement;

public class Organize extends DBConnectionApp{


    public void organize(String category, String team1, String team2, String ground, String date, String  time) {
        String query = "INSERT INTO games(category,team1,team2,stadium,date,time) " + "VALUES ('" + category
                + "','" + team1 + "','" + team2 + "','" + ground + "','" + date + "','" + time + "');";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
