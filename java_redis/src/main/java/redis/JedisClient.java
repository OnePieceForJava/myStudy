package redis;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 蜂蜜柚子茶
 * @Date 2022/6/14 20:34
 */
public class JedisClient {

    private static Socket socket;

    private static PrintWriter WRITER;
    private static BufferedReader READER;
    private static BufferedReader KEYBOARD_INPUT;

    private static final String INFO = "127.0.0.1:6379> ";

    public static void main(String[] args) throws Exception {
        try {
            //建立连接
            //虚拟机IP地址：192.168.29.128
            socket = new Socket("192.168.29.128", 6379);
            //获取输入流、输出流
            WRITER = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
            READER = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
            //键盘输入命令
            KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));
            //执行命令,同时结果解析
            execute();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            try {
                if (READER != null)
                    READER.close();
                if (WRITER != null)
                    WRITER.close();
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取键盘输入
     *
     * @return
     * @throws Exception
     */
    public static String getInput() throws Exception { // 键盘信息输入
        System.out.print(INFO);
        return KEYBOARD_INPUT.readLine();
    }

    /**
     * 执行命令
     *
     * @throws IOException
     */
    private static void execute() throws Exception {
        while (true) {
            //获取输入命令，去除首位空格
            String string = getInput().trim();
            //解析命令,去除所有空格
            String replace = string.replaceAll("\\s{1,}", "/");
            //System.out.println(replace);
            String[] strings = replace.split("/");
            //发送请求
            sendRequest(strings);
            //解析响应信息
            Object result = handleResponse();
            if (result == null) {
                System.out.println(getFormatResult("null", "warning"));
            } else if (result.toString().startsWith("ERR")) {
                System.out.println(getFormatResult(result.toString(), "error"));
            } else {
                System.out.println(getFormatResult(result.toString(), "info"));
            }
        }
    }

    /**
     * 格式化输出结果
     *
     * @param content 结果
     * @param type    类型
     * @return 格式化输出结果
     */
    private static String getFormatResult(String content, String type) {
        if (type.equals("error")) {
            return String.format("\033[%dm%s\033[0m", 31, content);
        } else if (type.equals("info")) {
            return String.format("\033[%dm%s\033[0m", 34, content);
        } else if (type.equals("warning")) {
            return String.format("\033[%dm%s\033[0m", 33, content);
        } else {
            return content;
        }
    }

    /**
     * 解析响应请求信息
     *
     * @return 解析结果
     */
    private static Object handleResponse() throws IOException {
        //五种情况读取数据
        int prefix = READER.read();
        switch (prefix) {
            case '+'://单行字符串，读取单行信息
                return READER.readLine();
            case '-'://异常信息，读取单行信息返回异常
                return READER.readLine();
            case ':'://数值类型，读取单行
                return Long.parseLong(READER.readLine());
            case '*':
                return readBulkString();
            case '$'://读取多行字符串
                int len = Integer.parseInt(READER.readLine());
                if (len == -1) {
                    return null;
                } else if (len == 0) {
                    return "";
                } else {
                    return READER.readLine();
                }
            default:
                throw new RuntimeException("错误的数据格式！");
        }
    }

    /**
     * 数组结果解析
     *
     * @return
     * @throws IOException
     */
    private static Object readBulkString() throws IOException {
        //获取数组大小
        int size = Integer.parseInt(READER.readLine());
        if (size <= 0) {
            return null;
        } else {
            List<Object> result = new ArrayList();
            for (int i = 0; i < size; i++) {
                result.add(handleResponse());
            }
            return result;
        }
    }

    /**
     * 发送请求信息
     *
     * @param args
     */
    private static void sendRequest(String... args) throws UnsupportedEncodingException {
        //本质上是命令--> set name XXXX
        WRITER.println("*" + args.length);
        for (String arg : args) {
            WRITER.println("$" + arg.getBytes("utf-8").length);
            WRITER.println(arg);
        }
        //清空缓冲区
        WRITER.flush();
    }
}