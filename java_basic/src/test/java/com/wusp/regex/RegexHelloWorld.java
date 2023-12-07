package com.wusp.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//TODO lombok 与 java 对应版本？ 百度没有找到答案

/**
 * https://www.runoob.com/java/java-regular-expressions.html
 */
@Slf4j
public class RegexHelloWorld {

    /**
     * 测试正则表达式的匹配
     */
    @Test
    public void test() {

        /**
         * 第一步就写的有问题
         * \d表示匹配数字，但写到java的String类中是 --> "\\d"
         * ^表示匹配开头
         * \d+ 等价==> \d{1,}  匹配数字≥1次
         * \. 匹配点
         * ()? 这里有点以为，这里的?是指 (){0,1},还是说是非贪心模式，从逻辑上来说，两者的含义是相同的
         */
        //String pattern = "^\d+(\.\d+)?";
        String pattern = "^\\d+(\\.\\d+)?";

        String str1 = "5.2";
        boolean matches1 = Pattern.matches(pattern, str1);
        if (matches1) {
            log.info("【{}】,匹配", str1);
        } else {
            log.info("【{}】,不匹配", str1);
        }

        String str2 = "1000";
        boolean matches2 = Pattern.matches(pattern, str2);
        if (matches2) {
            log.info("【{}】,匹配", str2);
        } else {
            log.info("【{}】,不匹配", str2);
        }

        String str3 = "0.6.7";
        boolean matches3 = Pattern.matches(pattern, str3);
        if (matches3) {
            log.info("【{}】,匹配", str3);
        } else {
            log.info("【{}】,不匹配", str3);
        }
    }

    @Test
    public void test2() {
        String pattrn1 = "o+?";

    }

    @Test
    public void test3() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+(.*))";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        System.out.println(m.groupCount());
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }

    }
}
