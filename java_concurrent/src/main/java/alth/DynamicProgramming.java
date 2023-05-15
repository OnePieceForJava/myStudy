package alth;

public class DynamicProgramming {
    public static void main(String[] args) {
        int[] weights = {5, 7, 3, 6, 4, 8};
        int[] values = {400, 700, 300, 500, 700, 600};
        int c = 10;
        int n = weights.length;
        getMaxBagValue(weights, values, c, n);
    }

    private static int getMaxBagValue(int[] weights, int[] values, int c, int n) {
        int[][] v = new int[n + 1][c + 1];

        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= c ; j++) {
                if (j < weights[i - 1]) {
                    v[i][j] = v[i - 1][j];
                } else {
                    v[i][j] = Math.max(v[i - 1][j], values[i - 1] + v[i - 1][j - weights[i - 1]]);
                }
            }
        }

        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= c ; j++) {
                String value = String.valueOf(v[i][j]);
                for (int l = value.length(); l <= 8; l++) {
                    value += " ";
                }
                System.out.print(value+"\t");
            }
            System.out.println();
        }
        System.out.println(v[n][c]);
        return v[n][c];
    }
}
