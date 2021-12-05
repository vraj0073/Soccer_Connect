package com.soccerconnect.database.queries.ground;

import com.soccerconnect.models.ground.GroundModel;

import java.util.ArrayList;

public interface GroundsQueriesInterface {
    public void groundQuery(String groundName, String address, String postalCode, String phone, String email);

    public ArrayList<GroundModel> getAllGrounds();

    public void deleteGround(String groundId);
}
