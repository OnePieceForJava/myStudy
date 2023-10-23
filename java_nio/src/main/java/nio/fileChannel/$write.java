package nio.fileChannel;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class $write {

    private FileOutputStream fileOutputStream;
    private FileChannel fileChannel;

    @Test
    public void fileChannel_write1() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("F:\\java\\02-workspace\\tmp\\a.txt"));
        FileChannel fileChannel = fileOutputStream.getChannel();
        try {
            ByteBuffer buffer = ByteBuffer.wrap("abcde".getBytes());

            System.out.println("fileChannel.position()=" + fileChannel.position());
            System.out.println("write() 1 返回值：" + fileChannel.write(buffer));//写入abcde
            System.out.println("fileChannel.position()=" + fileChannel.position());
            fileChannel.position(2); //从byte 'c'的位置从新写
            buffer.rewind();// 注意：还原buffer的position为0
            // 然后在当前位置position中再进行写入
            System.out.println("write() 2 返回值：" + fileChannel.write(buffer));
            System.out.println("fileChannel.position()=" + fileChannel.position());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileChannel.close();
        fileOutputStream.close();
    }

    @Test
    public void fileChannel_write2() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("F:\\java\\02-workspace\\tmp\\b.txt"));
        FileChannel fileChannel = fileOutputStream.getChannel();
        try {
            ByteBuffer buffer1 = ByteBuffer.wrap("abcde".getBytes());
            ByteBuffer buffer2 = ByteBuffer.wrap("12345".getBytes());
            fileChannel.write(buffer1);//abcde
            buffer2.position(1);//2345
            buffer2.limit(3);//23
            fileChannel.position(2);//ab23e
            fileChannel.write(buffer2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileChannel.close();
        fileOutputStream.close();
    }

    @Test
    public void fileChannel_write3() throws IOException, InterruptedException {
        fileOutputStream = new FileOutputStream(new File("F:\\java\\02-workspace\\tmp\\c.txt"));
        fileChannel = fileOutputStream.getChannel();
        for (int i = 0; i < 10; i++) {
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    try {
                        ByteBuffer buffer = ByteBuffer.wrap("abcde\r\n".getBytes());
                        fileChannel.write(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    try {
                        ByteBuffer buffer = ByteBuffer.wrap("我是中国人\r\n".getBytes());
                        fileChannel.write(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
        Thread.sleep(3000);
        fileChannel.close();
        fileOutputStream.close();
    }
}
