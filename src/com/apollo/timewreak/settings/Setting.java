package com.apollo.timewreak.settings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public abstract class Setting {
    /** The hashtable which will contain all of keys */
    private Properties settings = new Properties();
    private boolean isSavable = false;

    /**
     * @param defaults the default properties and values you want your base class to contain.
     */
    protected void setDefaults(Properties defaults) {
        this.settings = defaults;
    }

    protected void setProperty(String property, String value) {
        this.settings.setProperty(property, value);
    }

    protected void setProperty(String property, boolean value) {
        this.setProperty(property, String.valueOf(value));
    }

    protected void setProperty(String property, int value) {
        this.setProperty(property, String.valueOf(value));
    }

    protected boolean canSave() { return this.isSavable; }

    protected void setSavable(boolean saveStatus) { this.isSavable = saveStatus; }

    /**
     * @param property the property you're looking for
     * @return True if the {@link java.util.Properties Properties} hashTable contains current looking for property, else false.
     */
    public boolean hasProperty(String property) {
        return settings.containsKey(property);
    }

    /**
     * Sets the value of a property, if the property is found.
     * @param property the property you want to set the value of
     * @param value the value you want to set the property to
     */
    public void setPropertyIfFound(String property, String value) {
        if(this.hasProperty(property)) {
            this.setProperty(property, value);
        }
    }

    /**
     *  See {@link #setPropertyIfFound(String, String)} for usage
     *  Only difference is you can pass an int as a value, and it converts it to a String!
     */
    public void setPropertyIfFound(String property, int value) {
        this.setPropertyIfFound(property, String.valueOf(value));
    }

    public void setPropertyIfFound(String property, boolean value) {
        this.setPropertyIfFound(property, String.valueOf(value));
    }

    public String getProperty(String property) {
        return this.settings.getProperty(property);
    }

    public Set<Object> getAllProperties() {
        return this.settings.keySet();
    }
    
    // TODO: implement a proper IO Adapter Class / Wrapper
    public void save(){
        if(this.canSave()) {
            try {
                PrintWriter outputFile = new PrintWriter(this.getClass().getSimpleName() + ".cfg");
                for(Map.Entry<Object, Object> entry: this.settings.entrySet()) {
                    outputFile.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
                }
                outputFile.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
