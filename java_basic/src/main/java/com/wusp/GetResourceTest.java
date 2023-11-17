package com.wusp;

public class GetResourceTest {

    public static void main(String[] args) {
        classgetResource();
        classLoderGetResouce();
    }

    public static void classgetResource() {
        Class clazz = GetResourceTest.class;
        /**
         *  Class#getResource  是相对于Class类编译成*.class文件所在的路径
         */
        //file:/D:/01-projects/myStudy/java_basic/target/classes/com/wusp/
        System.out.println(clazz.getResource(""));
        //throw NullPointerException
        //System.out.println(clazz.getResource(null));

        //file:/D:/01-projects/myStudy/java_basic/target/classes/
        System.out.println(clazz.getResource("/"));
        //file:/D:/01-projects/myStudy/java_basic/target/classes/getResource.properties
        System.out.println(clazz.getResource("/getResource.properties"));

        //file:/D:/01-projects/myStudy/java_basic/target/classes/com/
        System.out.println(clazz.getResource("../"));
        //file:/D:/01-projects/myStudy/java_basic/target/classes/
        System.out.println(clazz.getResource("../../"));
        //null
        System.out.println(clazz.getResource("../../../"));

        //file:/D:/01-projects/myStudy/java_basic/target/classes/com/wusp/
        System.out.println(clazz.getResource("./"));
        System.out.println(clazz.getResource("./"));

        //file:/D:/01-projects/myStudy/java_basic/target/classes/getResource.properties
        System.out.println(clazz.getResource("../../getResource.properties"));
    }

    /**
     * getSystemResource与getResource的区别
     * public static URL getSystemResource(String name)
     * public URL getResource(String name)
     *
     *
     * 不以“/”开头
     */

    public static void classLoderGetResouce() {
        ClassLoader classLoader = GetResourceTest.class.getClassLoader();
        /**
         *  Class#getResource  是相对于Class类编译成*.class文件所在的路径
         */
        //file:/D:/01-projects/myStudy/java_basic/target/classes/
        System.out.println(classLoader.getResource(""));
        //throw NullPointerException
        //System.out.println(classLoader.getResource(null));


        //null
        System.out.println(classLoader.getResource("/"));
        //null
        System.out.println(classLoader.getResource("../"));
        //null
        System.out.println(classLoader.getResource("/getResource.properties"));
        //file:/D:/01-projects/myStudy/java_basic/target/classes/getResource.properties
        System.out.println(classLoader.getResource("./getResource.properties"));
        System.out.println(classLoader.getResource("getResource.properties"));

        //file:/D:/01-projects/myStudy/java_basic/target/classes/
        System.out.println(classLoader.getResource("./"));
        System.out.println(classLoader.getResource("."));
    }
}
