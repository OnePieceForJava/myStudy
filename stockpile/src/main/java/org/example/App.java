package org.example;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Locale;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        String urlStr = "https://search.zhelper.net/?[{%22name%22:%22Ylibrary%22,%22url%22:%22https://api.ylibrary.org%22,%22type%22:%22full%22,%22sensitive%22:false,%22detail%22:true}]";
        String param = "{\"keyword\":\"redis实战\",\"sensitive\":\"false\"},\"page\":\"1\"}";
        SendHttpsPOST(urlStr,param);
    }


    private static void test() {
        /*String content = MessageUtil.createContentMessage(head,data);
        URL postUrl = null;
        DataOutputStream output = null;
        try {
            postUrl = new URL("http://183.63.131.106:40011/extService/ghbExtService.do"); //URL请求地址
//postUrl = new URL("http://183.63.131.106:40013/extServiceTest/ghbExtService.do"); //URL请求地址
            HttpURLConnection urlcon =  (HttpURLConnection) postUrl.openConnection();
            int contentLength = content.getBytes().length;  //获取报文长度
            urlcon.setConnectTimeout(1000*15);
            urlcon.setReadTimeout(1000*60*2);
            urlcon.setRequestMethod("POST");  //post请求方式
            urlcon.setUseCaches(false);       //post请求不能使用缓存
            urlcon.setRequestProperty("Content-Length", String.valueOf(contentLength));
            if("1".equals(content.charAt(5))){
                urlcon.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
            }
            if("2".equals(content.charAt(5))){
                urlcon.setRequestProperty("Content-Type", "www-form-urlencoded");
                urlcon.setRequestProperty("charset", "UTF-8");
            }
            urlcon.setDoInput(true);  //默认为true
            urlcon.setDoOutput(true); //默认为true
//urlcon.connect();       //urlcon.getOutputStream()会隐含的进行connect();
            output =  new DataOutputStream(urlcon.getOutputStream());
            output.writeBytes(content);
            output.flush();

            output.close();
            String str=null;
//if("1".equals(String .valueOf(content.charAt(5)))){
            BufferedReader buffer =new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
            StringBuffer strBuffer = new StringBuffer();
            String str = null;
            while((str=buffer.readLine())!=null){
                strBuffer.append(str);
            }
            System.out.println("返回报文为： "+strBuffer);
            buffer.close();
//}

            urlcon.disconnect();
    */
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数,json格式，请求参数应该是{name:value1,name2:value2}的形式。
     * @return 所代表远程资源的响应结果
     * @throws IOException
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        URLConnection conn = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("contentType", "UTF-8");
            conn.setRequestProperty("Accept-Language", Locale.getDefault().toString());
            conn.setRequestProperty("Accept",
                    "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(120000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            // out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            InputStream stream = conn.getInputStream();
            in = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                in = new BufferedReader(new InputStreamReader(((HttpURLConnection) conn).getErrorStream(), "UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }



    private static TrustManager myX509TrustManager = new X509TrustManager() {

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
    };

    static public String SendHttpsPOST(String url, String param)
    {
        String result = null;


        //使用此工具可以将键值对编码成"Key=Value&Key2=Value2&Key3=Value3”形式的请求参数
        //String requestParam = URLEncodedUtils.format(param, "UTF-8");
        try {
            //设置SSLContext
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{myX509TrustManager}, null);

            //打开连接
            //要发送的POST请求url?Key=Value&Key2=Value2&Key3=Value3的形式
            URL requestUrl = new URL(url);
            HttpsURLConnection httpsConn = (HttpsURLConnection)requestUrl.openConnection();

            //设置套接工厂
            httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

            //加入数据
            httpsConn.setRequestMethod("POST");
            httpsConn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(
                    httpsConn.getOutputStream());
            if (param != null)
                out.writeBytes(param);

            out.flush();
            out.close();

            //获取输入流
            BufferedReader in = new BufferedReader(new InputStreamReader(httpsConn.getInputStream()));
            int code = httpsConn.getResponseCode();
            if (HttpsURLConnection.HTTP_OK == code){
                String temp = in.readLine();
                /*连接成一个字符串*/
                while (temp != null) {
                    if (result != null)
                        result += temp;
                    else
                        result = temp;
                    temp = in.readLine();
                }
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
