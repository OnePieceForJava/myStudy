package nio.buffer_api;

import org.junit.Test;

import java.nio.CharBuffer;

/**
 * limit():int
 * limit(int):Buffer
 * 第一个不应该读取或写入元素的index（索引）
 */
public class $limit {

    @Test
    public void limit1() {
        char[] charArray = new char[]{'a', 'b', 'c', 'd', 'e'};
        CharBuffer buffer = CharBuffer.wrap(charArray);
        System.out.println("-----------------------------------");
        System.out.println("wrap初始化时,mark = -1");
        System.out.println("position() = " + buffer.position());
        System.out.println("capacity() = " + buffer.capacity());
        System.out.println("limit() = " + buffer.limit());
        System.out.println("length() = " + buffer.length());
        System.out.println("remaining() = " + buffer.remaining());

        System.out.println("-----------------------------------");
        /**
         * limit ∈ (0,capacity),不在改范围 throw new IllegalArgumentException();
         * if (position > newLimit) position = newLimit;
         * if (mark > newLimit) mark = -1;
         */
        buffer.limit(3);
        System.out.println("调用limit(3),将limit设置为3");
        System.out.println("position() = " + buffer.position());
        System.out.println("capacity() = " + buffer.capacity());
        System.out.println("limit() = " + buffer.limit());
        System.out.println("length() = " + buffer.length());
        System.out.println("remaining() = " + buffer.remaining());

        char[] writeChars = {'f', 'g', 'h', 'i', 'j'};
        System.out.println("写-----------------------------------");
        try {
            for (int i = 0; i < buffer.capacity(); i++) {
                System.out.print(i + "--->");
                buffer.put(writeChars[i]);
                System.out.println("写入" + buffer.get(i) + "完成");
            }
        } catch (Exception e) {
            System.out.println("写出现异常");
        }

        System.out.println("读-----------------------------------");
        try {
            for (int i = 0; i < buffer.capacity(); i++) {
                System.out.print(i + "--->");
                System.out.println(buffer.get(i));
            }
        } catch (Exception e) {
            System.out.println("读出现异常");
        }
    }
}
