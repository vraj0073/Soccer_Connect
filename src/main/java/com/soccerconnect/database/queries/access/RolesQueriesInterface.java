package com.soccerconnect.database.queries.access;

public interface RolesQueriesInterface {
    public int getRoleFromEmail(String email);
    public int getRoleFromUserId(String userId);
    public String getUserId(String email);
}
