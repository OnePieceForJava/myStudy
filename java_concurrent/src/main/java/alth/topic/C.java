package alth.topic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题意
 * 给你一系列数字, 从中取出三个数字, 如果给的数字不到三个有几个取几个
 * 求这几个数字拼接后的最小数字是多少。
 *
 * 比如:
 * 给定 18,123,22,5,12,34,23,43,344,21
 * 拼接后的最小数字是 12,18,5的拼接, 12185
 */
public class C {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] strs = input.split(",");
        if(strs.length==0){
            System.out.println(0);
            return;
        }else if(strs.length==1){
            System.out.println(strs[0]);
        }else if(strs.length==2){
            System.out.println(Math.min(Integer.valueOf(strs[0]+strs[1]),Integer.valueOf(strs[1]+strs[0])));
        }else{
            int[] ints = new int[strs.length];
            for(int i=0;i<strs.length;i++){
                ints[i] = Integer.valueOf(strs[i]);
            }
            Arrays.sort(ints);
            for(int i=0;i<strs.length;i++){
                System.out.println(ints[i]);
            }





        }


    }
}
