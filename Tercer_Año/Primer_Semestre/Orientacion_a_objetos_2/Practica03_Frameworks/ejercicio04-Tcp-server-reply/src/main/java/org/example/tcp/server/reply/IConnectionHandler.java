package org.example.tcp.server.reply;

import java.io.IOException;
import java.net.ServerSocket;

public interface IConnectionHandler { 
    void handleConnection(ServerSocket serverSocket)  throws IOException;
}