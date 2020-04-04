package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ConfigSelector {

    // Default
    public static final String HOST;
    public static final int PORT;
    public static final String SCHEMA;
    public static final String USERNAME;
    public static final String PASSWORD;
    public static final String APIURL;

    private ConfigSelector() {
    }

    static {
        Properties appProps = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(new File("app.properties").getAbsolutePath())) {
            appProps.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HOST = appProps.getProperty("HOST");
        PORT = appProps.getProperty("PORT") != null ? Integer.parseInt(appProps.getProperty("PORT")) : -1;
        SCHEMA = appProps.getProperty("SCHEMA");
        USERNAME = appProps.getProperty("USERNAME");
        PASSWORD = appProps.getProperty("PASSWORD");
        APIURL = appProps.getProperty("APIURL");

        Objects.requireNonNull(HOST);
        Objects.requireNonNull(appProps.getProperty("PORT"));
        Objects.requireNonNull(SCHEMA);
        Objects.requireNonNull(USERNAME);
        Objects.requireNonNull(PASSWORD);
        Objects.requireNonNull(APIURL);
    }

}
