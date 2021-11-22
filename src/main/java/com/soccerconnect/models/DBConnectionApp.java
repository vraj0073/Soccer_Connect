package com.soccerconnect.models;

import java.sql.*;

public class DBConnectionApp {

    String HOST = "db-5308.cs.dal.ca";
    String SCHEMA = "CSCI5308_15_TEST";
    String USER = "CSCI5308_15_TEST_USER";
    String PASSWORD = "ooquiekieRoo5nah";
    String URL = "jdbc:mysql://" + HOST + ":3306/" + SCHEMA;
    Connection conn;

    public DBConnectionApp() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
