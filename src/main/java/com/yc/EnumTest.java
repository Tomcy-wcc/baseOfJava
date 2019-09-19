package com.yc;

public class EnumTest {
    public static void main(String[] args) {
        System.out.println(Food.valueOf("Apple").name());
        System.out.println(Enum.valueOf(Food.class, "Apple")==Food.valueOf("Apple"));

        Food[] foods = Food.values();
        for (Food f: foods) {
            System.out.println(f);
        }

        int a=20;
        int b=10;
        boolean flag=a++>b--&&b++>a--;

        System.out.println(flag+",a="+a+",b="+b);
    }
}

enum Food{
    Apple, Orange
}
