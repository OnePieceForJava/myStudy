package util;

import sun.java2d.pipe.SpanIterator;

import java.sql.SQLOutput;
import java.util.Random;

public class BuildIntegerArray {
    /**
     * 随机数的生成
     * @param arrSize
     * @return
     */
    public static int[] buildIntegerArray(int arrSize){
        int[] arr = new int[arrSize];
        for(int i=0;i<arrSize;i++){
            Random r = new Random();
            Integer randomInteger = r.nextInt(arrSize*5);
            arr[i] = randomInteger;
        }
        System.out.println("BuildIntegerArray.buildIntegerArray生成的Integer数组-->");
        PatternMethod.arrPrint(arr);
        return arr;
    }

    public static void main(String[] args) {
        buildIntegerArray(100);
    }
}
