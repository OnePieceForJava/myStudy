package com.wusp.dynamicProxy.jdkProxy;

public class UserServiceImpl implements UserService{
    @Override
    public void login(String name, String pwd) throws Exception {
        System.out.println("登录成功...");
    }

    @Override
    public void delUsers() throws Exception {
        System.out.println("删除用户...");
    }

    @Override
    public String[] selUsers() throws Exception {
        System.out.println("查询用户...");
        return new String[0];
    }
}
