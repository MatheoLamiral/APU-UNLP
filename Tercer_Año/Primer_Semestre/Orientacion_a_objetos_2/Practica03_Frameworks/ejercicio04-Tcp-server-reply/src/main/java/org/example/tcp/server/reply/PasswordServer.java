package org.example.tcp.server.reply;

public class PasswordServer {
    private TCPControlLoop tcpControlLoop;

    public PasswordServer() {
        this.tcpControlLoop = new TCPControlLoop(new PasswordHandler());
    }
}
