package com.wusp.generic;

public class ArrayAlg {

    public static Pair<String> minmax(String[] arr) {
        if (arr == null && arr.length == 0) {
            return null;
        }
        String min = arr[0];
        String max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (min.compareTo(arr[i]) > 0) { min = arr[i]; }
            if (max.compareTo(arr[i]) < 0) { max = arr[i]; }
        }
        return new Pair<>(min, max);
    }

    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }

    public static <T extends Comparable> T min(T[] a) {
        if (a == null && a.length == 0) { return null; }
        T smallest = a[0];
        for (int i = 1; i < a.length; i++) {
            if (smallest.compareTo(a[i]) > 0) {
                smallest = a[i];
            }
        }
        return smallest;
    }





    public static void main(String[] args) {

        System.out.println(ArrayAlg.getMiddle(3.12d, 50, 100).getClass().getName());//java.lang.Integer
        System.out.println(ArrayAlg.getMiddle( 50, 3.12d,100).getClass().getName());//java.lang.Double
        double middle3 =(Double)ArrayAlg.getMiddle(3.12d, 50, 100);
        System.out.println(middle3);

    }
}
