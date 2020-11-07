package com.apollo.timewreak.networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static final int DEFAULT_TIMEOUT = 1000;
    private Logger logger;
    private boolean isConnected = false;
    private String message = "";
    private Socket socket = null;
    private String uuid;

    private void setConnectionStatus(boolean connectionStatus) { this.isConnected = connectionStatus; }

    private String createRandomUUID() {
        UUID tmpUUID = UUID.randomUUID();
        return tmpUUID.toString();
    }

    public Client() {
        logger = Logger.getLogger(this.getClass().getName());
        uuid = createRandomUUID();
    }

    public String getUUID() { return this.uuid; }

    public boolean hasConnection() {
        return this.isConnected;
    }

    public void connect() {
        connect("127.0.0.1", 5432);
    }

    public void connect(String ip, int port) {
        connect(ip, port, DEFAULT_TIMEOUT);
    }

    public void connect(String ip, int port, int timeout) {
        try {
            logger.info(String.format("Connecting to: %s:%d MS Timeout: %d", ip, port, timeout));
            this.setConnectionStatus(true);

            while(hasConnection()) {
                socket = new Socket();
                socket.connect(new InetSocketAddress(InetAddress.getByName(ip), port), timeout);

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                logger.info("[FROM SERVER] " + reader.readLine());

                BufferedReader userInputBufferedReader = new BufferedReader(new InputStreamReader(System.in));
                message = userInputBufferedReader.readLine();

                writer.println(message + " uuid:" + this.getUUID());

                if("exit".equalsIgnoreCase(message)) {
                    logger.info(String.format("Disconnecting Client id %s", getUUID()));
                    writer.println("exit");
                    socket.close();
                    setConnectionStatus(false);
                }

                logger.info(String.format("[To Server]: %s", reader.readLine()));

            }
        } catch (SocketException e) {
            logger.severe(e.toString());
        } catch (Exception e) {
            logger.severe(e.toString());
        }
    }

    public void say(PrintWriter writer, String clientMessage) {
        this.message = clientMessage;
        writer.println(message);
    }
}
