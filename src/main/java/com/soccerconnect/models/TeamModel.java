package com.soccerconnect.models;

public class TeamModel extends UserModel {
    public TeamModel(String userId, String name, String email, String mobile, String password, String category) {
        super(userId, "2", email, name, mobile, password, category);
    }

    public TeamModel(String userId, String name) {
        super(userId, "1", null, name, null, null, null);
    }
}
