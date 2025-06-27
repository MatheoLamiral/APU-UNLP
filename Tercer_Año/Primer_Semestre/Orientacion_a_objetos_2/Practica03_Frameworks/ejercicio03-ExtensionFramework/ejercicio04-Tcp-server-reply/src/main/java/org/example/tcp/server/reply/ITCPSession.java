package org.example.tcp.server.reply;

import java.net.Socket;

public interface ITCPSession { 
    void handleConnection(Socket clientSocket);
}