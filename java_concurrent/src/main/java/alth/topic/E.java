package alth.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E {


    static int m;
    static int n;

    static int result = 0;
    static int[][] arr = new int[99][99];



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();


        String[] ss = s.split(" ");
        //长
        m = Integer.parseInt(ss[0]);
        //宽
        n = Integer.parseInt(ss[1]);

        List<Pos> eatPos = new ArrayList<>();
        int[][] serched = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int point = sc.nextInt();
                if (point == 3) {
                    eatPos.add(new Pos(i, j));
                }
                if (point == 1) {
                    serched[i][j] = 1;
                }
                arr[i][j] = point;
            }
        }

        if (eatPos.size() == 0) {
            System.out.println(0);
            return;
        }


        for (Pos eat : eatPos) {
            //从聚餐点可以到达几个人, 如果能到达两个人, 则可达聚餐点+1
            int personFlag = 0;

            Pos left = eat.left();
            Pos right = eat.right();
            Pos up = eat.up();
            Pos down = eat.down();
            serched[eat.x][eat.y] = 1;
            //上下左右
            personFlag = search(left.x, left.y, personFlag, serched);
            if (personFlag == 2) {
                result++;
                continue;
            }
            personFlag = search(right.x, right.y, personFlag, serched);
            if (personFlag == 2) {
                result++;
                continue;
            }
            personFlag = search(up.x, up.y, personFlag, serched);
            if (personFlag == 2) {
                result++;
                continue;
            }
            personFlag = search(down.x, down.y, personFlag, serched);
            if (personFlag == 2) {
                result++;
            }
        }

        System.out.println(result);
    }



    /**
     * @param x          找的下一个点
     * @param y          找的下一个点
     * @param personFlag 记录已经找到的人数, 找到2个人就成功, 给答案+1
     * @param searched   记录已经找过的点
     */
    public static int search(int x, int y, int personFlag, int[][] searched) {
        if (!isValid(x, y, searched)) {
            return personFlag;
        }
        if (arr[x][y] == 2) {
            personFlag++;
        }
        if (personFlag == 2) {
            return personFlag;
        }

        int[][] serchedCopy = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                serchedCopy[i][j] = searched[i][j];
            }
        }
        serchedCopy[x][y] = 1;

        Pos current = new Pos(x, y);
        Pos left = current.left();
        Pos right = current.right();
        Pos up = current.up();
        Pos down = current.down();
        //上下左右
        personFlag = search(left.x, left.y, personFlag, serchedCopy);
        if (personFlag == 2) {
            return personFlag;
        }
        personFlag = search(right.x, right.y, personFlag, serchedCopy);
        if (personFlag == 2) {
            return personFlag;
        }
        personFlag = search(up.x, up.y, personFlag, serchedCopy);
        if (personFlag == 2) {
            return personFlag;
        }
        personFlag = search(down.x, down.y, personFlag, serchedCopy);
        return personFlag;
    }

    /**
     * 是否在边界内而且没找过
     */
    public static boolean isValid(int x, int y, int[][] searched) {
        if (x >= m || x < 0 || y >= n || y < 0) {
            return false;
        }
        if (searched[x][y] == 1) {
            return false;
        }
        return true;
    }


    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos left() {
            return new Pos(x, y - 1);
        }

        public Pos right() {
            return new Pos(x, y + 1);
        }

        public Pos up() {
            return new Pos(x - 1, y);
        }

        public Pos down() {
            return new Pos(x + 1, y);
        }

    }


    /*public static void print(int[][] arr) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }*/
}
