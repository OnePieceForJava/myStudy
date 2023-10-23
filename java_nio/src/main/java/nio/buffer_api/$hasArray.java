package nio.buffer_api;

import org.junit.Test;

import java.nio.ByteBuffer;

public class $hasArray {
    /**
     * 程序中使用byte[]hb存储数据，所以hb[]对象为非空，结果就是true。
     * 打印false代表byte[]hb数组值为null，并没有将数据存储到hb[]中，而是直接存储在内存中。
     */
    @Test
    public void hasArray1() {
        ByteBuffer allocateByteBuffer = ByteBuffer.allocate(5);
        ByteBuffer aallocateDirectByteBuffer = ByteBuffer.allocateDirect(5);
        System.out.println(allocateByteBuffer.hasArray());
        System.out.println(aallocateDirectByteBuffer.hasArray());
    }
}
