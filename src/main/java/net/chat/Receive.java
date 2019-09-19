package net.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {

    private DataInputStream dis;

    private boolean isRun = true;

    public Receive(Socket socket) {
        try {
            dis = new DataInputStream( socket.getInputStream());
        } catch (IOException e) {
            isRun = false;
            CloseUtil.closeAll(dis);
            e.printStackTrace();
        }
    }

    public String receive() {
        try {
            return dis.readUTF();
        } catch (IOException e) {
            isRun = false;
            CloseUtil.closeAll(dis);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        while (isRun) {
            System.out.println(receive());
        }
    }
}
