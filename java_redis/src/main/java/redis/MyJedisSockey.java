package redis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyJedisSockey {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.42.111",6379);
            //Socket socket = new Socket("127.0.0.1",6380);
            System.out.println("isConnected-->"+socket.isConnected());
            OutputStream outputStream = socket.getOutputStream();
            String commadStr = "*3\r\n$3\r\nset\r\n$5\r\nhello\r\n$5\r\nworld";
            //将客户要发送的信息翻译成字节文件，发送出去
            outputStream.write(commadStr.getBytes());
            outputStream.flush();
            //发送一个终结符，告诉服务器，已经发送完毕
            socket.shutdownOutput();



            //返回此客户端的一个输入流
            InputStream inputStream = socket.getInputStream();
            //将服务器回写的数据进行翻译，并打印在控制台
            byte[] b = new byte[1024];
            inputStream.read(b);
            System.out.println(new String(b, "utf-8"));
           /* int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {

            }*/
            //关闭资源
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
