package com.apollo.timewreak.networking;

import com.sun.javafx.util.Logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private ServerSocket serverSocket = null;
    private boolean serverIsRunning = false;
    private ServerConfig configuration;
    private Logger logger;

    /** @return true if there is a server config or not. */
    private boolean hasServerConfig() { return this.configuration != null; }

    /** @param runningStatus a boolean that states whether the server is running or not! */
    private void isRunning(boolean runningStatus) { this.serverIsRunning = runningStatus; }

    /** @param port the port you want to run the server on. */
    public Server(int port) {
        logger = Logger.getLogger(this.getClass().getName());

        if(!this.hasServerConfig()) {
            ServerConfig tmpConfig = new ServerConfig();
            tmpConfig.setProperty("port", String.valueOf(port));
            this.configuration = tmpConfig;
        }

        try {
            logger.info(String.format("Creating Server on port %d", port));
            this.serverSocket = new ServerSocket(port);
            this.isRunning(true);
        } catch (IOException e) {
            //  I/O error occurs when opening the socket.
            logger.severe(String.format("IO Exception occurred %s: ", e.getStackTrace().toString()));
        } catch (SecurityException e) {
            // we do now have permission to listen on the specified port.
            logger.severe(String.format("Security Exception occurred %s: ", e.getStackTrace().toString()));
        } catch (IllegalArgumentException e) {
            // port parameter is out of specified range of valid port values which is between 0 and 65535
            logger.severe("port parameter is out of the specified range of valid port values. Please use a value between 0 and 65535");
        }
    }

    public Server(ServerConfig serverConfig) {
        this(Integer.valueOf(serverConfig.getProperty("port")));
        this.configuration = serverConfig;
        logger.info("Running server with current configuration : " +  serverConfig.toString());
    }

    public ServerConfig getProperties() { return this.configuration; }

    /** starts the server. */
    public void start() {
        try {
            while(this.serverIsRunning) {
                // listening for a connection
                Socket clientSocket = serverSocket.accept();

                PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

                String prompt = "Hello, enter a message!";

                printWriter.println(prompt);

                logger.info(String.format("[TO Client] %s", prompt));

                BufferedReader clientInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String output = clientInputStream.readLine();

                printWriter.println(output);

                if(output.equalsIgnoreCase("exit")) {
                    printWriter.close();
                    clientSocket.close();
                }

                logger.info(String.format("[FROM Client] %s", output));

            }
        } catch (IOException e) {
            //  I/O error occurs when opening the socket.
            logger.warning(e.getMessage());
        } catch (SecurityException e) {
            // we do now have permission to listen on the specified port.
        } catch (IllegalArgumentException e) {
            // port parameter is out of specified range of valid port values which is between 0 and 65535
        }
    }

    /** stops the server. */
    public void stop() {
        logger.info("Closing the server on port " + this.configuration.getProperty("port") + "!");
        this.isRunning(false);
    }
}
