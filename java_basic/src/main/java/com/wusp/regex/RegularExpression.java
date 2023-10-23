package com.wusp.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

    public static void main(String[] args) {
        //example1();
        //example2();
        example3();
    }

    public static void example1() {
        System.out.println(Pattern.matches("zo*", "z"));
        System.out.println(Pattern.matches("zo+", "z"));
    }

    public static void example2() {
        // 按指定模式在字符串查找
        //String line = "This order was  placed for QT3000! OK?";
        String line = "This order was 123 placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
    }

    public static void example3() {
        String regex = "\\bcat\\b";
        String input = "cat cat cat cattie cat";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input); // 获取 matcher 对象
        int count = 0;


        char[] inputChar = input.toCharArray();
        System.out.println(input.getBytes().length - input.toCharArray().length);
        //for(char c:inputChar){
        //    System.out.println(c);
        //}

        while (m.find()) {
            count++;
            System.out.println("Match number " + count);
            System.out.println("start(): " + m.start());
            System.out.println(inputChar[m.start()]);
            System.out.println("end(): " + m.end());
            System.out.println(inputChar[m.end()-1]);
            System.out.println("-----------------------------------------------------");
        }
    }

}
