package com.wusp.meta.pojo;

import org.springframework.stereotype.Component;

@Component
public class AutowiredBean {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void print() {
        System.out.println("print()...");
    }
}
