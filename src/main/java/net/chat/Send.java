package net.chat;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Send implements Runnable {

    private Socket socket;

    private Scanner scanner;

    private DataOutputStream dos;

    private boolean isRun = true;

    public Send(Socket socket){
        this.socket = socket;
        try {
            scanner = new Scanner(System.in);
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            isRun = false;
            CloseUtil.closeAll(dos, scanner);
            e.printStackTrace();
        }
    }

    public String inputMsg(){
        return scanner.nextLine();
    }

    public void send(String msg){
        if("".equals(msg)){
            return;
        }
        try {
            if(msg.equals("exit")){
                socket.close();
            }
            dos.writeUTF(msg);
        } catch (IOException e) {
            isRun = false;
            CloseUtil.closeAll(scanner, dos);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (isRun){
            send(inputMsg());
        }
    }
}
