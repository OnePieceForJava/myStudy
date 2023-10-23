package nio.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class DataTransfer {

    @Test
    public void openServerSocket() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        //Stream.close会导致socket关闭
        //reader.close();
        //inputStreamReader.close();
        //inputStream.close();
        System.out.println("接收数据完毕...");

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("服务端已接收到纳兰性德的《木兰花令 拟古决绝词》".getBytes());
        outputStream.flush();


        //Stream.close会导致socket关闭
        reader.close();
        inputStreamReader.close();
        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();
    }

    @Test
    public void openClien() throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 12345);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("《木兰花令 拟古决绝词》\n".getBytes());
        outputStream.write("\n".getBytes());
        outputStream.write("人生若只如初见，何事秋风悲画扇？\n".getBytes());
        outputStream.write("等闲变却故人心，却道故人心易变。\n".getBytes());
        outputStream.write("骊山语罢清宵半，泪雨零铃终不怨。\n".getBytes());
        outputStream.write("何如薄倖锦衣郎，比翼连枝当日愿。\n".getBytes());
        outputStream.flush();
        //outputStream.close();
        socket.shutdownOutput();
        System.out.println("数据传输完毕...");

        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
        inputStreamReader.close();
        inputStream.close();
        socket.close();
    }

}
