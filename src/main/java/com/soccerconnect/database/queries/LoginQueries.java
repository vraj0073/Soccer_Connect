package com.soccerconnect.database.queries;

import com.soccerconnect.database.DBConnectionApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginQueries extends DBConnectionApp {
    public String getPasswordFromEmail(String email){
        String password = "";
        String query = "SELECT Password from users where Email_ID='" + email + "';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                password = rs.getString("Password");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return password;
    }
}
