package nio.buffer_api;

import org.junit.Test;

import java.nio.ByteBuffer;

public class $clear {

    @Test
    public void test1() {
        byte[] byteArray = new byte[] {1, 2, 3, 4, 5};

        ByteBuffer bytebuffer = ByteBuffer.wrap(byteArray);
        System.out.println("-----------------------------------");
        System.out.println("wrap初始化时,mark = -1");
        System.out.println("position() = " + bytebuffer.position());
        System.out.println("limit() = " + bytebuffer.limit());
        System.out.println("capacity() = " + bytebuffer.capacity());

        System.out.println("-----------------------------------");
        bytebuffer.position(2);
        bytebuffer.mark();
        System.out.println("调用position(2),mark()后，mark = 2");
        System.out.println("position() = " + bytebuffer.position());
        bytebuffer.limit(4);
        System.out.println("调用limit(4)");
        System.out.println("limit() = " + bytebuffer.limit());
        System.out.println("capacity() = " + bytebuffer.capacity());
        System.out.println("-----------------------------------");

        bytebuffer.clear();
        System.out.println("调用clear()后,mark = -1");
        System.out.println("position() = " + bytebuffer.position());
        System.out.println("limit() = " + bytebuffer.limit());
        System.out.println("capacity() = " + bytebuffer.capacity());
        System.out.println("-----------------------------------");

        try {
            bytebuffer.reset();
        } catch (java.nio.InvalidMarkException e) {
            System.out.println("调用reset()，捕获异常，mark丢失");
        }
    }
}
