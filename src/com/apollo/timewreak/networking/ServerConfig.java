package com.apollo.timewreak.networking;

import java.util.HashMap;
import java.util.Properties;

public class ServerConfig {
    private Properties properties;

    private void setDefaultsPropertiesAndValues() {
        this.properties.setProperty("port", "5432");
        this.properties.setProperty("ip", "127.0.0.1");
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

    public String getProperty(String propertyName) {
        return this.properties.getProperty(propertyName);
    }

    @Override
    public String toString() {
        return this.properties.toString();
    }
}

