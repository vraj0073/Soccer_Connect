package com.soccerconnect.database.queries.access;

public interface RegistrationQueriesInterface {
    public void registrationQuery(String role,
                                  String email,
                                  String name,
                                  String mobile,
                                  String password,
                                  String category);
    public void addTeamStats(String teamId);
}
