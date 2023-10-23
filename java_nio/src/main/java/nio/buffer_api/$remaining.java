package nio.buffer_api;

import org.junit.Test;

import java.nio.CharBuffer;

/**
 * 返回“当前位置”与limit之间的元素数
 */
public class $remaining {
    @Test
    public void remaining1() {
        char[] charArray = new char[] {'a', 'b', 'c', 'd', 'e'};
        CharBuffer buffer = CharBuffer.wrap(charArray);
        System.out.println("-----------------------------------");
        System.out.println("position() = " + buffer.position());
        System.out.println("limit() = " + buffer.limit());
        System.out.println("remaining() = " + buffer.remaining());

        System.out.println("-----------------------------------");
        System.out.println("调用limit(3),position(2)");
        buffer.limit(3);
        buffer.position(2);
        System.out.println("position() = " + buffer.position());
        System.out.println("limit() = " + buffer.limit());
        System.out.println("remaining() = " + buffer.remaining());

        System.out.println("-----------------------------------");
        System.out.println("结论：");
        System.out.println("remaining = limit - position");

    }
}
