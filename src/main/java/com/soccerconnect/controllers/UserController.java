package com.soccerconnect.controllers;

import com.soccerconnect.database.queries.user.*;

public abstract class UserController extends MasterController{
    PlayerQueriesInterface pq = new PlayerQueries(conn);
    TeamsQueriesInterface tq = new TeamsQueries(conn);
    RequestsQueriesInterface rqq = new RequestsQueries(conn);
}
