package nio.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;

public class SocketCommunication {


    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        int runTag = 1;
        while (runTag == 1) {
            Socket socket = serverSocket.accept();
            CommunicationThread processThread = new CommunicationThread(socket);
            processThread.start();
        }
        serverSocket.close();
    }

    @Test
    public void test() throws IOException {
        Socket socket = new Socket("localhost", 8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("我是中国人".getBytes());
        outputStream.close();
        socket.close();
    }

    private class CommunicationThread extends Thread {
        private Socket socket;

        public CommunicationThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);

                char[] charArray = new char[1000];
                int readLength = -1;
                while ((readLength = reader.read(charArray)) != -1) {
                    String newString = new String(charArray, 0, readLength);
                    System.out.println(newString);
                }
                reader.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 线程池的方式
     */
    public class ReadRunnable implements Runnable {
        private Socket socket;

        public ReadRunnable(Socket socket) {
            super();
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                byte[] byteArray = new byte[100];
                int readLength = inputStream.read(byteArray);
                while (readLength != -1) {
                    System.out.println(new String(byteArray, 0, readLength));
                    readLength = inputStream.read(byteArray);
                }
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class Server {
        private ServerSocket serverSocket;
        private Executor pool;

        public Server(int port, int poolSize) {
            try {
                serverSocket = new ServerSocket(port);
                pool = Executors.newFixedThreadPool(poolSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void startService() {
            try {
                for (; ; ) {
                    Socket socket = serverSocket.accept();
                    pool.execute(new ReadRunnable(socket));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
