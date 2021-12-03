package com.soccerconnect.controllers;

import com.soccerconnect.database.queries.PlayerQueries;
import com.soccerconnect.database.queries.RequestsQueries;
import com.soccerconnect.database.queries.TeamsQueries;

public abstract class UserController extends MasterController{
    PlayerQueries pq = new PlayerQueries();
    TeamsQueries tq = new TeamsQueries();
    RequestsQueries rqq = new RequestsQueries();
}
