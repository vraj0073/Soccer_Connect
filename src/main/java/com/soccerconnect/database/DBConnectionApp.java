package com.soccerconnect.database;

import com.soccerconnect.utils.ConfigReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class DBConnectionApp {
    
    public Connection conn;

    public DBConnectionApp() {
        try {
            ConfigReader properties = new ConfigReader();
            HashMap<String,String> configuration = properties.getPropValues();
            String HOST = configuration.get("HOST");
            String SCHEMA = configuration.get("SCHEMA");
            String USER = configuration.get("USER");
            String PASSWORD = configuration.get("PASSWORD");
            String URL = "jdbc:mysql://" + HOST + ":3306/" + SCHEMA;
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | IOException e) {
            System.out.println(e);
        }
    }
}
