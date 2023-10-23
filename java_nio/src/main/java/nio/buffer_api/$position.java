package nio.buffer_api;

import java.nio.CharBuffer;

import org.junit.Test;

/**
 * position “下一个”要读取或写入元素的index（索引）
 * position∈ [0,limit]
 * position=limit的这种情况，put方法是没法设值的，position2()
 */
public class $position {

    @Test
    public void position1() {
        char[] charArray = new char[] {'a', 'b', 'c', 'd', 'e'};
        CharBuffer buffer = CharBuffer.wrap(charArray);
        System.out.println("-----------------------------------");
        System.out.println("wrap初始化时,mark = -1");
        System.out.println("position() = " + buffer.position());
        System.out.println("capacity() = " + buffer.capacity());
        System.out.println("limit() = " + buffer.limit());
        System.out.println("length() = " + buffer.length());
        System.out.println("remaining() = " + buffer.remaining());

        System.out.println("-----------------------------------");
        System.out.println("调用position(2)");
        buffer.position(2);
        System.out.println("position() = " + buffer.position());
        System.out.println("capacity() = " + buffer.capacity());
        System.out.println("limit() = " + buffer.limit());
        System.out.println("length() = " + buffer.length());
        System.out.println("remaining() = " + buffer.remaining());

        System.out.println("-----------------------------------");
        /*调用put("z")*/
        System.out.println("调用get()");
        System.out.println(buffer.get());
        System.out.println("position() = " + buffer.position());

        System.out.println("-----------------------------------");
        System.out.println("调用put(z)");
        buffer.put("z");
        System.out.println("position() = " + buffer.position());

        System.out.println("-----------------------------------");
        for (int i = 0; i < charArray.length; i++) {
            System.out.print(charArray[i] + " ");
        }

        System.out.println();
        System.out.println("调用put(\"z\")的时，position=3,所以index=3的值由原来的e被替换成z");
        System.out.println("get(),put(z)方法后，position的位置往后移了一位");
    }

    /**
     * throw java.nio.BufferOverflowException
     */
    @Test
    public void position2() {
        char[] charArray = new char[] {'a', 'b', 'c', 'd', 'e'};
        CharBuffer buffer = CharBuffer.wrap(charArray);
        buffer.limit(3);
        buffer.position(3);
        buffer.put("x");
    }
}
