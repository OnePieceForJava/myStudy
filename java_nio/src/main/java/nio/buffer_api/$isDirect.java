package nio.buffer_api;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

public class $isDirect {

    @Test
    public void isDirect() {
        byte[] byteArray = new byte[] {1, 2, 3};
        ByteBuffer wrapBuffer = ByteBuffer.wrap(byteArray);
        System.out.println(wrapBuffer.isDirect());

        ByteBuffer allocateBuffer = ByteBuffer.allocate(3);
        System.out.println(allocateBuffer.isDirect());

        ByteBuffer allocateDirectBuffer = ByteBuffer.allocateDirect(3);
        System.out.println(allocateDirectBuffer.isDirect());
    }

    @Test
    public void DirectClean1()
        throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //2147483647+1  --> 2^32 = 2^10 * 2^10 * 2^10 *2
        //B -> KB -> MB ->GB    2GB的内存
        ByteBuffer buffer = ByteBuffer.allocateDirect(Integer.MAX_VALUE);

        //1B的空间
        byte[] byteArray = new byte[] {1};
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            buffer.put(byteArray);
        }
        System.out.println("put end!");
        Thread.sleep(1000);
        // 此程序运行的效果就是1秒钟之后立即回收内存
        // 也就是回收“直接缓冲区”所占用的内存
        //DirectByteBuffer.cleaner() 获取Cleaner对象
        // 调用Cleaner对象的clean()
        Method cleanerMethod = buffer.getClass().getMethod("cleaner");
        cleanerMethod.setAccessible(true);
        Object returnValue = cleanerMethod.invoke(buffer);
        Method cleanMethod = returnValue.getClass().getMethod("clean");
        cleanMethod.setAccessible(true);
        cleanMethod.invoke(returnValue);

    }

    @Test
    public void DirectClean2() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(Integer.MAX_VALUE);
        byte[] byteArray = new byte[] {1};
        System.out.println(Integer.MAX_VALUE);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            buffer.put(byteArray);
        }
        System.out.println("put end!");
        // 此程序多次运行后，一直在耗费内存，
        // 进程结束后，也不会马上回收内存，
        // 而是会在某个时机触发GC垃圾回收器进行内存的回收
    }
}
