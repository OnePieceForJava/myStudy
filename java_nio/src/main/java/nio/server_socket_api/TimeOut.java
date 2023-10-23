package nio.server_socket_api;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.Test;

public class TimeOut {

    /**
     * 0
     *
     * java.net.SocketTimeoutException: Accept timed out
     *
     * 	at java.net.DualStackPlainSocketImpl.waitForNewConnection(Native Method)
     * 	at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:131)
     * 	at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:535)
     * 	at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:189)
     * 	at java.net.ServerSocket.implAccept(ServerSocket.java:545)
     * 	at java.net.ServerSocket.accept(ServerSocket.java:513)
     * 	at nio.server_socket_api.TimeOut.test1(TimeOut.java:16)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.lang.reflect.Method.invoke(Method.java:498)
     * 	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
     * 	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     * 	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
     * 	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     * 	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
     * 	at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
     * 	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
     * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
     * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
     * 	at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
     * 	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
     * 	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
     * 	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
     * 	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
     * 	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
     * 	at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
     * 	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     * 	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
     * 	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
     * 	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
     * 	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        //返回0意味
        //着禁用了选项（即无穷大的超时值） SO_TIMEOUT
        System.out.println(serverSocket.getSoTimeout());
        //accept设置了10s的超时时间
        serverSocket.setSoTimeout(10000);
        serverSocket.accept();
    }
}
