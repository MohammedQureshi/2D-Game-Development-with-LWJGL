package com.apollo.timewreak.networking;

import java.util.HashMap;
import java.util.Properties;

public class ServerConfig {
    private Properties properties;

    private void setDefaultsPropertiesAndValues() {
        this.setProperty("port", Server.DefaultConfig.HOST_PORT);
        this.setProperty("ip", "127.0.0.1");
        this.setProperty("maxPlayers", Server.DefaultConfig.MAX_PLAYER_COUNT);
    }

    public ServerConfig() {
        this.properties = new Properties();
        setDefaultsPropertiesAndValues();
    }
    
    public ServerConfig(Properties properties) {
        this.properties = properties;
    }

    public Object setProperty(String name, String value) {
        return this.properties.setProperty(name, value);
    }

    public Object setProperty(String name, int value) {
        return this.setProperty(name, Integer.toString(value));
    }


    public String getProperty(String propertyName) {
        return this.properties.getProperty(propertyName);
    }

    @Override
    public String toString() {
        return this.properties.toString();
    }
}

