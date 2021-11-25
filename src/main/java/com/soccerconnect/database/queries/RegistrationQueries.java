package com.soccerconnect.database.queries;

import com.soccerconnect.database.DBConnectionApp;

import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationQueries extends DBConnectionApp {

    public void registrationQuery(String role,
                                  String email,
                                  String name,
                                  String mobile,
                                  String password,
                                  String category){

        String query = "INSERT INTO users (Name, Email_ID, Password, Phone_No, Gender, Role_ID) VALUES ('"
                + name + "','" + email + "','" + password + "','" + mobile + "','" + category + "','" + role + "');";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
