package com.soccerconnect.database.queries.game;

import com.soccerconnect.database.queries.user.AdminQueriesInterface;
import com.soccerconnect.models.game.GameModel;

import java.util.ArrayList;

public interface GamesQueriesInterface {
    public void organize(String category, String team1, String team2, String ground, String date, String time);

    public ArrayList<GameModel> getGames(AdminQueriesInterface aq);

    public GameModel getGameDetails(String scoreGameId, AdminQueriesInterface aq);
}
