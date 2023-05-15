package org.example;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 每个 Java 应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。
 * 可以通过 getRuntime 方法获取当前运行时。应用程序不能创建自己的 Runtime 类实例。
 * java.lang.Process
 *
 * ProcessBuilder.start() 和 Runtime.exec 方法创建一个本机进程，
 * 并返回 Process 子类的一个实例，该实例可用来控制进程并获取相关信息。
 * Process 类提供了执行从进程输入、执行输出到进程、等待进程完成、检查进程的退出状态以及销毁（杀掉）进程的方法。
 * 对于带有 Process 对象的 Java 进程，没有必要异步或并发执行由 Process 对象表示的进程。
 */
public class Test1 {

    /**
     * Java调用Windows命令测试 * @author liuyazhuang *
     */

    public static void main(String args[]) {
        testWinCmd();
        //dirOpt();
    }

    public static void testWinCmd() {
        System.out.println("------------------testWinCmd()--------------------");
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.totalMemory());
        System.out.println(runtime.freeMemory());
        System.out.println(runtime.maxMemory());
        System.out.println(runtime.availableProcessors());
        //处理器数
        try {
            //打开记事本
            runtime.exec("notepad");
            //执行一个exe文件
            //runtime.exec("C:\\Program Files\\Microsoft Office\\OFFICE11\\winword.exe C:\\Users\\chenh\\Desktop\\AOP.docx");
            //执行批处理
            //runtime.exec("c:\\x.bat");
            ////执行系统命令
            //runtime.exec("cmd /c dir ");
            //runtime.exec("cmd /c dir c:\\");
            ////
            //// -------------- 文件操作 --------------
            //runtime.exec("cmd /c copy c:\\x.bat d:\\x.txt");
            ////copy并改名
            //runtime.exec("cmd /c rename d:\\x.txt x.txt.bak");
            ////重命名
            //runtime.exec("cmd /c move d:\\x.txt.bak c:\\");
            ////移动
            //runtime.exec("cmd /c del c:\\x.txt.bak");
            ////删除 //-------------- 目录操作 --------------
            //runtime.exec("cmd /c md c:\\_test");
            //删除
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行批处理文件，并获取输出流重新输出到控制台
     */
    public static void dirOpt() {
        System.out.println("------------------dirOpt()--------------------");
        Process process;
        try {
            //执行命令
            process = Runtime.getRuntime().exec("c:\\x.bat");
            //取得命令结果的输出流
            InputStream fis = process.getInputStream();
            //用一个读输出流类去读
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            //逐行读取输出到控制台
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
