package nio.server_socket_api.socket_address;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.junit.Test;

public class Construction {

    @Test
    public void test1() throws IOException {
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, 8888);

        System.out.println(inetSocketAddress.getHostName());
        System.out.println(inetSocketAddress.getHostString());

        InetSocketAddress inetSocketAddress1 = new InetSocketAddress("192.168.0.103", 80);
        InetSocketAddress inetSocketAddress2 = new InetSocketAddress("192.168.0.103", 80);
        System.out.println(inetSocketAddress1.getHostName());
        System.out.println(inetSocketAddress2.getHostString());

        InetAddress inetAddress1 = InetAddress.getLocalHost();

    }

    /**
     * public final String getHostName（）方法的作用是获取主机名。
     * 注意，如果地址是用字面IP地址创建的，则此方法可能触发名称服务反向查找，也就是利用DNS服务通过IP找到域名。
     * <p>
     * public final String getHostString（）方法的作用是返回主机名或地址的字符串形式，
     * 如果它没有主机名，则返回IP地址。这样做的好处是不尝试反向查找。
     */
    @Test
    public void test2() throws IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        /*System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getHostAddress());*/
        InetSocketAddress inetSocketAddress1 = new InetSocketAddress(inetAddress, 80);
        InetSocketAddress inetSocketAddress2 = new InetSocketAddress(inetAddress.getHostAddress(), 80);
        System.out.println(inetSocketAddress1.getHostString());
        System.out.println(inetSocketAddress1.getHostName());
        //注意：下面两行的顺序不同产生的结果不同

        // 如果先输出getHostName()，再输出getHostString()，
        /*private String getHostName() {
            if (hostname != null)
                return hostname;
            if (addr != null)
                return addr.getHostName();
            return null;
        }*/

        /*private String getHostString() {
            if (hostname != null)
                return hostname;
            if (addr != null) {
                if (addr.holder().getHostName() != null)
                    return addr.holder().getHostName();
                else
                    return addr.getHostAddress();
            }
            return null;
        }*/
        //先调用getHostName(), 调用getHostString()时 源码中hostname！=null了，所以返回的是hostname
        System.out.println(inetSocketAddress2.getHostString());
        System.out.println(inetSocketAddress2.getHostName());
    }

    @Test
    public void test3() throws UnknownHostException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);
        InetAddress inetAddress = inetSocketAddress.getAddress();
        byte[] ipAddress = inetAddress.getAddress();
        for (int i = 0; i < ipAddress.length; i++) {
            System.out.print((byte)ipAddress[i] + " ");
        }

        System.out.println("-------------------------");
        InetAddress inetAddress1 = InetAddress.getLocalHost();
        byte[] ipAddress1 = inetAddress1.getAddress();
        for (int i = 0; i < ipAddress1.length; i++) {
            System.out.print((byte)ipAddress1[i] + " ");
        }

    }

    @Test
    public void test3_1() throws UnknownHostException {
        InetAddress inetAddress1 = InetAddress.getLocalHost();
        byte[] ipAddress1 = inetAddress1.getAddress();
        for (int i = 0; i < ipAddress1.length; i++) {
            System.out.print((byte)ipAddress1[i] + " ");
        }

    }

    /**
     * 4.   创建未解析的套接字地址
     * <p>
     * public static InetSocketAddress createUnresolved（String host，int port）
     * 方法的作用是根据主机名和端口号创建未解析的套接字地址，但不会尝试将主机名解析为InetAddress。
     * 该方法将地址标记为未解析，有效端口值介于0～65535之间。
     * 端口号0代表允许系统在bind操作中随机挑选空闲的端口。
     * <p>
     * <p>
     * public final boolean isUnresolved（）
     * 方法的作用：如果无法将主机名解析为InetAddress，则返回true。
     */

    @Test
    public void test4() {
        InetSocketAddress inetSocketAddress1 = new InetSocketAddress("www.baidu.com", 80);
        // 输出false的原因是可以对www.baidu.com进行解析
        System.out.println(inetSocketAddress1.isUnresolved());
        System.out.println(inetSocketAddress1.getAddress().getHostAddress());

        InetSocketAddress inetSocketAddress2 = new InetSocketAddress("www.baidu 3245f dgsadfasdfasdfasdf.com ", 80);
        // 输出true的原因是不能对这个域名进行解析
        System.out.println(inetSocketAddress2.isUnresolved());

        // 输出true是因为即使能对www.baidu.com进行解析，内部也不解析
        InetSocketAddress inetSocketAddress3 = InetSocketAddress.createUnresolved("www.baidu.com", 80);
        System.out.println(inetSocketAddress3.isUnresolved());

        // 输出true的原因是内部从来不解析
        InetSocketAddress inetSocketAddress4 = InetSocketAddress.createUnresolved(
            "www.baidu3245fdgsadfasdfasdfasdf.com", 80);
        System.out.println(inetSocketAddress4.isUnresolved());
    }

}
