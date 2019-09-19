package net.chat;

import java.io.IOException;
import java.net.*;

public class Client {

    public void start() {
        try {
            Socket socket = new Socket("192.168.13.112", 8899);
            new Thread(new Send(socket)).start();
            new Thread(new Receive(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Client().start();
    }
}
