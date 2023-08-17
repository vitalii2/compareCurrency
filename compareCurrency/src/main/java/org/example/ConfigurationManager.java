package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private final String currencyConversionApiUrl;
    private final String url;
    private final String username;
    private final String password;

    private ConfigurationManager() {
        Properties prop = new Properties();
        String tempCurrencyConversionApiUrl, tempUrl, tempUsername, tempPassword;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(inputStream);
            tempCurrencyConversionApiUrl = prop.getProperty("currencyConversionApiUrl");
            tempUrl = prop.getProperty("url");
            tempUsername = prop.getProperty("username");
            tempPassword = prop.getProperty("password");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to load configuration values from properties file.");
        }
//        System.out.println(tempCurrencyConversionApiUrl);
        currencyConversionApiUrl = tempCurrencyConversionApiUrl;
        url = tempUrl;
        username = tempUsername;
        password = tempPassword;
    }

    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getApiUrl() {
        System.out.println();
        return currencyConversionApiUrl;
    }

    public String getDataBaseUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
