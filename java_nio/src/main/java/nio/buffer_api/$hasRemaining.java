package nio.buffer_api;

import org.junit.Test;

import java.nio.ByteBuffer;

public class $hasRemaining {

    @Test
    public void hasRemaining() {
        byte[] byteArray = new byte[] {1, 2, 3};

        ByteBuffer bytebuffer = ByteBuffer.wrap(byteArray);

        bytebuffer.limit(3);
        bytebuffer.position(2);

        System.out.println("bytebuffer.hasRemaining=" + bytebuffer.hasRemaining() +
            " bytebuffer.remaining="
            + bytebuffer.remaining());
    }
}
