package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    private static final int PORT = 8888;

    private static final String HOSTNAME = "localhost";

    public static void main(String[] args) {

        //0表示让操作系统随机分配一个端口号
        try(DatagramSocket socket = new DatagramSocket(0)){

            //设置超时时间10秒
            socket.setSoTimeout(10000);

            /*//指定发往的主机
            InetAddress host = InetAddress.getByName(HOSTNAME);

            byte[] buf = "嘿嘿".getBytes();
            //指定包发往的目的地
            DatagramPacket request = new DatagramPacket(buf, buf.length, host, PORT);

            //发送
            socket.send(request);*/

            //为接受的数据包准备空间
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);

            //接收客户端的消息
            socket.receive(response);

            //打印接收的消息
            String responseMsg = new String(response.getData(), "utf-8");
            System.out.println(response.getAddress()+"----->"+responseMsg);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
