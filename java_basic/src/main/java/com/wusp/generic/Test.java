package com.wusp.generic;

import java.lang.reflect.Method;
import java.time.LocalDate;

public class Test {

    /**
     * 类型擦除带来的问题
     */
    public static void main(String[] args) {
        Dateinterval dateinterval = new Dateinterval();
        dateinterval.setFirst(LocalDate.of(2021, 8, 1));
        dateinterval.setSecond(LocalDate.of(2022, 8, 1));
        Method[] methods = dateinterval.getClass().getMethods();
        for (Method method:methods){
            //System.out.println(method.getName());
            /*if("setSecond".equals(method.getName())){
                System.out.println("=======================");
                Class<?>[] parameterTypes = method.getParameterTypes();
                for(Class<?> clazz:parameterTypes){
                    System.out.println(clazz.getName());
                }
            }*/
            if("setFirst".equals(method.getName())){
                System.out.println("=======================");
                Class<?>[] parameterTypes = method.getParameterTypes();
                for(Class<?> clazz:parameterTypes){
                    System.out.println(clazz.getName());
                }
            }
        }
    }



    /*public static void test2(){
        //Array type expected; found: 'com.wusp.generic.Pair<java.lang.String>'
        Pair<String>[] pairs = new Pair<String>()[10];
    }*/


}
