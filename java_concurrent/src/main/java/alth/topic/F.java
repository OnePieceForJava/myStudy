package alth.topic;

import util.PatternMethod;

import java.util.Arrays;

public class F {

    /**
     * 1.
     * 最长递增子序列
     * 对于一个数字序列，请设计一个复杂度为O(nlogn)的算法，返回该序列的最长上升子序列的长度，
     * 这里的子序列定义为这样一个序列U1，U2...，其中Ui < Ui+1，且A[Ui] < A[Ui+1]。
     * 给定一个数字序列A及序列的长度n，请返回最长上升子序列的长度。
     * 测试样例：
     * [2,1,4,3,1,5,6],7
     * 返回：4
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {5,7,1,9,4,6,2,8,3};
        //System.out.println(myImpl(nums));
        findLongest(nums,nums.length);
    }


    /**
     * 动态规划解法
     * @param nums
     * @return
     */
    private static int myImpl(int[] nums){
        if(nums.length<2){
            return nums.length;
        }
        int res = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        for(int i = 1;i<nums.length;i++){
            for(int j =0;j<i;j++){
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
                res = Math.max(res,dp[i]);
            }
        }
        return res;
    }


    /**
     * gt 英文全称是 Greater than
     *
     * lt 英文全称是 Less than
     * @param arr
     * @param dp
     * @return
     */

    public static int[] generateLIS(int[] arr, int[] dp) {
        int len = 0;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];
        lis[--len] = arr[index];
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                lis[--len] = arr[i];
                index = i;
            }
        }
        return lis;
    }

    public static int[] lis(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdp(arr);
        return generateLIS(arr, dp);
    }

    public static int[] getdp(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l + 1;
        }
        return dp;
    }

    public static int findLongest(int[] A, int n) {
        int[] ans = lis(A);
        return ans.length;
    }

}
