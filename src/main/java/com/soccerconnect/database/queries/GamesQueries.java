package com.soccerconnect.database.queries;

import com.soccerconnect.database.DBConnectionApp;
import com.soccerconnect.models.GameModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GamesQueries extends DBConnectionApp {

    public void organize(String category, String team1, String team2, String ground, String date, String  time) {
        String query = "INSERT INTO games(category,team1,team2,ground,date,time) " + "VALUES ('" + category
                + "','" + team1 + "','" + team2 + "','" + ground + "','" + date + "','" + time + "');";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<GameModel> getGames() {
        ArrayList<GameModel> games= new ArrayList<>();
        String query = "SELECT * from games JOIN Grounds ON ground=Ground_ID;";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                games.add(new GameModel(rs.getString("game_ID"),
                        rs.getString("category"), rs.getString("team1"),
                        rs.getString("team2"), rs.getString("ground"),
                        rs.getString("date"), rs.getString("time")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return games;
    }

}
