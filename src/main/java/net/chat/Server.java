package net.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.ArrayList;

public class Server {

    private ArrayList<Channel> channels = new ArrayList<>();


    public void start() {
        try {
            System.out.println("服务器已经启动了");
            //创建服务器对象
            ServerSocket serverSocket = new ServerSocket();
            //设置服务器端口号和网络地址
            serverSocket.bind(new InetSocketAddress(3306));
            while (true) {
                //监听
                Socket socket = serverSocket.accept();
                System.out.println("连接成功");
                Channel channel = new Channel(socket);
                channels.add(channel);
                channel.sendSystemMessage("欢迎" + socket.getInetAddress() + ":" + socket.getPort() + "来到聊天室");
                new Thread(channel).start();
                System.out.println("现在有" + channels.size() + "个连接");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Channel implements Runnable {

        private DataOutputStream dos;

        private DataInputStream dis;

        private boolean isRun = true;

        private Socket socket;

        public Channel(Socket socket) {
            this.socket = socket;
            try {
                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                isRun = false;
                CloseUtil.closeAll(dos, dis);
                channels.remove(this);
                e.printStackTrace();
            }
        }

        public Socket getSocket() {
            return socket;
        }

        /**
         * 接受消息
         */
        private String receive() {
            try {
                return dis.readUTF();
            } catch (IOException e) {
                isRun = false;
                CloseUtil.closeAll(dos, dis);
                channels.remove(this);
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 发送消息
         * @param msg
         */
        private void send(String msg) {
            if (msg == null || "".equals(msg)) {
                return;
            }
            try {
                dos.writeUTF(msg);
            } catch (IOException e) {
                isRun = false;
                CloseUtil.closeAll(dos, dis);
                channels.remove(this);
                e.printStackTrace();
            }
        }

        /**
         * 发送群聊消息
         */
        private void sendMessage(String msg) {
            for (Channel channel : channels) {
                if (channel != this) {
                    channel.send(socket.getInetAddress() + ":" + socket.getPort() + "---->" + msg);
                }
            }
        }

        /**
         * 发送系统消息
         * @param msg
         */
        private void sendSystemMessage(String msg) {
            for (Channel channel : channels) {
                channel.send(msg);
            }
        }

        /**
         * 私聊
         */


        @Override
        public void run() {
            while (isRun) {
                sendMessage(receive());
            }
        }
    }


    public static void main(String[] args) {
        new Server().start();
    }

}
