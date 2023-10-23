package com.wusp.compare;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class Test {

    public static void main(String[] args) {
        Optional<CompareBean> minCompareBean = Arrays.asList(new CompareBean(1), new CompareBean(4),
            new CompareBean(10), new CompareBean(100)).stream().min(
            Test.comparing(compareBean -> compareBean.getOrder()));

        Function<CompareBean, Integer> function = (compareBean) -> {
            return compareBean.getOrder();
        };
        Comparator<Integer> comparator = Integer::compareTo;


    }


    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
        Function<? super T, ? extends U> keyExtractor)
    {
        Objects.requireNonNull(keyExtractor);
        //U u1 = keyExtractor.apply()
        Comparator<T> comparator = (Comparator<T> & Serializable)(c1,c2)->{
            U u1 = keyExtractor.apply(c1);
            System.out.println(u1.getClass().getName()); //java.lang.Integer
            U u2 = keyExtractor.apply(c2);
            int value = u1.compareTo(u2);
            System.out.println("u1,u2,value->{"+u1+","+u2+","+value+"}");
            return value;
        };
        return  comparator;
        //return (Comparator<T> & Serializable)
        //    (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
    }

}