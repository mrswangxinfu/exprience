package java8.wxf.algorithm.DP;

/**
 * 801 · 背包问题X 完全背包
 * 算法
 * 中等
 * 通过率
 * 72%
 *
 * 题目题解笔记讨论排名
 * 描述
 * 你总共有n元，商人总共有三种商品，它们的价格分别是150元,250元,350元，三种商品的数量可以认为是无限多的，购买完商品以后需要将剩下的钱给商人作为小费，求最少需要给商人多少小费
 *
 * 样例
 * 样例 1:
 *
 * 输入:  n = 900
 * 输出:  0
 * 样例 2:
 *
 * 输入:  n = 800
 * 输出:  0
 */
public class backPackX {
    /**
     * @param n: the money you have
     * @return: the minimum money you have to give
     */
    public int backPackX(int n) {
        // write your code here
        int[] value = {150, 250, 350};
        int[] f = new int[n+1];

        for (int i=1; i<=value.length; i++) {
            // 完全背包 正序优化空间复杂度
            for (int j=value[i-1]; j<=n; j++) {
                f[j] = Math.max(f[j],f[j-value[i-1]]+value[i-1]);
            }
        }
        return n-f[n];
    }
}
