package redis;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class RedisClient_OriginOutput {

    private static String HOST = "192.168.42.111";
    private static int PORT = 6379;
    private static String CRTF = "\r\n";

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
            //发送一个终结符，告诉服务器，已经发送完毕
            //socket.shutdownOutput();
            //将服务器回写的数据进行翻译，并打印在控制台
            byte[] b = new byte[1024];
            inputStream.read(b);
            System.out.println(new String(b, "utf-8"));

        }
        //关闭资源
        inputStream.close();
        outputStream.close();
        socket.close();
    }




}

