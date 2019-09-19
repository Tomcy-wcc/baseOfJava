package com.yc;

import java.io.*;

public class SerializableUtil<T>{

    /**
     * 序列化对象
     *
     * @param o
     * @param des
     */
    public void serializableObject(T o, File des) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(des))) {
            objectOutputStream.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化
     * @param src
     * @return
     */
    public T deSerializableObject(File src) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(src))) {
            return (T)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        //Point point = new Point(20, 30);
        //SerializableUtil<Point> serializableUtil = new SerializableUtil<>();
        //serializableUtil.serializableObject(point, new File("serializable" + point.getClass().getCanonicalName()));
        //Point p = serializableUtil.deSerializableObject(new File("serializable" + Point.class.getName()));
        //System.out.println(p);
        Point p = new Point(1, 2, new A(2));
        Object clone = p.clone();
        System.out.println(clone);
    }
}

class A{
    int i = 1;

    public A(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "A{" +
                "i=" + i +
                '}';
    }
}

class Point implements Serializable, Cloneable{

    private static final long serialVersionUID = -123123123131L;

    private int x;

    private int y;

    private A a;

    //transient int z;

    public Point(int x, int y, A a) {
        this.x = x;
        this.a = a;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", a=" + a +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
