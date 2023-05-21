package nio.buffer_api;

import java.nio.*;

public class $wrap {

    public static void main(String[] args) {
        byte[] byteArray = new byte[] { 1, 2, 3 };
        short[] shortArray = new short[] { 1, 2, 3, 4 };
        int[] intArray = new int[] { 1, 2, 3, 4, 5 };
        long[] longArray = new long[] { 1, 2, 3, 4, 5, 6 };
        float[] floatArray = new float[] { 1, 2, 3, 4, 5, 6, 7 };
        double[] doubleArray = new double[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        char[] charArray = new char[] { 'a', 'b', 'c', 'd' };

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
}