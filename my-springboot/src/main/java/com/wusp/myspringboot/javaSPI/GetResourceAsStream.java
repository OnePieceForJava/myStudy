package com.wusp.myspringboot.javaSPI;

import java.io.InputStream;

/**
 * Class，ClassLoader的getResourceAsStream的用法
 */
public class GetResourceAsStream {
    public static void main(String[] args) {
        Class clazz = GetResourceAsStream.class;
        /**
         * Class类的getResourceAsStream的用法
         */

        /*用法一：不以"/"开头,找的是相对当前类的路径*/
        // 不以“/”开头,本质上也是调用的 ClassLoader()的getResourceAsStream方法。
        InputStream inputStream1 = clazz.getResourceAsStream("application.properties");
        System.out.println(inputStream1 == null);//true

        /*用法二：以"/"开头*/
        // 以“/”开头，则是在项目的classpath下寻找,若存在多重路径，是有“/”分隔
        //这里的classpath路径其实指的就是class文件编译后存放的路径
        InputStream inputStream2 = clazz.getResourceAsStream("/application.properties");
        System.out.println(inputStream2 == null);//fasle

        InputStream inputStream2_1 = clazz.getResourceAsStream("/dir/data.properties");
        System.out.println(inputStream2_1 == null);//false

        /**
         * ClassLoader类的getResourceAsStream的用法
         */
        InputStream inputStream3 = clazz.getClassLoader().getResourceAsStream("application.properties");
        System.out.println(inputStream3 == null);

        InputStream inputStream3_1 = clazz.getClassLoader().getResourceAsStream("dir/data.properties");
        System.out.print("inputStream3_1-->");
        System.out.println(inputStream3_1 == null);

        //ClassLoader()的getResourceAsStream方法不能以“/”开头，但也不会报错
        InputStream inputStream4 = clazz.getClassLoader().getResourceAsStream("/application.properties");
        System.out.println(inputStream4 == null);
    }
}
