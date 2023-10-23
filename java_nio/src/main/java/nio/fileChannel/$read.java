package nio.fileChannel;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class $read {

    @Test
    public void read1() throws IOException {
        FileInputStream fisRef = new FileInputStream(new File("F:\\java\\02-workspace\\tmp\\read1.txt"));
        FileChannel fileChannel = fisRef.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);

        int readLength = fileChannel.read(byteBuffer);
        System.out.println("---------------------------------");
        System.out.println(readLength);     // 取得5个字节
        System.out.println(new String(byteBuffer.array()));
        // 将下面的代码添加注释，那么再次执行read()方法时，
        // 返回值是0，因为byteBuffer没有remaining剩余空间
        // byteBuffer.clear();
        System.out.println("---------------------------------");
        readLength = fileChannel.read(byteBuffer);
        System.out.println(readLength);     // 取得0个字节
        System.out.println(new String(byteBuffer.array()));

        System.out.println("---------------------------------");
        // 执行clear()方法，使缓冲区状态还原,若未重置会导致数据无法读到缓存区
        byteBuffer.clear();
        readLength = fileChannel.read(byteBuffer);
        System.out.println(readLength);     // 到达流的末尾值为-1
        System.out.println(new String(byteBuffer.array()));
        System.out.println("---------------------------------");

        byteBuffer.clear();

        while ((readLength = fileChannel.read(byteBuffer)) != -1) {
            System.out.println(readLength);     // 到达流的末尾值为-1
            System.out.println(new String(byteBuffer.array()));
            byteBuffer.clear();
        }
        System.out.println(readLength);
        fileChannel.close();
        fisRef.close();
    }

    @Test
    public void read2() throws Exception {
        FileInputStream fisRef = new FileInputStream(new File("F:\\java\\02-workspace\\tmp\\read2.txt"));
        FileChannel fileChannel = fisRef.getChannel();
        fileChannel.position(2);
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        System.out.println(fileChannel.read(byteBuffer));

        byte[] getByteArray = byteBuffer.array();
        for (int i = 0; i < getByteArray.length; i++) {
            System.out.print((char)getByteArray[i]);
        }
        fileChannel.close();
        fisRef.close();
    }

    @Test
    public void read3() throws Exception {
        // abcde
        FileInputStream fisRef = new FileInputStream(new File("F:\\java\\02-workspace\\tmp\\read2.txt"));
        FileChannel fileChannel = fisRef.getChannel();
        fileChannel.position(2);//cde

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        byteBuffer.position(3);//cd

        // 向ByteBuffer读入cd
        fileChannel.read(byteBuffer);
        byte[] getByteArray = byteBuffer.array();
        for (int i = 0; i < getByteArray.length; i++) {
            if (getByteArray[i] == 0) {
                System.out.print("空格");
            } else {
                System.out.print((char)getByteArray[i]);
            }
        }

        fileChannel.close();
        fisRef.close();
    }

    @Test
    public void read4() throws Exception {
        // abcde
        FileInputStream fisRef = new FileInputStream(new File("F:\\java\\02-workspace\\tmp\\read3.txt"));
        FileChannel fileChannel = fisRef.getChannel();

        for (int i = 0; i < 1; i++) {
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
                        int readLength = fileChannel.read(byteBuffer);
                        while (readLength != -1) {
                            byte[] getByte = byteBuffer.array();
                            System.out.println("Thread1->" + new String(getByte, 0, readLength));
                            byteBuffer.clear();
                            readLength = fileChannel.read(byteBuffer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
                        int readLength = fileChannel.read(byteBuffer);
                        while (readLength != -1) {
                            byte[] getByte = byteBuffer.array();
                            System.out.println("Thread2->" + new String(getByte, 0, readLength));
                            byteBuffer.clear();
                            readLength = fileChannel.read(byteBuffer);
                        }
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
        fisRef.close();
    }

    @Test
    public void read5() throws Exception {
        FileInputStream fisRef = new FileInputStream(new File("F:\\java\\02-workspace\\tmp\\read2.txt"));
        FileChannel fileChannel = fisRef.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        System.out.println("A " + fileChannel.position());
        fileChannel.read(byteBuffer);
        System.out.println("B " + fileChannel.position());
        fileChannel.close();
        fisRef.close();
        byteBuffer.rewind();
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.print((char)byteBuffer.get());
        }
    }

    @Test
    public void read6() throws Exception {
        FileInputStream fisRef = new FileInputStream(new File("F:\\java\\02-workspace\\tmp\\read2.txt"));
        FileChannel fileChannel = fisRef.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.position(1);
        byteBuffer.limit(3);
        fileChannel.read(byteBuffer);
        fileChannel.close();
        fisRef.close();

        byteBuffer.rewind();

        for (int i = 0; i < byteBuffer.limit(); i++) {
            byte eachByte = byteBuffer.get();
            if (eachByte == 0) {
                System.out.print("空格");
            } else {
                System.out.print((char)eachByte);
            }
        }
    }

}
