package com.soccerconnect.models;



import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class Organize extends DBConnectionApp{


    public void organize(String category, String Team1, String Team2, String stadium, String date, String  Time) {
        String query = "INSERT INTO organize(category,Team1,Team2,stadium,date,Time) " + "VALUES ('" + category
                + "','" + Team1 + "','" + Team2 + "','" + stadium + "','" + date + "','" + Time + "');";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
