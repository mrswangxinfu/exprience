package java8.wxf.algorithm.DP;

import java.text.MessageFormat;

/**
 * 动态规划（DP）：
 * 问题：找零钱（硬币有：1、5、20、25分）
 */
public class coins {
    public static void main(String[] args) {
        int num= coins.coins(41);
        System.out.println("找零的硬币最少个数为："+num);
        String str = "this is a test!";
        System.out.println(MessageFormat.format
                ("MessageFormat方法：{0}这是{1}的使用", str ,"占位符"));

    }

    /**
     * 暴力解法
     * @param n
     * @return
     */
//    static int coins(int n) {
//        // 递归基
//        if (n < 1) return Integer.MAX_VALUE;
//        if (n == 1 || n == 5 || n == 20 || n == 25) return 1; // 边界情况
//
//        // 求出四种取法的最小值
//        int min1 = Math.min(coins(n - 1), coins(n - 5));
//        int min2 = Math.min(coins(n - 20), coins(n - 25));
//
//        return Math.min(min1, min2) + 1;
//    }


    /**
     *找零钱 - 记忆化搜索
     */
    static int coins(int n) {
        if (n < 1) return -1; // 处理非法数据
        int[] dp = new int[n + 1];
        int[] faces = { 1, 5, 20, 25 }; // 给定的面值数组

        for (int face : faces) {
            // 如果我要凑的钱是20元, 那么我肯定用不到25元面值
            if (face > n) break; // 用不到的面值不用初始化
            dp[face] = 1; // 初始化可能用到的面值
        }
        return coins(n, dp);
    }
    static int coins(int n, int[] dp) {
        // 递归基
        if (n < 1) return Integer.MAX_VALUE;
        if (dp[n] == 0) { // 记忆化搜索, dp[n] == 0 表示以前没有算过, 那便初始化一下
            int min1 = Math.min(coins(n - 5, dp), coins(n - 1, dp));
            int min2 = Math.min(coins(n - 25, dp), coins(n - 20, dp));
            dp[n] = Math.min(min1, min2) + 1;
        }
        return dp[n];
    }
}
