package com.soccerconnect.models;

public class PlayerModel extends UserModel {
    public PlayerModel(String userId, String name, String email , String mobile, String password, String category) {
        super(userId, "1", email, name, mobile, password, category);
    }

    public PlayerModel(String userId, String name) {
        super(userId, "1", null, name, null, null, null);
    }

    public PlayerModel(String userId, String name,String email,String mobile,String category) {
        super(userId, "1", email, name, mobile, null, category);
    }
}
