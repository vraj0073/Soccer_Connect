package com.soccerconnect.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class AddGround extends DBConnectionApp{

    public void groundQuery(String groundName,  String address, String postalCode, String phone, String email) {
        String query = "INSERT INTO Grounds(Ground_Name, Address, Postal_Code, Contact_No, Email) " + "VALUES ('" + groundName
                + "','" + address + "','" + postalCode + "','" + phone + "','" + email + "');";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public HashMap<Integer, String> getAllGrounds() {
        HashMap<Integer, String> grounds=new HashMap<>();
        String query = "SELECT Ground_ID,Ground_Name from Grounds;";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                grounds.put(rs.getInt("Ground_ID"), rs.getString("Ground_Name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return grounds;
    }
}
