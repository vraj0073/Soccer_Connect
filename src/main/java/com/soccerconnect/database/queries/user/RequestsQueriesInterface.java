package com.soccerconnect.database.queries.user;

import java.util.HashMap;

public interface RequestsQueriesInterface {
    public HashMap<String, String> getRequests(String fromId);
    public void addRequest(String fromId, String toId);
    public HashMap<String, String> getReceivedRequests(String toId);
}
