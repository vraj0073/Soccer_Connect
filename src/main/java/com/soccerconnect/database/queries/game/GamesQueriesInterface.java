package com.soccerconnect.database.queries.game;

import com.soccerconnect.database.queries.user.AdminQueriesInterface;
import com.soccerconnect.models.game.GameModel;

import java.util.ArrayList;
import java.util.HashMap;

public interface GamesQueriesInterface {
    public void organize(String category, String team1, String team2, String ground, String date, String time);

    public ArrayList<GameModel> getGames(AdminQueriesInterface aq);

    public GameModel getGameDetails(String scoreGameId, AdminQueriesInterface aq);

    void addGameInfo(String gameId, String team1Goals, String team2Goals, String mom);

    public HashMap<String, String> getGameScore(String scoreGameId, AdminQueriesInterface aq);

}
