package com.wusp.generic;

/**
 * 泛型通配符 测试类
 */
public class GenericSymbolTest {


    public static void printBuddies(Pair<Employee> p){
        Employee first= p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    public static <T extends Employee> void printBuddies2(Pair<T> p){
        Employee first= p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    public static void printBuddies3(Pair<? extends Employee> p){
        Employee first= p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }



    public static void printBuddies4(Pair<? super Manager> p){
        //Manager first= p.getFirst();
        //Manager second = p.getSecond();
        p.setFirst(new Manager("cfo"));
        Object o = p.getFirst();
        //p.setFirst(new Employee("dev1"));
        //System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    public static void main(String[] args) {
        Pair<Manager> pair = new Pair<>(new Manager("first"),new Manager("second"));
        //GenericSymbolTest.printBuddies(pair);
        //GenericSymbolTest.printBuddies2(pair);

        //? extends Employee
        // Employee类及其子类
        Pair<Employee> pair1 = new Pair<>(new Employee("first"),new Employee("second"));
        GenericSymbolTest.printBuddies3(pair);


        Pair<Manager> pair3 = new Pair<>(new Manager("cfo"),new Manager("ceo"));
        Pair<? extends Employee> pair4 = pair3;
       /* pair4.setFirst(new Employee("dev"));
        pair4.setFirst(new Manager("uat"));*/
    }


    public static boolean hasNulls(Pair<?> pair){
        return pair.getFirst()==null || pair.getSecond()==null;
    }
}
