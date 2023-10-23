package com.wusp.annotationUsage.qualifier;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(20)
//@Primary
//@Priority(20)
//@Qualifier
public class Service1 implements IService{
    @Override
    public void print() {
        System.out.println("this is Service1");
    }
}
