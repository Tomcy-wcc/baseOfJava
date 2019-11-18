package com.yc;

import java.util.Arrays;

public class SortTest {

    public static void selectSort(int[] a){
        for(int i = 0; i < a.length-1; i++){
            int min = i;
            for (int j = i+1; j < a.length; j++){
                if(a[j] < a[min]){
                    min = j;
                }
            }
            if(min != i){
                int t = a[min];
                a[min] = a[i];
                a[i] = t;
            }
        }
    }

    public static void bubbleSort(int[] a){
        System.out.println(Arrays.toString(a));
        for(int i = 1; i <= a.length-1; i++){
            for(int j = 0; j < a.length-i; j++){
                if(a[j] > a[j+1]){
                    int t = a[j];
                    a[j] = a[j+1];
                    a[j+1] = t;
                }

            }
            System.out.println(Arrays.toString(a));
        }
    }

    public static void main(String[] args) {
        int[] a = {5, 7, 6, 2, 1, 3, 8, 10, 4, 9};
        //selectSort(a);
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
        System.out.println("哈哈");
    }
}
