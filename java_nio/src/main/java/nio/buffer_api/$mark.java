package nio.buffer_api;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * mark()--> mark = position;
 * 缓冲区的标记是一个索引，
 * <p>
 * 在调用reset（）方法时，会将缓冲区的position位置重置为该索引。
 * 标记（mark）并不是必需的。
 * 定义mark时，不能将其定义为负数，并且不能让它大于position。
 * 如果定义了mark，则在将position或limit调整为小于该mark的值时，该mark被丢弃，丢弃后mark的值是-1。
 * 如果未定义mark，那么调用reset（）方法将导致抛出InvalidMarkException异常
 */
public class $mark {
    @Test
    public void mark1() {
        byte[] byteArray = new byte[] {1, 2, 3};
        ByteBuffer bytebuffer = ByteBuffer.wrap(byteArray);
        System.out.println("-----------------------------------");
        System.out.println("wrap初始化时,mark = -1");
        System.out.println("position() = " + bytebuffer.position());
        System.out.println("limit() = " + bytebuffer.limit());
        System.out.println("capacity() = " + bytebuffer.capacity());

        System.out.println("-----------------------------------");
        bytebuffer.position(1);
        bytebuffer.mark();  // 在位置1设置mark
        System.out.println("调用position(1),mark()");
        System.out.println("position() = " + bytebuffer.position());

        bytebuffer.position(2);
        bytebuffer.reset();

        System.out.println("-----------------------------------");
        System.out.println("调用position(2),reset()");
        System.out.println("position() = " + bytebuffer.position());

        System.out.println("-----------------------------------");
        System.out.println("总结：");
        System.out.println("mark()方法的作用 --> mark= position");
        System.out.println("reset()方法的作用-->position = mark");

    }

    /**
     * mark = -1 时，是无法调用reset()的
     */
    @Test
    public void mark2() {
        byte[] byteArray = new byte[] {1, 2, 3};
        ByteBuffer bytebuffer = ByteBuffer.wrap(byteArray);
        bytebuffer.reset();

    }
}
