package java8.wxf.algorithm.DP;

/**
 * 798 · 背包问题VII  ---多重背包  ---可行性
 * 算法
 * 中等
 * 通过率
 * 59%
 *
 * 题目题解笔记讨论排名
 * 描述
 * 假设你身上有 n 元，超市里有多种大米可以选择，每种大米都是袋装的，必须整袋购买，给出每种大米的重量，价格以及数量，求最多能买多少公斤的大米
 *
 * 样例
 * 样例 1:
 *
 * 输入:  n = 8, prices = [3,2], weights = [300,160], amounts = [1,6]
 * 输出:  640
 * 解释:  全买价格为2的米。
 * 样例 2:
 *
 * 输入:  n = 8, prices  = [2,4], weight = [100,100], amounts = [4,2 ]
 * 输出:  400
 * 解释:  全买价格为2的米
 */
public class backPackVII {
    // 法1
    public static int backPackVII(int n, int[] prices, int[] weight, int[] amounts) {
        // write your code here
        // 前i个数是组成目标值w的最大值
        // f[i][w] = max(f[i-1][w-k*prices[i-1]]+k*weight, f[i-1][w]){k=1,2,...amounts[i-1]&&k*weight<=n}
        int[][] f = new int[prices.length+1][n+1];
        for (int j=1; j<=prices.length; j++) {
            for (int i=n; i>0; i--) {
                int k = 0;
                while ((k<=amounts[j-1])&&(i>=k*prices[j-1])) {
                    f[j][i] = Integer.max(f[j][i], f[j-1][i-k*prices[j-1]]+k*weight[j-1]);
                    k++;
                }
            }
        }

        return f[prices.length][n];
    }

    // 法2
    public static void solve3(int n,int[] prices,int[] weight, int[] amounts) {
        int[][] dp = new int[prices.length + 1][n + 1];
        for (int i = 0; i < prices.length; i++){
            for (int j = 0; j <= n; j++){
                for (int k = 0; k <= amounts[i] && k * prices[i] <= j; k++){
                    dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j-k * prices[i]] + k * weight[i]);
                }
            }
        }
        System.out.println("最大价值为：" + dp[prices.length][n]);
    }
    // 优化空间为O(n)
    private static int ksp(int N,int[] prices,int[] weight, int[] amounts){
        int[] newResults = new int[N + 1];
        // 开始填表
        for (int m = 0; m < prices.length; m++){
            // 考虑第m个物品
            //TODO 分两种情况
            // 1： amounts[m] * prices[m] >= N 则可以当做完全背包问题来处理
            // 完全背包降低空间后 正序枚举j
            if (amounts[m] * prices[m] >= N) {
                for (int j = prices[m]; j <= N ; j++) {
                    newResults[j] = Math.max(newResults[j], newResults[j - prices[m]] + weight[m]);
                }
            } else {
                // 2： amounts[m] * prices[m] < N 则需要在 newResults[n-V[m]*k] + P[m] * k 中找到最大值，0 <= k <= M[m]
                // 多重背包降低空间后 逆序枚举j
                for (int j = N; j >= prices[m] ; j--) {
                    int k = 1;
                    while (k <= amounts[m] && j >= prices[m] * k ){
                        newResults[j] = Math.max(newResults[j], newResults[j - prices[m] * k] + weight[m] * k);
                        k++;
                    }
                }
            }
        }
        return newResults[N];
    }
    public static void main(String[] args) {
        int n = 8;
//        int[] prices = {2,4};
//        int[] weight = {100,100};
//        int[] amounts = {4,2};
        int[] prices = {3,2};
        int[] weight = {300,160};
        int[] amounts = {1,6};
//        backPackVII(n, prices, weight, amounts);
//        solve3(n,prices,weight,amounts);
        ksp(n,prices,weight,amounts);
    }

}
