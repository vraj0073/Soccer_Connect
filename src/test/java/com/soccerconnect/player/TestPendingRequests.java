package com.soccerconnect.player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.soccerconnect.database.queries.PlayerQueries;
import com.soccerconnect.database.queries.RequestsQueries;

import com.soccerconnect.models.PlayerModel;
import com.soccerconnect.models.PlayerStatsModel;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
public class TestPendingRequests{

    RequestsQueries requestQueries;

    @BeforeEach
    void setup(){
        requestQueries = new RequestsQueries();
    }

    @Test
    void TestPendingRequest() throws SQLException {

        requestQueries.conn = mock(Connection.class);
        Statement stmt = mock(Statement.class);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("teamId")).thenReturn("1");
        when(resultSetMock.getString("playerId")).thenReturn("2");
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(requestQueries.conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(resultSetMock);

        



    }
    
}


