package ar.edu.unlp.info.oo2.ejercicio1_SingleThreadTCP;

import java.io.PrintWriter;
import java.net.Socket;

public class VoidServer extends SingleThreadTCPServer {

    public void handleMessage(String message, PrintWriter out) {

    }

    protected void displaySocketData(Socket clientSocket) {
        System.out.println("Client connected from: " + clientSocket.getInetAddress().getHostAddress() + ":"
                + clientSocket.getPort());
        displayWarning();
    }

    protected void displayUsage() {
        System.err.println("Usage: java" + this.getClass().getName() + "<port number>");
        displayWarning();
    }

    protected void displaySocketInformation(int portNumber) {
        System.out.println(this.getClass().getName() + " server listening on port: " + portNumber);
        displayWarning();
    }

    private void displayWarning() {
        System.err.println("--This is the VoidServer. It will do nothing for you--");
    }

    public static void main(String[] args) {

        new VoidServer().startLoop(args);

    }
}