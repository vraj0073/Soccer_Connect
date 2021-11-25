package com.soccerconnect.database.queries;

import com.soccerconnect.database.DBConnectionApp;
import com.soccerconnect.models.GroundModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroundQueries extends DBConnectionApp {

    public void groundQuery(String groundName,  String address, String postalCode, String phone, String email) {
        String query = "INSERT INTO Grounds(Ground_Name, Address, Postal_Code, Contact_No, Email) " + "VALUES ('"
                + groundName + "','" + address + "','" + postalCode + "','" + phone + "','" + email + "');";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public ArrayList<GroundModel> getAllGrounds() {
        ArrayList<GroundModel> grounds=new ArrayList<>();
        String query = "SELECT Ground_ID,Ground_Name from Grounds;";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                grounds.add(new GroundModel(rs.getString("Ground_ID"),
                        rs.getString("Ground_Name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return grounds;
    }

    public void deleteGround(String groundId) {
        String query = "DELETE FROM Grounds WHERE Ground_ID='" + groundId + "';";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
