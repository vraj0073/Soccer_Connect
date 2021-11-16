package com.soccerconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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

    public void registrationQuery(String role,
                                  String email,
                                  String name,
                                  String mobile,
                                  String password,
                                  String category){

        String query = "INSERT INTO users (Name, Email_ID, Password, Phone_No, Gender, Role_ID) " +
                "VALUES ('"
                + name + "','" + email + "','" + password + "','" + mobile + "','" + category + "','" + role + "');";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getRoleFromEmail(String email){
        int roleID = -1;
        String query = "SELECT Role_ID from users where Email_ID='" + email + "';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                roleID = rs.getInt("Role_ID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return roleID;
    }

    public int getRoleFromUserId(String userId){
        int roleID = -1;
        String query = "SELECT Role_ID from users where User_ID='" + userId + "';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                roleID = rs.getInt("Role_ID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return roleID;
    }

    public HashMap<Integer, String> getTeams(){
        HashMap<Integer, String> teams=new HashMap<>();
        String query = "SELECT User_ID,Name from users where Role_Id='2';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                teams.put(rs.getInt("User_ID"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teams;
    }

    public String getUserId(String email){
        String userID = "-1";
        String query = "SELECT User_ID from users where Email_ID='" + email + "';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                userID = rs.getString("User_ID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userID;
    }

    public void addRequest(String fromId, String toId){
        String query = "INSERT INTO requests (From_ID, To_ID, Status) " +
                "VALUES ('" + fromId + "','" + toId + "','Request Sent');";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public HashMap<String, String> getRequests(String fromId){
        HashMap<String, String> teams=new HashMap<>();
        String query = "SELECT Name,Status from requests JOIN users ON To_ID=User_ID AND From_ID='"+fromId+"';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teams.put(rs.getString("Name"), rs.getString("Status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teams;
    }

    public HashMap<Integer, String> getPlayers(){
        HashMap<Integer, String> players=new HashMap<>();
        String query = "SELECT User_ID,Name from users where Role_Id='1';";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                players.put(rs.getInt("User_ID"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return players;
    }

    public void groundQuery(String stadiumName, String location, String manageName, String capacity) {
        String query = "INSERT INTO users (Stadium_name, Location , Manager_Name, Capacity) " +
                "VALUES ('"
                + stadiumName + "','" + location + "','" + manageName + "','" + capacity + "');";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
