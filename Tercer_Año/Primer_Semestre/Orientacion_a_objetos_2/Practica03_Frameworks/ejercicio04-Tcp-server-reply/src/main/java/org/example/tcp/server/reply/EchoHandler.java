package org.example.tcp.server.reply;

import java.io.PrintWriter;


public class EchoHandler implements IMessageHandler {


    public void handleMessage(String message, PrintWriter out) {
        out.println(message);
    }
}