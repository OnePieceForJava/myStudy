package nio.buffer_api;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import org.junit.Test;

public class $wrap {

    public static void main(String[] args) {
        byte[] byteArray = new byte[] {1, 2, 3};
        short[] shortArray = new short[] {1, 2, 3, 4};
        int[] intArray = new int[] {1, 2, 3, 4, 5};
        long[] longArray = new long[] {1, 2, 3, 4, 5, 6};
        float[] floatArray = new float[] {1, 2, 3, 4, 5, 6, 7};
        double[] doubleArray = new double[] {1, 2, 3, 4, 5, 6, 7, 8};
        char[] charArray = new char[] {'a', 'b', 'c', 'd'};

        ByteBuffer bytebuffer = ByteBuffer.wrap(byteArray);
        ShortBuffer shortBuffer = ShortBuffer.wrap(shortArray);
        IntBuffer intBuffer = IntBuffer.wrap(intArray);
        LongBuffer longBuffer = LongBuffer.wrap(longArray);
        FloatBuffer floatBuffer = FloatBuffer.wrap(floatArray);
        DoubleBuffer doubleBuffer = DoubleBuffer.wrap(doubleArray);
        CharBuffer charBuffer = CharBuffer.wrap(charArray);

        System.out.println("bytebuffer=" + bytebuffer.getClass().getName());
        System.out.println("shortBuffer=" + shortBuffer.getClass().getName());
        System.out.println("intBuffer=" + intBuffer.getClass().getName());
        System.out.println("longBuffer=" + longBuffer.getClass().getName());
        System.out.println("floatBuffer=" + floatBuffer.getClass().getName());
        System.out.println("doubleBuffer=" + doubleBuffer.getClass().getName());
        System.out.println("charBuffer=" + charBuffer.getClass().getName());

        System.out.println();

        System.out.println("bytebuffer.capacity=" + bytebuffer.capacity());
        System.out.println("shortBuffer.capacity=" + shortBuffer.capacity());
        System.out.println("intBuffer.capacity=" + intBuffer.capacity());
        System.out.println("longBuffer.capacity=" + longBuffer.capacity());
        System.out.println("floatBuffer.capacity=" + floatBuffer.capacity());
        System.out.println("doubleBuffer.capacity=" + doubleBuffer.capacity());
        System.out.println("charBuffer.capacity=" + charBuffer.capacity());
    }

    @Test
    public void wrap2() {
        ByteBuffer bytebuffer1 = ByteBuffer.allocateDirect(100);
        ByteBuffer bytebuffer2 = ByteBuffer.allocate(200);
        System.out.println("bytebuffer1 position=" + bytebuffer1.position() +
            " limit=" + bytebuffer1.limit());
        System.out.println("bytebuffer2 position=" + bytebuffer2.position() +
            " limit=" + bytebuffer2.limit());
        System.out.println("bytebuffer1=" + bytebuffer1 + " isDirect=" + bytebuffer1.
            isDirect());
        System.out.println("bytebuffer2=" + bytebuffer2 + " isDirect=" + bytebuffer2.
            isDirect());
    }
}