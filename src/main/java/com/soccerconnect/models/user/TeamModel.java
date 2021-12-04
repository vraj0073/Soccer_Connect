package com.soccerconnect.models.user;

import com.soccerconnect.Constants;

public class TeamModel extends UserModel {
    public TeamModel(String userId, String name, String email, String mobile, String password, String category) {
        super(userId, Constants.TeamRole, email, name, mobile, password, category);
    }

    public TeamModel(String userId, String name) {
        super(userId, Constants.TeamRole, null, name, null, null, null);
    }

    public TeamModel(String userId, String name,String email,String mobile,String category) {
        super(userId, Constants.TeamRole, email, name, mobile, null, category);
    }
}
