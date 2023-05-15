package alth.topic;

import java.util.Scanner;

/**
 * 1.
 * 汽水瓶
 * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
 * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
 * 数据范围：输入的正整数满足
 * 1≤n≤100
 * 注意：本题存在多组输入。输入的 0 表示输入结束，并不用输出结果。
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 32M，其他语言64M
 *
 *
 * 输入描述：
 * 输入文件最多包含 10 组测试数据，每个数据占一行，仅包含一个正整数 n（ 1<=n<=100 ），表示小张手上的空汽水瓶数。n=0 表示输入结束，你的程序不应当处理这一行。
 *
 * 输出描述：
 * 对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。
 *
 * 示例1
 * 输入例子：
 * 3
 * 10
 * 81
 * 0
 * 输出例子：
 * 1
 * 5
 * 40
 * 例子说明：
 * 样例 1 解释：用三个空瓶换一瓶汽水，剩一个空瓶无法继续交换
 * 样例 2 解释：用九个空瓶换三瓶汽水，剩四个空瓶再用三个空瓶换一瓶汽水，剩两个空瓶，向老板借一个空瓶再用三个空瓶换一瓶汽水喝完得一个空瓶还给老板
 */
public class A {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            if(a==0){
                break;
            }else {
                System.out.println(recursiveMode(a));
            }
            //int b = in.nextInt();
            //System.out.println(a + b);
        }
    }


    /**
     * 剩余瓶数
     *
     * @param in
     * @return
     */
    public static int recursiveMode(int in) {
        if (in < 2) {
            return 0;
        } else if (in == 2) {
            return 1;
        } else {
            //可兑换饮料数
            int x = in / 3;
            //剩余饮料瓶数
            int y = in % 3;
            return x + recursiveMode(x + y);
        }
    }


}
