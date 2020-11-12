package com.apollo.timewreak.networking;

import com.apollo.timewreak.main.Config;

/**
 * TODO: still needs complete implementation. I.e. adding GameMaps[] and GameModes[] (if we're adding these)
 * */
public class Lobby {
    private Server server;
    private int maxPlayerCount = 0;
//    private Map[] playerMap;


    public Lobby() {

        /** getting the value from the Config and getting the host port. */
        server = new Server(Server.DefaultConfig.HOST_PORT);

        server.start();
    }

    public int getMaxPlayerCount() {
        return this.maxPlayerCount;
    }

    public void setMaxPlayerCount(int newMaxPlayerCount) {
        this.maxPlayerCount = newMaxPlayerCount;
    }


}

// what do we need in a lobby?
// 1. playerCount
// 2. GameMode / GameType
// 3. GameMap
// 4.