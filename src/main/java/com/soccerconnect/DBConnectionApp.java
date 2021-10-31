package com.soccerconnect;

import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionApp {

    String HOST = "db-5308.cs.dal.ca";
    String SCHEMA = "CSCI5308_15_TEST";
    String USER = "CSCI5308_15_TEST_USER";
    String PASSWORD = "ooquiekieRoo5nah";
    String URL = "jdbc:mysql://" + HOST + ":3306/" + SCHEMA;
    Connection conn;

    public DBConnectionApp(){
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public void runQuery(String query){
//        String query = "SELECT * FROM roles";
        try{
             Statement stmt = conn.createStatement();
             stmt.executeQuery(query);

//            while (rs.next()) {
//                int roleId = rs.getInt("Role_ID");
//                String roleName = rs.getString("Role_Name");
//                System.out.println("Role ID: " + roleId + " , Role Name: " + roleName);
//            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void registrationQuery(String role,
                                  String email,
                                  String name,
                                  String mobile,
                                  String password,
                                  String category){

        String query = "INSERT INTO users (Name, Email_ID, Password, Phone_No, Gender, Role_ID) " +
                "VALUES ('"
                + name + "','" + email + "','" + password + "','" + mobile + "','" + category + "','" + role + "');";
        System.out.println(query);
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args){
        DBConnectionApp db = new DBConnectionApp();
        String query = "SELECT * FROM roles";
        db.runQuery(query);
    }

}
