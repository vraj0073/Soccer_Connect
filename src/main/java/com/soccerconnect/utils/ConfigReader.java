package com.soccerconnect.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ConfigReader {
    InputStream inputStream;
    HashMap<String,String> configuration=new HashMap<String,String>();

    public HashMap<String,String> getPropValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "application.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            configuration.put("HOST",prop.getProperty("HOST"));
            configuration.put("SCHEMA",prop.getProperty("SCHEMA"));
            configuration.put("USER",prop.getProperty("USER"));
            configuration.put("PASSWORD",prop.getProperty("PASSWORD"));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return configuration;
    }
}
