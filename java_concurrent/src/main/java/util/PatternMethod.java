package util;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class PatternMethod {
    public static void arrPrint(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ", ");
            }
        }
        System.out.println();
    }


    public static void arrPrint(AtomicIntegerArray atomicIntegerArray) {
        int arrSize = atomicIntegerArray.length();
        for (int i = 0; i <arrSize; i++) {
            if (i == arrSize - 1) {
                System.out.print(atomicIntegerArray.get(i));
            } else {
                System.out.print(atomicIntegerArray.get(i) + ", ");
            }
        }
        System.out.println();
    }


    public static int getCPUNum(){
        return  Runtime.getRuntime().availableProcessors();//native方法
    }


    public static void main(String[] args) {
        System.out.println(getCPUNum());
    }
}
