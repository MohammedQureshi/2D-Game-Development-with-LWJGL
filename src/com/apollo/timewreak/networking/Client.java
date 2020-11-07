package com.apollo.timewreak.networking;

public class Client {
    private static final int DEFAULT_TIMEOUT = 1000;
    public Client() {

    }
    public void connect() {
        connect("127.0.0.1", 2500);
    }
    public void connect(String ip, int port) {
        connect(ip, port, DEFAULT_TIMEOUT);
    }
    public void connect(String ip, int port, int timeout) {

    }

}
