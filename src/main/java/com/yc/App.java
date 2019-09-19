package com.yc;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Hello world!
 */
public class App {
    /**
     * 统计一个目录下的Java文件与class文件
     *
     * @param file
     * @param count
     */
    public static void countFile(File file, int[] count) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    String fName = f.getName();
                    System.out.println(f.getPath());
                    if (fName.endsWith(".java")) {
                        count[0]++;
                    } else if (fName.endsWith(".class")) {
                        count[1]++;
                    }
                } else {
                    countFile(f, count);
                }
            }
        } else {
            String fName = file.getName();
            if (fName.endsWith(".java")) {
                count[0]++;
            } else if (fName.endsWith(".class")) {
                count[1]++;
            }
        }
    }

    public static void filterFile(String dir) {
        File file = new File(dir);
        File[] files = file.listFiles(f -> f.getName().matches(".*\\d+.*"));
        for (File f : files) {
            System.out.println(f.getName());
        }
    }

    public static void main(String[] args) {
        /*int[] count = new int[2];
        countFile(new File("../"), count);
        System.out.println(Arrays.toString(count));*/
        //filterFile("../");
        //System.out.println(Integer.toBinaryString(-11));
        ConcurrentLinkedQueue<Integer> linkedQueue = new ConcurrentLinkedQueue<>();
        linkedQueue.offer(3);
        linkedQueue.offer(4);
        linkedQueue.offer(5);
    }
}