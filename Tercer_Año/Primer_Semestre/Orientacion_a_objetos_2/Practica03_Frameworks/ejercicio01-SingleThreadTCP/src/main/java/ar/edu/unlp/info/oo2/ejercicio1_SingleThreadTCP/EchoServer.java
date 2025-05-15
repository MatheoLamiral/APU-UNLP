package ar.edu.unlp.info.oo2.ejercicio1_SingleThreadTCP;

import java.io.PrintWriter;

public class EchoServer extends SingleThreadTCPServer {
   
    public void handleMessage(String message, PrintWriter out) {
        out.println(message);
    }

    public static void main(String[] args) {

        new EchoServer().startLoop(args);

    }

}