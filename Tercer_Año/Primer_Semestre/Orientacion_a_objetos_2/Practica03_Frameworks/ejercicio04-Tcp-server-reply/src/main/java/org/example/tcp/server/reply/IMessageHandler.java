package org.example.tcp.server.reply;

import java.io.PrintWriter;

public interface IMessageHandler { 
 
    void handleMessage(String message, PrintWriter out);
    
}