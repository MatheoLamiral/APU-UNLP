package org.example.tcp.server.reply;

import java.io.IOException;
import java.net.ServerSocket;


public class TCPControlLoop {

    private IConnectionHandler connectionHandler;
    public TCPControlLoop() {
        this.connectionHandler = new SingleConnectionHandler();
    }
    
    public TCPControlLoop(IConnectionHandler connHandler) {
        this.connectionHandler = connHandler;
    }

    public final void startLoop(String[] args) {
        checkArguments(args);

        int portNumber = Integer.parseInt(args[0]);
        
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            displaySocketInformation(portNumber);
            while (true) {
                connectionHandler.handleConnection(serverSocket);
            }
        } catch (IOException e) {
            displayAndExit(portNumber);
        }
    }

    private void displayAndExit(int portNumber) {
        System.err.println("Could not listen on port " + portNumber);
        System.exit(-1);
    }

    private void displaySocketInformation(int portNumber) {
        System.out.println(this.getClass().getName() + " server listening on port: " + portNumber);
    }

    private void checkArguments(String[] args) {
        if (args.length != 1) {
            displayUsage();
            System.exit(1);
        }
    }

    private void displayUsage() {
        System.err.println("Usage: java"+this.getClass().getName() +"<port number>");
    }

}
