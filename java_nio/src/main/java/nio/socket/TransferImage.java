package nio.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class TransferImage {
    @Test
    public void server() {
        try {
            ServerSocket serverSocket = new ServerSocket(8088);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            byte[] byteArray = new byte[256];
            FileOutputStream imgOutputStream = new FileOutputStream(new File("F:\\java\\02-workspace\\tmp\\zookeeper_server.jpg"));

            int readLength = inputStream.read(byteArray);
            while (readLength != -1) {
                imgOutputStream.write(byteArray, 0, readLength);
                readLength = inputStream.read(byteArray);
            }
            imgOutputStream.close();
            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void client() {
        try {
            FileInputStream pngStream = new FileInputStream(new File("F:\\java\\02-workspace\\tmp\\zookeeper.jpg"));
            byte[] byteArray = new byte[2048];
            System.out.println("socket begin " + System.currentTimeMillis());
            Socket socket = new Socket("localhost", 8088);
            System.out.println("socket end " + System.currentTimeMillis());

            OutputStream outputStream = socket.getOutputStream();
            int readLength = pngStream.read(byteArray);
            while (readLength != -1) {
                outputStream.write(byteArray, 0, readLength);
                readLength = pngStream.read(byteArray);
            }
            outputStream.close();
            pngStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
