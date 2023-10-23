package nio.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * 注意点，TransferPoJo类必须要实现Serializable类，否则会报错
 */
public class TransferPoJo implements Serializable {

    @Test
    public void server() throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        for (int i = 0; i < 5; i++) {
            Userinfo userinfo = (Userinfo)objectInputStream.readObject();
            System.out.println(
                "在服务端打印" + (i + 1) + "：" + userinfo.getId() + " " + userinfo.getUsername() + " " + userinfo
                    .getPassword());
            Userinfo newUserinfo = new Userinfo();
            newUserinfo.setId(i + 1);
            newUserinfo.setUsername("serverUsername" + (i + 1));
            newUserinfo.setPassword("serverPassword" + (i + 1));
            objectOutputStream.writeObject(newUserinfo);
        }
        objectOutputStream.close();
        objectInputStream.close();
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }

    @Test
    public void client() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 8888);

        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        for (int i = 0; i < 5; i++) {
            Userinfo newUserinfo = new Userinfo();
            newUserinfo.setId(i + 1);
            newUserinfo.setUsername("clientUsername" + (i + 1));
            newUserinfo.setPassword("clientPassword" + (i + 1));
            objectOutputStream.writeObject(newUserinfo);
            Userinfo userinfo = (Userinfo)objectInputStream.readObject();
            System.out.println(
                "在客户端打印" + (i + 1) + "：" + userinfo.getId() + " " + userinfo.getUsername() + " " + userinfo
                    .getPassword());
        }
        objectOutputStream.close();
        objectInputStream.close();
        outputStream.close();
        inputStream.close();
        socket.close();
    }

    class Userinfo implements Serializable {
        private long id;
        private String username;
        private String password;

        public Userinfo() {
        }

        public Userinfo(long id, String username, String password) {
            super();
            this.id = id;
            this.username = username;
            this.password = password;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
