package com.yc;

public class SingletonTest {
    public static void main(String[] args) {
        for (int i = 1; i <= 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //System.out.println(Thread.currentThread().getName()+"--->"+Person.getInstance().hashCode());
                    System.out.println(Thread.currentThread().getName() + "--->" + Dog.INSTANCE.hashCode());
                }
            }).start();
        }
    }
}

enum Dog {
    INSTANCE;
}

class Person {

    private static volatile Person person;

    private Person() {

    }

    public static Person getInstance() {
        if (person == null) {
            synchronized (Person.class) {
                if (person == null) {
                    person = new Person();
                }
            }
        }
        return person;
    }
}
