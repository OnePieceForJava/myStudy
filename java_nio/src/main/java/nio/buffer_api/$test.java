package nio.buffer_api;

import java.nio.ByteBuffer;

import org.junit.Test;

public class $test {

    @Test
    public void test1(){
        ByteBuffer lenBuffer = ByteBuffer.allocateDirect(4);

        /**
         * After the length is read, a new incomingBuffer is allocated in
         * readLength() to receive the full message.
         */
        ByteBuffer incomingBuffer = lenBuffer;
        System.out.println("incomingBuffer.hasRemaining()-->"+(incomingBuffer.hasRemaining()));
        System.out.println("incomingBuffer.remaining()-->"+incomingBuffer.remaining());
        if (!incomingBuffer.hasRemaining()) {
        }

        /*System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());

        System.out.println(byteBuffer.getInt());*/
    }
}
