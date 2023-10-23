package com.wusp.dynamicProxy.jdkProxy;

public interface UserService {
    //登录
    void login(String name, String pwd) throws Exception;
    //删除
    void delUsers() throws Exception;
    //查询
    String[] selUsers() throws Exception;
}
