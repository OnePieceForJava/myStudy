package com.wusp.dynamicProxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {

    public static UserService createProxy(UserService userService) {
        UserService proxyUserService = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),
            userService.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (method.getName().equals("login") || method.getName().equals("delUsers") || method.getName()
                        .equals("selUsers")) {
                        long startTime = System.currentTimeMillis();
                        Object res = method.invoke(userService, args);
                        long endTime = System.currentTimeMillis();
                        System.out.println("耗时： " + (endTime - startTime));
                        return res;
                    }
                    return method.invoke(userService, args);
                }
            });
        return proxyUserService;
    }

    /**
     *
     * @param userService
     * @return
     */
    public static UserService createProxy2(UserService userService) {
        UserService proxyUserService = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),
            userService.getClass().getInterfaces(), (proxy, method, args) -> {
                if (method.getName().equals("login") || method.getName().equals("delUsers") || method.getName().equals(
                    "selUsers")) {
                    long startTime = System.currentTimeMillis();
                    Object res = method.invoke(userService, args);
                    long endTime = System.currentTimeMillis();
                    System.out.println("耗时： " + (endTime - startTime));
                    return res;
                }
                return method.invoke(userService, args);

            });
        return proxyUserService;
    }


    public static UserService createProxy3(UserService userService) {
       UserServiceProxyHandler handler = new UserServiceProxyHandler(userService);
        UserService proxy = (UserService)Proxy.newProxyInstance(handler.getClass().getClassLoader(),new Class[]{UserService.class},handler);
        //UserService proxy = (UserService)Proxy.newProxyInstance(handler.getClass().getClassLoader(),UserService.class.getInterfaces(),handler);
        return proxy;
    }


     static class UserServiceProxyHandler implements InvocationHandler {

        private UserService orginalObject;
        public UserServiceProxyHandler(UserService userService) {
            this.orginalObject = userService;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(proxy.getClass().getName());
            //com.sun.proxy.$Proxy0
            Object o = method.invoke(this.orginalObject,args);
            return o;
        }
    }

    /**
     * 动态代理的优点
     * <p>
     * 减少重复代码：通过动态代理，可以将一些通用的代码逻辑（如日志记录、事务管理、异常处理等）抽象出来，统一处理，从而减少代码的重复性，提高代码的可维护性和可读性。
     * 灵活性强：使用动态代理可以实现对原始对象的透明代理，从而使得代理对象和被代理对象具有相同的接口，对于调用者来说是透明的，同时也可以灵活地切换代理对象，从而实现不同的业务逻辑。
     * 可扩展性强：通过动态代理，可以在不修改原始对象的情况下，对其进行增强和扩展，从而实现更丰富的功能和更高的可复用性。
     * 提高性能：动态代理可以通过缓存等技术，减少创建代理对象的开销，从而提高系统的性能和效率。
     * 可以实现横向功能：动态代理可以通过在代理类中添加额外的功能实现横向功能（cross-cutting concerns），如安全控制、权限控制、性能监控等。
     * 总之，动态代理是一种非常灵活和可扩展的技术，可以帮助我们实
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        UserService userService = new UserServiceImpl();
        UserService proxy1 = ProxyUtil.createProxy(userService);
        UserService proxy2 = ProxyUtil.createProxy2(userService);
        UserService proxy3 = ProxyUtil.createProxy3(userService);
        proxy1.login("小明", "1234567");
        proxy2.login("小明", "1234567");
        proxy3.login("小明", "1234567");
    }
}
