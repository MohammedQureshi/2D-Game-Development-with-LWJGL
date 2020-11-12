package com.apollo.timewreak.main;

public enum DevelopmentEnvironment {
    STAGING("STAGING"),
    DEVELOPMENT("DEVELOPMENT"),
    PRODUCTION("PRODUCTION");

    private final String environment;

    /** @param environment the environment you want to declare  */
    DevelopmentEnvironment(final String environment) {
        this.environment = environment;
    }

    /** @returns the current environment */
    @Override
    public String toString() {
        return environment;
    }

}
