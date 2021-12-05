package com.soccerconnect.database.queries.user;

import com.soccerconnect.models.stats.TeamStatsModel;
import com.soccerconnect.models.user.PlayerModel;
import com.soccerconnect.models.user.TeamModel;

import java.util.ArrayList;

public interface TeamsQueriesInterface {
    public ArrayList<TeamModel> getTeams(String playerId);
    public void acceptRequest(String playerId, String teamId);
    public void rejectRequest(String playerId, String teamId);
    public TeamStatsModel getTeamStats(String teamId);
    public ArrayList<PlayerModel> getTeamPlayers(String teamId);
    public void deletePlayer(String playerId, String teamId);

}
