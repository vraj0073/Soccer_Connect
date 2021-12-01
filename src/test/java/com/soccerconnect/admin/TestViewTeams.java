package com.soccerconnect.admin;

import com.soccerconnect.database.queries.AdminQueries;
import com.soccerconnect.models.TeamModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestViewTeams {

    AdminQueries aq;

    @BeforeEach
    void setup() {
        aq = new AdminQueries();
        aq.conn = mock(Connection.class);
    }

    @Test
    void TestViewAllTeams() throws SQLException {

        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("User_ID")).thenReturn("200");
        when(resultSetMock.getString("Name")).thenReturn("Avengers");
        when(resultSetMock.getString("email_id")).thenReturn("avengers@gmail.com");
        when(resultSetMock.getString("phone_no")).thenReturn("9029895678");
        when(resultSetMock.getString("gender")).thenReturn("male");
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(aq.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        ArrayList<TeamModel> expected_players = new ArrayList<>();
        expected_players.add(new TeamModel("200", "Avengers", "avengers@gmail.com",
                            "9029895678","male"));
        ArrayList<TeamModel> actual_players =  aq.getAllTeams();

        assertEquals(expected_players.size(), actual_players.size());
        for (int i = 0; i<expected_players.size(); i++){
            TeamModel expected_team = expected_players.get(i);
            TeamModel actual_team = actual_players.get(i);
            assertEquals(expected_team.getUserId(), actual_team.getUserId());
            assertEquals(expected_team.getName(), actual_team.getName());
            assertEquals(expected_team.getEmail(), actual_team.getEmail());
            assertEquals(expected_team.getMobile(), actual_team.getMobile());
            assertEquals(expected_team.getCategory(), actual_team.getCategory());
        }
    }

    @Test
    void TestViewAllTeamsEmptyData() throws SQLException {

        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(false);
        when(aq.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        ArrayList<TeamModel> actual_players =  aq.getAllTeams();
        assertEquals(0, actual_players.size());

    }

    @Test
    void TestTeamIdToName() throws SQLException {

        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("User_ID")).thenReturn("200");
        when(resultSetMock.getString("Name")).thenReturn("Avengers");
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(aq.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        HashMap<String, String> expectedTeamIdToName = new HashMap<>();
        expectedTeamIdToName.put("200", "Avengers");
        HashMap<String, String> actualTeamIdToName = aq.getTeamIdToName();

        assertEquals(expectedTeamIdToName.size(), actualTeamIdToName.size());
        assertEquals(expectedTeamIdToName, actualTeamIdToName);
    }
}
