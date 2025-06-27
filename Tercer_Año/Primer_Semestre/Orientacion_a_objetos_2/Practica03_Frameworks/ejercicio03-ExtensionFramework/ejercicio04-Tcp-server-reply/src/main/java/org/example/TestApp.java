package org.example;

import org.example.tcp.server.reply.*;

public class TestApp  {

    public static void main(String[] args) {

        new TCPControlLoop().startLoop(args);

    }
}