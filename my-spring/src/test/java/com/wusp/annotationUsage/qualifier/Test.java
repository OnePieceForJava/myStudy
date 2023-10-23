package com.wusp.annotationUsage.qualifier;

import com.wusp.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    /**
     * Service1类和Service2上只有@Service这个注解
     *
     * -->运行结果抛出异常
     */
    /*org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name
     'serviceConsumer': Unsatisfied dependency expressed through field 'iService'; nested exception is org
     .springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.study
     .annotationUsage.qualifier.IService' available: expected single matching bean but found 2: service1,service2*/
    @org.junit.jupiter.api.Test
    public void test1(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        applicationContext.getBean(IService.class).print();
    }



    /*
    * 执行后抛出异常，原因是@Order不能控制注入的优先级。
    * Service1类和Service2上只有@Service这个注解
    * Service1类上加上@Order(20) Service2类上加上@Order(30)  -->运行结果抛出异常
    */
    /*
    警告: Exception encountered during context initialization - cancelling refresh attempt:
    org.springframework.beans.factory.UnsatisfiedDependencyException:
    Error creating bean with name 'serviceConsumer':
     Unsatisfied dependency expressed through field 'iService';
     nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException:
     No qualifying bean of type 'com.study.annotationUsage.qualifier.IService' available:
     expected single matching bean but found 2: service1,service2
     */
    @org.junit.jupiter.api.Test
    public void test2(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        applicationContext.getBean(IService.class).print();
    }

    /**
     * Service1类和Service2上有@Service这个注解
     * Service1类上增加@Primary注解
     */
    @org.junit.jupiter.api.Test
    public void test3(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        applicationContext.getBean(IService.class).print();
    }



    /**
     * Service1类和Service2上有@Service这个注解
     * 1、Service1类上增加@Priority(20),Service2类上不增加任何注解，这里没有报错，获取到的是Service1
     * 2、Service1类上增加@Priority(20),Service2类上增加@Priority(1)，这里没有报错，获取到的是Service2
     */
    @org.junit.jupiter.api.Test
    public void test4(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        applicationContext.getBean(IService.class).print();
    }







}
