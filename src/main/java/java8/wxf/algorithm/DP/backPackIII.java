package java8.wxf.algorithm.DP;

/**
 * ---完全背包 ---可行性型
 * 描述
 * 给定 n 种物品, 每种物品都有无限个. 第 i 个物品的体积为 A[i], 价值为 V[i].
 *
 * 再给定一个容量为 m 的背包. 问可以装入背包的最大价值是多少?
 *
 * 不能将一个物品分成小块.
 * 放入背包的物品的总大小不能超过 m.
 * 样例
 * 样例 1:
 *
 * 输入: A = [2, 3, 5, 7], V = [1, 5, 2, 4], m = 10
 * 输出: 15
 * 解释: 装入三个物品 1 (A[1] = 3, V[1] = 5), 总价值 15.
 * 样例 2:
 *
 * 输入: A = [1, 2, 3], V = [1, 2, 3], m = 5
 * 输出: 5
 * 解释: 策略不唯一. 比如, 装入五个物品 0 (A[0] = 1, V[0] = 1).
 */
public class backPackIII {
    /**
     * @param A: an integer array
     * @param V: an integer array
     * @param m: An integer
     * @return: an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        // write your code here
        // f[i][j] = max(f[i-k*A[j-1]][j-1]+k*V[j-1],f[i][j-1]) {k=0,1,2...m/A[j-1]}
        int[] f = new int[m+1];
        for (int i=1; i<=m; i++) {
            for (int j=1; j<=A.length; j++) {
                if ((i-A[j-1])>=0) {
                    f[i]=Integer.max(f[i-A[j-1]]+V[j-1],f[i]);
                }
            }
        }
        return f[m];
    }
}
