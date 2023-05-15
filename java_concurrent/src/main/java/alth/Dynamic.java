package alth;

import util.PatternMethod;

public class Dynamic {
    /*public static void main(String[] args) {
        int[][] array = {{1,3,5,9},{8,1,3,4},{5,0,6,1},{8,8,4,0}};
        System.out.println(myanmic(array));
    }

    public static int myanmic(int[][] array) {
        if(array.length == 0) {
            return 0;
        }
        //声明一个新的数组
        i
        //遍历每行
        for(int i = 1; i < array.length; i++) {
            fornt[][] dp = new int[array.length][array.length];
        dp[0][0] = array[0][0];
        for(int i = 1; i < dp[0].length; i++) {
            dp[0][i] = array[0][i - 1] + array[0][i];
        }(int j = 0; j < dp[i].length; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i - 1][j] + array[i][j];
                }else if(dp[i - 1][j] < dp[i][j - 1]){
                    dp[i][j] = array[i][j] + dp[i - 1][j];
                }else {
                    dp[i][j] = dp[i][j - 1] + array[i][j];
                }
            }
        }
        return dp[dp.length - 1][dp[dp.length - 1].length - 1];
    }*/


    /**
     * f(n) 这个n变量是什么？
     * -->n是背包的容量
     * --> 归纳法：f(1),f(2),...,f(n)
     *
     *
     * f(1)可以放入哪些
     */


    /**
     * 给定长度都为N的数据weights[] values[]
     * bag代表袋子的载重
     * 装的物品不能超过bag,价值最大
     *
     *
     *  01背包问题
     * @param weights
     * @param values
     * @param bag
     * @return
     */
    public static int solution_1(int[] weights, int[] values, int bag) {
        //每样物品只能放到背包一次，最大能放到背包中的物品数量为len
        int len = weights.length;

        /*数组的下标是从0开始的，这里“+1”的目的是为了下标从1开始，方便理解*/
        int[][] dp = new int[len + 1][bag + 1];

        //行是物品的数量，列是背包的重量
        /*for(int i = 1;i<=len;i++){
            for(int j = 1;j<=bag;j++){
            }
        }*/
        for (int i = len - 1; i >= 0; --i) {
            //System.out.println("--->" + i);
            for (int j = 1; j <= bag; ++j) {
                System.out.println("dp["+(i+1)+"]["+j+"]--->"+dp[i+1][j]);
                dp[i][j] = dp[i + 1][j];
                if (j >= weights[i]) {
                    dp[i][j] = Math.max(dp[i][j], values[i] + dp[i + 1][j - weights[i]]);
                }
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        //array();
        System.out.println(solution_1(new int[] {50, 30, 100}, new int[] {40, 50, 80}, 100));
    }

    /**
     * 理解二维数组
     */
    public static void array() {
        int[][] array = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        for (int i = 0; i < array.length; i++) {
            //System.out.println(PatternMethod.arrPrint(array[i]));
            for (int j = 0; j < array[i].length; j++) {
                //System.out.print(array[i][j] +" ");
                System.out.println("arrry["+i+"]["+j+"]--->"+array[i][j]);
            }
            System.out.println();
        }

        /**
         * arrry[0][0]--->1
         * arrry[0][1]--->3
         * arrry[0][2]--->5
         * arrry[0][3]--->9
         *
         * arrry[1][0]--->8
         * arrry[1][1]--->1
         * arrry[1][2]--->3
         * arrry[1][3]--->4
         *
         * arrry[2][0]--->5
         * arrry[2][1]--->0
         * arrry[2][2]--->6
         * arrry[2][3]--->1
         *
         * arrry[3][0]--->8
         * arrry[3][1]--->8
         * arrry[3][2]--->4
         * arrry[3][3]--->0
         *
         */
    }
}