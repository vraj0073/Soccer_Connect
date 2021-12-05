package com.soccerconnect.database.queries.game;

import com.soccerconnect.database.queries.user.AdminQueriesInterface;
import com.soccerconnect.models.game.GameModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class GamesQueries implements GamesQueriesInterface {

    public Connection conn;

    public GamesQueries(Connection conn) {
        this.conn = conn;
    }

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

    public ArrayList<GameModel> getGames(AdminQueriesInterface aq) {
        ArrayList<GameModel> games= new ArrayList<>();
        HashMap<String, String> teamIdToName = aq.getTeamIdToName();
        HashMap<String, String> groundIdToName = aq.getGroundIdToName();
        String query = "SELECT * from games;";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                games.add(new GameModel(rs.getString("game_ID"),
                        rs.getString("category"), rs.getString("team1"),
                        rs.getString("team2"), rs.getString("ground"),
                        rs.getString("date"), rs.getString("time"),
                        teamIdToName.get(rs.getString("team1")),
                        teamIdToName.get(rs.getString("team2")),
                        groundIdToName.get(rs.getString("ground"))));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return games;
    }

    public GameModel getGameDetails(String scoreGameId, AdminQueriesInterface aq) {
        GameModel game = null;
        HashMap<String, String> teamIdToName = aq.getTeamIdToName();
        String query = "SELECT * from games where game_ID='" + scoreGameId + "';";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                game = new GameModel(rs.getString("game_ID"),
                        rs.getString("category"), rs.getString("team1"),
                        rs.getString("team2"), rs.getString("ground"),
                        rs.getString("date"), rs.getString("time"),
                        teamIdToName.get(rs.getString("team1")),
                        teamIdToName.get(rs.getString("team2")), null);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return game;
    }

}
