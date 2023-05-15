package alth.topic;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class D {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //手牌
        String s1 = sc.nextLine();
        //出过的牌
        String s2 = sc.nextLine();

        //初始化
        HashMap<String, Integer> count = new HashMap<>(16);
        count.put("3",4);
        count.put("4",4);
        count.put("5",4);
        count.put("6",4);
        count.put("7",4);
        count.put("8",4);
        count.put("9",4);
        count.put("10",4);
        count.put("J", 4);
        count.put("Q", 4);
        count.put("K", 4);
        count.put("A", 4);

        String[] ss1 = s1.split("-");
        for (int i = 0; i < ss1.length; i++) {
            count.put(ss1[i], count.get(ss1[i]) - 1);
        }

        String[] ss2 = s2.split("-");
        for (int i = 0; i < ss2.length; i++) {
            count.put(ss2[i], count.get(ss2[i]) - 1);
        }

        for(String key:count.keySet()){
            System.out.println("key-->"+key+",value-->"+count.get(key));
        }

        //最大长度,队列, 读到0就输出前面的串
        String[] sa = new String[]{"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        Deque<String> list = new LinkedList<>();
        //从大的开始
        String result = null;
        int max = 0;
        for (int i = sa.length - 1; i >= 0; i--) {
            if (count.get(sa[i]) > 0) {
                list.offerFirst(sa[i]);
            } else {
                //读到0了, 看能不能组成顺子
                if (list.size() < 5) {
                    list.clear();
                } else {
                    if (list.size() > max) {
                        max = list.size();
                        String tem = "";
                        while (list.size() > 0) {
                            tem += list.pollFirst() + "-";
                        }
                        result = tem.substring(0, tem.length() - 1);
                    } else {
                        list.clear();
                    }
                }
            }
        }

        //读到头 处理剩余数据
        if (list.size() >= 5 && list.size() > max) {
            max = list.size();
            String tem = "";
            while (list.size() > 0) {
                tem += list.pollFirst() + "-";
            }
            result = tem.substring(0, tem.length() - 1);
        }

        if (max == 0) {
            System.out.println("NO-CHAIN");
        } else {
            System.out.println(result);
        }
    }

}
