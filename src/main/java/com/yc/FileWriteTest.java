package com.yc;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class FileWriteTest {

    /**
     * 序列化对象
     *
     * @param o
     * @param des
     * @param <T>
     */
    public static <T> void serializableObject(T o, File des) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(des))) {
            objectOutputStream.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化
     *
     * @param src
     * @param <T>
     * @return
     */
    public static <T> Object deSerializableObject(File src) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(src))) {
            return objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 统计Java代码行数
     *
     * @param src
     */
    public static void countLineOfJava(File src) {

        Set<File> files = new LinkedHashSet<>();
        getJavaFile(src, files);
        int count = 0;
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file)))) {
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    count+="".equals(str) ? 0:1;
                    //count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(count);
    }

    public static void getJavaFile(File src, Set<File> javaFiles) {
        if (src.isDirectory()) {
            File[] files = src.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    if (f.getName().endsWith(".java")) {
                        javaFiles.add(f);
                    }
                } else {
                    getJavaFile(f, javaFiles);
                }
            }
        } else {
            if (src.getName().endsWith(".java")) {
                javaFiles.add(src);
            }
        }
    }

    /**
     * 文件拷贝
     *
     * @param source
     * @param des
     * @throws IOException
     */
    public static void copyFile(File source, File des) {
        try (FileReader reader = new FileReader(source);
             FileWriter writer = new FileWriter(des)) {
            char[] buf = new char[1024];
            int temp = 0;
            while ((temp = reader.read(buf)) != -1) {
                writer.write(buf, 0, temp);
            }
            System.out.println("拷贝成功");
        } catch (IOException e) {
            System.out.println("拷贝失败");
            e.printStackTrace();
        }
    }

    /**
     * 拷贝目录
     *
     * @param source 原目录的位置
     * @param des    拷贝到哪里
     */
    public static void copyDirectory(File source, File des) {
        //假如是目录
        if (source.isDirectory()) {
            //先创建一个目录对象
            File dir = new File(des, source.getName());
            //创建目录
            if (!dir.exists()) {
                dir.mkdir();
            }
            //遍历源目录
            File[] listFiles = source.listFiles();
            for (File f : listFiles) {
                if (f.isFile()) {
                    //复制文件
                    copyFile(f, new File(dir, f.getName()));
                } else {
                    copyDirectory(f, dir);
                }
            }
        } else {
            copyFile(source, new File(des, source.getName()));
        }
    }


    public static void main(String[] args) throws IOException {
        /*File sourceFile = new File("./temp");
        File desFile = new File("./test");
        //假如目标目录不存在，创建目标目录
        if (!desFile.exists()) {
            desFile.mkdir();
        }
        //copyFile(sourceFile, desFile);
        copyDirectory(sourceFile, desFile);*/
        //countLineOfJava(new File("C:\\Users\\wcc\\eclipse-workspace"));
        //C:\Users\wcc\IdeaProjects\baseOfJava\src\main\java\net
        countLineOfJava(new File("C:\\Users\\wcc\\IdeaProjects\\baseOfJava\\src\\main\\java\\offer"));

        //序列化与反序列化
        /*Point point = new Point(20, 50);
        serializableObject(point, new File("serializable" + point.getClass().getCanonicalName()));
        Point p = (Point) deSerializableObject(new File("serializable" + point.getClass().getCanonicalName()));
        System.out.println(p);*/
    }
}

