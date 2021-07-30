package java8.wxf.algorithm.DP;

/**
 * 971 · 剩余价值背包
 * 算法
 * 困难
 * 通过率
 * 55%
 *
 * 题目题解笔记讨论排名
 * 描述
 * 有一个容量为 c 的背包。
 * 有 n 个 A 类物品，第 i 个 A 类物品的体积为 a[i]，物品的价值为装入该物品后背包剩余容量 * k1。
 * 有 m 个 B 类物品，第 i 个 B 类物品的体积为 b[i]，物品的价值为装入该物品后背包剩余容量 * k2。
 * 求最大可以获得的价值。
 *
 * 1 <= k1, k2, c, a[i], b[i] <= 10^7
 * 1 <= n, m <= 1000
 * 样例
 * 样例 1:
 *
 * 给出 k1 = `3`,k2 = `2`,c = ` 7`,n = `2`,m = `3`,a = `[4,3]`,b = `[1,3,2]`，返回 `23`。
 * 输入:
 * 3 2 7 2 3
 * [4,3]
 * [1,3,2]
 * 输出:
 * 23
 *
 * 解释：
 * 2 * (7-1)+2*(6-2) + 3 * (4-3) = 23
 * 样例 2:
 *
 * 给出 k1 = `1`,k2 = `2`,c = ` 5`,n = `1`,m = `1`,a = `[2]`,b = `[1]`，返回 `10`。
 * 输入:
 * 1 2 5 1 1
 * [2]
 * [1]
 * 输出:
 * 10
 *
 * 解释：
 * 2 * (5-1)+1*(4-2) = 10
 */
public class backPack971 {
    /**
     * @param k1: The coefficient of A
     * @param k2: The  coefficient of B
     * @param c: The volume of backpack
     * @param n: The amount of A
     * @param m: The amount of B
     * @param a: The volume of A
     * @param b: The volume of B
     * @return: Return the max value you can get
     */
    public static long getMaxValue(int k1, int k2, int c, int n, int m, int[] a, int[] b) {
        // Write your code here
//        int[] f = new int[c+1];
//        int[] volume = new int[n+m];
//
//        for (int i=0; i<n+m; i++) {
//            volume[i] = i<n ? a[i] : b[i-n];
//        }
//
//        for (int i=1; i<=n+m; i++) {
//            for (int j=c; j>volume[i-1]; j--) {
//                if (i<=n) {
//                    f[j] = Math.max(f[j],f[j-volume[i-1]]+k1*(j-volume[i-1]));
//                }
//                else {
//                    f[j] = Math.max(f[j],f[j-volume[i-1]]+k2*(j-volume[i-1]));
//                }
//            }
//        }
//
//        return f[c];

        int[][] f = new int[n+m+1][c+1];
        int[] volume = new int[n+m];
        int max = 0;

        for (int i=0; i<n+m; i++) {
            volume[i] = i<n ? a[i] : b[i-n];
        }

        for (int i=1; i<=n+m; i++) {
            for (int j=volume[i-1]; j<=c; j++) {
                f[i][j] = f[i-1][j];
                if (i>n) {
                    f[i][j] = Math.max(f[i][j],f[i-1][j-volume[i-1]]+k2*(j-volume[i-1]));
                }
                else
                    f[i][j] = Math.max(f[i][j],f[i-1][j-volume[i-1]]+k1*(j-volume[i-1]));
            }
        }
        for (int k=0; k<=c; k++) {
            max = Math.max(max, f[m+n][k]);
        }
        return max;
    }

    public static void main(String[] args) {
      int k1 = 3;
      int k2 = 2;
      int c = 7;
      int n = 2;
      int m = 3;
      int[] a = {4,3};
      int[] b = {1,3,2};

      getMaxValue(k1, k2, c, n, m, a, b);
    }
}
