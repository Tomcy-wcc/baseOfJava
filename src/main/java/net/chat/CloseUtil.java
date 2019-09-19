package net.chat;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {
    
    public static void closeAll(Closeable... ios){
        for(Closeable io : ios){
            if (io != null){
                try {
                    io.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
