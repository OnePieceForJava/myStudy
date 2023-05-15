import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        if (in.hasNextLine()) { // 注意 while 处理多个 case
            String inStr = in.nextLine();
            if (inStr.length() < 8) {
                for (int i = inStr.length(); i < 8; i++) {
                    inStr += "0";
                }
                System.out.print(inStr);
            } else {
                for (int i = 0; i < inStr.length();) {
                    String subStr ;
                    if((i+8)>inStr.length()){
                        subStr = inStr.substring(i, inStr.length());
                        for (int j = subStr.length(); j < 8; j++) {
                            subStr += "0";
                        }
                    }else{
                        subStr = inStr.substring(i, i+8);
                    }
                    System.out.println(subStr);
                    i = i+8;
                }
            }
        }
    }
}