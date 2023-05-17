package redis;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

public class RedisClient_FormatOutput {

    private static String HOST = "192.168.42.111";
    private static int PORT = 6379;
    private final static String CRTF = "\r\n";

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(HOST, PORT);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //返回此客户端的一个输入流
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        while (true) {
            if (socket.isConnected()) {
                System.out.print(HOST + ">");
            }

            String command = bufferedReader.readLine();

            //输入 quit,断开Socket连接
            if("QUIT".equals(command.toUpperCase())){
                break;
            }

            command.split(" ");
            String[] parameters = command.trim().replaceAll("\\s{1,}"," ").split(" ");

            //将客户要发送的信息翻译成字节文件，发送出去
            outputStream.write(("*"+parameters.length+CRTF).getBytes("utf-8"));
            for(String parameter:parameters){
                outputStream.write(("$"+parameter.length()+CRTF).getBytes("utf-8"));
                outputStream.write((parameter+CRTF).getBytes("utf-8"));
            }
            //发送一个终结符，告诉服务器，已经发送完毕,这里不用
            //socket.shutdownOutput();

            //将服务器回写的数据进行翻译，并打印在控制台
            BufferedReader responseReader =  new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            int prefix = responseReader.read();
            switch (prefix){

                case '+':  //·状态回复：在RESP中第一个字节为"+"
                    stringBuffer.append(responseReader.readLine());
                    break;
                case '-': //·错误回复：在RESP中第一个字节为"-"。
                    stringBuffer.append("(error) ");
                    stringBuffer.append(responseReader.readLine());
                    break;

                case ':':  //整数回复：在RESP中第一个字节为"："。
                    stringBuffer.append("(integer) ");
                    stringBuffer.append(responseReader.readLine());
                    break;
                case '$'://整数回复：在RESP中第一个字节为"："。
                    int strLen = Integer.valueOf(responseReader.readLine());
                    if(strLen == -1){
                      stringBuffer.append("(nil)");
                    }else{
                        stringBuffer.append(responseReader.readLine());
                    }
                    break;
                case '*'://字符串回复：在RESP中第一个字节为"$"
                    int count = Integer.valueOf(responseReader.readLine());
                    for(int i=1;i<=count;i++){
                        responseReader.read();
                        int strlen = Integer.valueOf(responseReader.readLine());
                        stringBuffer.append(i+") ");
                        if(strlen==-1){
                            stringBuffer.append("(nil)");
                        }else {
                            stringBuffer.append(responseReader.readLine());
                        }
                        stringBuffer.append("\n");
                    }
                    break;
                default:
                    throw  new RuntimeException("错误的数据格式！");
            }
            System.out.println(stringBuffer.toString().trim());
        }
        //关闭资源
        inputStream.close();
        outputStream.close();
        socket.close();
    }




}

