package com.wusp.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MatcherTest {

    /**
     * matches、lookingAt、find 方法
     * <p>
     * 这三个方法返回的都是boolean类型
     * lookingAt()是看字符串头部是否和模式匹配
     * matches() 则是字符串整体和模式匹配
     * find() 则是看字符串中是否和模式匹配
     */
    @Test
    public void findTest() {
        String regex = "foo";
        String input = "fooooooooooooooooo";
        String input2 = "ooooofoooooooooooo";
        Pattern pattern = Pattern.compile(regex);
        Matcher m1 = pattern.matcher(input);
        Matcher m2 = pattern.matcher(input2);
        //find()方法明明应该返回true，最下面的输出里面输出的是false。原因是什么？
        //start 和 end 方法  while(m.find()){...}给出了灵感，在Matcher里面肯定是维护了索引

        //log.info("find():{}",m1.find());
        //log.info("find():{}",m2.find());

        /*这里需要注意若想lookingAt()后，在调用find()方法是有问题了，这几个查找的方法最好不要混用*/
        //log.info("先lookingAt()->{},后find()->{}",m1.lookingAt(),m1.find());
        //log.info("先find()->{},后lookingAt->{}",m1.find(),m1.lookingAt());
        log.info("先matches()->{},后find->{}", m1.matches(), m1.find());
        log.info("lookingAt:{}, matches():{},find():{}", m1.lookingAt(), m1.matches(), m1.find());
        log.info("lookingAt:{}, matches():{},find():{}", m2.lookingAt(), m2.matches(), m1.find());
    }

    /**
     * start 和 end 方法
     * <p>
     * public void test(String[] args)
     * org.junit.runners.model.InvalidTestClassError: Invalid test class 'com.wusp.regex.MatcherTest':
     * 1. Method test should have no parameters
     * 不能有参数、不能是静态方法
     * </p>
     */
    @Test
    public void testStartAndEnd() {
        String regex = "\\bcat\\b";
        String input = "cat cat cat cattie cat";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input); // 获取 matcher 对象
        /**
         * matches():尝试将整个区域与模式匹配
         * tip:整个区域
         */
        log.info("Pattern.matches():{}", Pattern.matches(regex, input));
        log.info("m.matches():{}", m.matches());
        int count = 0;
        /*
         * find():尝试查找与该模式匹配的输入序列的下一个子序列。
         * tip:子序列
         */
        while (m.find()) {
            count++;
            log.info("--------------------------------------------");
            log.info("第{}次匹配：", count);
            //System.out.println("Match number " + count);
            log.info("index:[{},{}]", m.start(), m.end());
            //System.out.println("start(): " + m.start());
            //System.out.println("end(): " + m.end());
            log.info("input.substring({},{}):{}", m.start(), m.end(), input.substring(m.start(), m.end()));
            //if(count>1){
            //    log.info("[{},{}]",m.start(1),m.end(1));
            //}
        }
    }

    /**
     * replaceFirst 和 replaceAll 方法
     */
    @Test
    public void testReplace() {
        String regex = "dog";
        String input = "The dog says meow. " + "All dogs say meow.";
        String replace = "cat";
        log.info("orgin string->{}", input);

        Pattern p1 = Pattern.compile(regex);
        Matcher m1 = p1.matcher(input);
        log.info("cat replaceAll dog->{}", m1.replaceFirst(replace));
        Pattern p2 = Pattern.compile(regex);
        Matcher m2 = p2.matcher(input);
        log.info("cat replaceAll dog->{}", m2.replaceAll(replace));
    }

    /**
     * appendReplacement 和 appendTail 方法
     */
    @Test
    public void testTail() {
        String regex = "a*b";
        String input = "aabfooaabfooabfoobkkk";
        String replace = "-";
        log.info("orgin string->{}", input);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, replace);
            log.info("{},{},{},{}",input,m.start(),m.end(),input.substring(m.start(),m.end()));
            log.info("{}",sb.toString());
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
