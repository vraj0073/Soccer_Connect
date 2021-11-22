package com.soccerconnect.models;

import java.sql.SQLException;
import java.sql.Statement;

public class AddGround extends DBConnectionApp{

    public void groundQuery(String stadiumName,  String address, String postalCode, String phone, String email) {
        String query = "INSERT INTO Grounds(Stadium_Name, Address, Postal_Code, Contact_No, Email) " + "VALUES ('" + stadiumName
                + "','" + address + "','" + postalCode + "','" + phone + "','" + email + "');";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

 
    
}
