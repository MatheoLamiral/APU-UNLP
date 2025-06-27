package org.example;

import org.example.tcp.server.reply.*;

public class MultiEchoApp  {

    public static void main(String[] args) {

        new TCPControlLoop(new MultiConnectionHandler(new EchoHandler())).startLoop(args);
    }
}
