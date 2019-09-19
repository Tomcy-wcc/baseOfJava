package net.transfer;

import net.chat.CloseUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * 传输文件服务端
 */
public class FileTransferServer {

    public static void main(String[] args) {
        InputStream inputStream = null;
        FileOutputStream fos = null;
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            byte[] fileNameBuf = new byte[1024];
            //获取消息总长度
            int len = inputStream.read(fileNameBuf);
            System.out.println(Arrays.toString(fileNameBuf));
            System.out.println(len);
            Integer fileNameLen = Integer.valueOf(new String(fileNameBuf, 0, 10).trim(), 2);
            System.out.println(fileNameLen);
            String fileName = new String(fileNameBuf, 10, fileNameLen);
            fos = new FileOutputStream("./upload/"+fileName);
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf))!=-1){
                fos.write(buf, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            CloseUtil.closeAll(inputStream, fos);
        }
    }

}
