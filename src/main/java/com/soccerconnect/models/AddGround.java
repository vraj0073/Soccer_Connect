package com.soccerconnect.models;

import java.sql.SQLException;
import java.sql.Statement;

public class AddGround extends DBConnectionApp{

    public void groundQuery(String stadiumName, String stadiumId,  String address, String managerName, String phone, String email, String capacity, String capacity2) {
        String query = "INSERT INTO Grounds(Stadium_Name,Stadium_ID, Address, Manager_Name, Contact_No, Email, Capacity) " + "VALUES ('" + stadiumName
                + "','" + stadiumId + "','" + address + "','" + managerName + "','" + phone + "','" + email + "','" + capacity + "');";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

 
    
}
