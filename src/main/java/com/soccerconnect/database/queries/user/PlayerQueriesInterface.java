package com.soccerconnect.database.queries.user;

import com.soccerconnect.models.stats.PlayerStatsModel;
import com.soccerconnect.models.stats.TeamStatsModel;
import com.soccerconnect.models.user.PlayerModel;
import com.soccerconnect.models.user.TeamModel;

import java.util.ArrayList;

public interface PlayerQueriesInterface {
    public PlayerStatsModel getPlayerStats(String playerId);
    public ArrayList<PlayerModel> getPlayers(String teamId);
    public ArrayList<TeamStatsModel> getEachTeamStats(String playerId);
    public ArrayList<TeamModel> getPlayersTeams(String playerId);
    public void removeTeam(String teamId, String playerId);
    public void acceptRequest(String teamId, String playerId);
    public void rejectRequest(String teamId, String playerId);
    public void addPlayerStats(String playerId,String teamId);
}
