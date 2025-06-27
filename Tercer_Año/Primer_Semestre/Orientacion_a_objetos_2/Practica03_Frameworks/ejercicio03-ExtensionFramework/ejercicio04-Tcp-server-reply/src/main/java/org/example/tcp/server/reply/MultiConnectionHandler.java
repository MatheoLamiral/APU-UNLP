package org.example.tcp.server.reply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiConnectionHandler implements IConnectionHandler {
    private IMessageHandler messageHandler;
    //private EndSessionPolicy endSessionPolicy;
    


    public MultiConnectionHandler(IMessageHandler messageHandler/*, EndSessionPolicy endSessionPolicy */) {
        this.messageHandler = messageHandler;
        //this.endSessionPolicy = endSessionPolicy;
    }

    private Socket acceptAndDisplaySocket(ServerSocket serverSocket) throws IOException {
        Socket clientSocket = serverSocket.accept();
        displaySocketData(clientSocket);
        return clientSocket;
    }

    private void displaySocketData(Socket clientSocket) {
        System.out.println("Client connected from: " + clientSocket.getInetAddress().getHostAddress() + ":"
                + clientSocket.getPort());
    }

    public void handleConnection(ServerSocket serverSocket) throws IOException {
        Socket clientSocket = acceptAndDisplaySocket(serverSocket);
        new SocketWorker(clientSocket).start();

    }

    private class SocketWorker extends Thread {
        private Socket clientSocket;

        public SocketWorker(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try (
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received message: " + inputLine + " from "
                            + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());

                    if (inputLine.equalsIgnoreCase("")) {
                        break; // Client requested to close the connection
                    }
                    messageHandler.handleMessage(inputLine, out);
                }

                System.out.println("Connection closed with " + clientSocket.getInetAddress().getHostAddress() + ":"
                        + clientSocket.getPort());
            } catch (IOException e) {
                System.err.println("Problem with communication with client: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error closing socket: " + e.getMessage());
                }
            }
        }

    }

}