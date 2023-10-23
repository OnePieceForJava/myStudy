package com.wusp.compare;

import java.io.Serializable;
import java.util.function.Function;

public interface CompareInterfaceTest<T> /*extends Comparable<T>*/{
    int compare(T t1, T t2);

    // TODO 泛型中super 与 extends的联系与区别
    // U extends Comparable
    public static <T, U extends Comparable<? super U>> CompareInterfaceTest<T> comparing(
        Function<? super T, ? extends U> keyExtractor) {



        // 对象 -->
        CompareInterfaceTest<T> compareInterfaceTest = (t1, t2) -> {

            U u1 =  keyExtractor.apply(t1);
            U u2 =  keyExtractor.apply(t2);
            // U  extends  Comparable<? super U>

            Comparable<U> comparable = (u)->{return 0;};


            return keyExtractor.apply(t1).compareTo(keyExtractor.apply(t2));
        };






        //return  compareInterfaceTest;
        //实际上这个方法接受一个函数并返回另一个函数
        //Objects.requireNonNull(keyExtractor);
        return (CompareInterfaceTest<T> & Serializable)(c1, c2) -> keyExtractor.apply(c1).compareTo(
            keyExtractor.apply(c2));
    }



}
