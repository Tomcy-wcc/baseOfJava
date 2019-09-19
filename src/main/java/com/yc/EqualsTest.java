package com.yc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EqualsTest {
    public static void main(String[] args) {
        Cat cat = new Cat("aaa");
        String s = "AAA";
        //System.out.println(cat.equals(s));
       // System.out.println(s.equals(cat));

        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(cat);
        System.out.println(cats.contains(s));

        Animal animal = new Animal();

        System.out.println(!(animal instanceof Cat));

        System.out.println();

    }
}

class Animal{

    public Animal(){
        System.out.println(getClass());
    }

}

class Cat extends Animal{

    private String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        /*if(obj instanceof Cat){
            return name.equalsIgnoreCase(((Cat) obj).name);
        }

        if(obj instanceof String){
            return name.equalsIgnoreCase((String) obj);
        }*/
        System.out.println(obj.getClass());
        System.out.println(getClass());
        System.out.println("---->"+(obj.getClass() == getClass()));

        return obj instanceof Cat && ((Cat) obj).name.equalsIgnoreCase(name);
    }
}
