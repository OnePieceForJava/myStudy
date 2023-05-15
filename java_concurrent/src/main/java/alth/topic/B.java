package alth.topic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * 2.
 * 明明的随机数
 * 明明生成了N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，
 * 把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
 *
 * 数据范围：

 * 1≤n≤1000  ，输入的数字大小满足
 * 1≤val≤500
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 32M，其他语言64M
 */
public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        HashSet<Integer> hashSet = new HashSet<>();

        for(int i=0;i<N;i++){
           if(in.hasNextInt()){
               hashSet.add(in.nextInt());
           }
        }
        sort(hashSet);
    }

    /**
     * 冒泡排序
     * @param hashSet
     */
    private static void sort(HashSet<Integer> hashSet){
        /**
         * Exception in thread "main" java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
         * 	at alth.topic.B.sort(B.java:45)
         * 	at alth.topic.B.main(B.java:37)
         */
        //Integer[] array = (Integer[]) hashSet.toArray();

        Integer[] array =  hashSet.toArray(new Integer[0]);

        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[i]>array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        for(Integer integer:array){
            System.out.println(integer);
        }

    }





}
