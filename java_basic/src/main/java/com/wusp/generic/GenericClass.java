package com.wusp.generic;

import java.io.Serializable;
import java.time.LocalDate;

public class GenericClass<T,P extends Comparable<?> & Serializable> {

    public static void main(String[] args) {
        GenericClass<LocalDate,String> a = new GenericClass<>();
    }


    public void genericMethod(T t, P p) {
        System.out.println(t.getClass().getName());
        System.out.println(p.getClass().getName());
    }


}
