package org.example;//import tcp.server.reply.TCPControlLoop;
//import tcp.server.reply.EchoHandler;

import org.example.tcp.server.reply.*;
//import tcp.server.reply.SingleConnectionHandler;

public class EchoApp  {
   

    public static void main(String[] args) {

        new TCPControlLoop(new SingleConnectionHandler(new EchoHandler())).startLoop(args);
        
    }

}