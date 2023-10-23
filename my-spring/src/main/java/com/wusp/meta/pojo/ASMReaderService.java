package com.wusp.meta.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ASMReaderService{

    @Autowired
    private AutowiredBean autowiredBean;

    public void apply(){
        autowiredBean.print();
    }
}
