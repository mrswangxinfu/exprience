package java8.wxf.algorithm.DP;

/**
 * 125 · 背包问题（二） 0-1背包 ---可行性型
 * 算法
 * 中等
 * 通过率
 * 52%
 *
 * 题目题解笔记讨论排名
 * 描述
 * 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
 *
 * 问最多能装入背包的总价值是多大?
 *
 * A[i], V[i], n, m 均为整数
 * 你不能将物品进行切分
 * 你所挑选的要装入背包的物品的总大小不能超过 m
 * 每个物品只能取一次
 * m <= 1000m<=1000\
 * len(A),len(V)<=100len(A),len(V)<=100
 *
 * 样例
 * 样例 1：
 *
 * 输入：
 *
 * m = 10
 * A = [2, 3, 5, 7]
 * V = [1, 5, 2, 4]
 * 输出：
 *
 * 9
 * 解释：
 *
 * 装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9
 *
 * 样例 2：
 *
 * 输入：
 *
 * m = 10
 * A = [2, 3, 8]
 * V = [2, 5, 8]
 * 输出：
 *
 * 10
 * 解释：
 *
 * 装入 A[0] 和 A[2] 可以得到最大价值, V[0] + V[2] = 10
 *
 * 挑战
 * O(nm) 空间复杂度可以通过, 你能把空间复杂度优化为O(m)吗？
 */
public class backPackII {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     *
     * 可以优化空间为O(n)
     */
    public int backPackII(int m, int[] A, int[] V) {
        // write your code here
        // f[i][j] is previous j-numbers max values of A for limit i
        int[][] f = new int[m+1][A.length+1];
        int maxValue;

        for (int i=0; i<=m; i++) {
            maxValue = 0;
            for (int j=0; j<=A.length; j++) {
                if (j==0 || i==0) {
                    f[i][j] = 0;
                    continue;
                }
                f[i][j] = maxValue;
                if (i-A[j-1]>=0) {
                    f[i][j] = Integer.max(f[i-A[j-1]][j-1] + V[j-1], maxValue);
                    maxValue = Integer.max(f[i][j], maxValue);
                }
            }
        }
        return f[m][A.length];
    }
}
