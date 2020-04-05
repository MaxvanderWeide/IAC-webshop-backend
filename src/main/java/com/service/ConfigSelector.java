package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    // Authentication
    public static final String SECRET_KEY;

    public static final String PROJECT_ID;
    public static final String BUCKET;

    private ConfigSelector() {
    }

    static {
        Properties appProps = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(new File("app.properties").getAbsolutePath())) {
            appProps.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HOST = appProps.getProperty("HOST");
        PORT = appProps.getProperty("PORT") != null ? Integer.parseInt(appProps.getProperty("PORT")) : -1;
        SCHEMA = appProps.getProperty("SCHEMA");
        USERNAME = appProps.getProperty("USERNAME");
        PASSWORD = appProps.getProperty("PASSWORD");
        APIURL = appProps.getProperty("APIURL");
        SECRET_KEY = appProps.getProperty("SECRET_KEY");
        PROJECT_ID = appProps.getProperty("PROJECT_ID");
        BUCKET = appProps.getProperty("BUCKET");

        Objects.requireNonNull(HOST);
        Objects.requireNonNull(appProps.getProperty("PORT"));
        Objects.requireNonNull(SCHEMA);
        Objects.requireNonNull(USERNAME);
        Objects.requireNonNull(PASSWORD);
        Objects.requireNonNull(APIURL);
        Objects.requireNonNull(SECRET_KEY);
        Objects.requireNonNull(PROJECT_ID);
        Objects.requireNonNull(BUCKET);
    }

}
