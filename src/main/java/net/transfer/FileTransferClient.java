package net.transfer;

import net.chat.CloseUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * 文件传输的客户端
 */
public class FileTransferClient {
    public static void main(String[] args) {
        OutputStream outputStream = null;
        FileInputStream fis = null;
        try {
            //创建客户端对象
            Socket socket = new Socket("localhost", 8888);
            //得到写出流
            outputStream = socket.getOutputStream();
            //读取本地文件
            File file = new File("./a.txt");
            String fileName = file.getName();
            //发送文件名字长度值+文件名字
            byte[] nameBytes = fileName.getBytes();
            System.out.println(Arrays.toString(nameBytes));
            byte[] lenBytes = Integer.toBinaryString(nameBytes.length).getBytes();
            System.out.println(Arrays.toString(lenBytes));
            byte[] fileNameBytes = new byte[nameBytes.length + 10];
            System.arraycopy(lenBytes, 0, fileNameBytes, 10-lenBytes.length, lenBytes.length);
            System.arraycopy(nameBytes, 0, fileNameBytes, 10, nameBytes.length);
            System.out.println(Arrays.toString(fileNameBytes));
            outputStream.write(fileNameBytes);

            fis = new FileInputStream(file);
            //缓冲区
            byte[] buf = new byte[1024];
            int len;
            while ((len = fis.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            //强制刷新缓冲区
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeAll(outputStream, fis);
        }
    }
}
