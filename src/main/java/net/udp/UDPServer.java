package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

    private static final int PORT = 8888;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)){

            while (true) {
                try {

                    //为接受的数据包准备空间
                    DatagramPacket request = new DatagramPacket(new byte[1024], 1024);

                    //接收客户端的消息
                    socket.receive(request);

                    //打印接收的消息
                    String receiveMsg = new String(request.getData(), "utf-8");
                    System.out.println(request.getAddress()+":"+request.getPort()+"----->"+receiveMsg);

                    //服务端发送消息给客户端
                    //1、准备消息数据包
                    byte[] sendMsg = "哈哈".getBytes();
                    DatagramPacket response = new DatagramPacket(sendMsg, sendMsg.length, request.getAddress(), request.getPort());
                    //2、发送数据包
                    socket.send(response);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
