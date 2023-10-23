package com.wusp.annotationUsage.qualifier;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(30)
//@Priority(1)
public class Service2 implements IService{
    @Override
    public void print() {
        System.out.println("this is Service2");
    }
}
