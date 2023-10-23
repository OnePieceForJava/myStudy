package nio.buffer_api;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * final Buffer flip（）方法的作用：反转此缓冲区。
 * 首先将限制设置为当前位置，然后将位置设置为0。如果已定义了标记，则丢弃该标记。
 * <p>
 * 通俗解释是“缩小limit的范围
 * <p>
 * limit = position;
 * position = 0;
 * mark = -1;
 */
public class $flip {

    @Test
    public void flip1() {
        byte[] byteArray = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteBuffer bytebuffer = ByteBuffer.wrap(byteArray);
        bytebuffer.position(2);
        bytebuffer.mark();
        System.out.println("-----------------------------------");
        System.out.println("mark = 2");
        System.out.println("position() = " + bytebuffer.position());
        System.out.println("limit() = " + bytebuffer.limit());
        System.out.println("capacity() = " + bytebuffer.capacity());

        bytebuffer.flip();
        System.out.println("-----------------------------------");
        System.out.println("mark = -1");
        System.out.println("position() = " + bytebuffer.position());
        System.out.println("limit() = " + bytebuffer.limit());
        System.out.println("capacity() = " + bytebuffer.capacity());
        System.out.println("bytebuffer.position=" + bytebuffer.position());
        System.out.println();
        try {
            bytebuffer.reset();
        } catch (java.nio.InvalidMarkException e) {
            System.out.println("flip()方法调用后,mark丢失,reset()方法报错");
        }
    }

    @Test
    public void flip2() {
        CharBuffer charBuffer = CharBuffer.allocate(20);
        System.out.println("A position=" + charBuffer.position() + " limit=" +
            charBuffer.limit());
        // 一共写入14个字
        charBuffer.put("我是中国人我在中华人民共和国");
        System.out.println("B position=" + charBuffer.position() + " limit=" +
            charBuffer.limit());
        charBuffer.position(0);// 位置position还原成0
        System.out.println("C position=" + charBuffer.position() + " limit=" +
            charBuffer.limit());
        // 下面for语句的打印效果是“国”字后面有6个空格，这6个空格是无效的数据
        // 应该只打印前14个字符，后6个字符不再读取
        for (int i = 0; i < charBuffer.limit(); i++) {
            System.out.print(charBuffer.get());
        }
        System.out.println();
        // 上面的代码是错误读取数据的代码

        // 下面的代码是正确读取数据的代码
        System.out.println("D position=" + charBuffer.position() + " limit=" +
            charBuffer.limit());
        // 还原缓冲区的状态
        charBuffer.clear();
        System.out.println("E position=" + charBuffer.position() + " limit=" +
            charBuffer.limit());
        // 继续写入
        charBuffer.put("我是美国人");
        System.out.println("F position=" + charBuffer.position() + " limit=" +
            charBuffer.limit());
        // 设置for循环结束的位置，也就是新的limit值
        /*charBuffer.limit(charBuffer.position());
        charBuffer.position(0);*/
        //注释掉的代码可以使用flip()方法处理
        charBuffer.flip();
        System.out.println("G position=" + charBuffer.position() + " limit=" +
            charBuffer.limit());
        for (int i = 0; i < charBuffer.limit(); i++) {
            System.out.print(charBuffer.get());
        }
    }
}
