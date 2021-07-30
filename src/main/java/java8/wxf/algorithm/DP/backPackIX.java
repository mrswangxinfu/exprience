package java8.wxf.algorithm.DP;

/**
 * 800 · 背包问题 IX --0-1背包变种
 * 算法
 * 中等
 * 通过率
 * 51%
 *
 * 题目题解笔记讨论排名
 * 描述
 * 你总共有n 万元，希望申请国外的大学，要申请的话需要交一定的申请费用，给出每个大学的申请费用以及你得到这个大学offer的成功概率，大学的数量是 m。如果经济条件允许，你可以申请多所大学。找到获得至少一份工作的最高可能性。
 *
 * 0<=n<=10000,0<=m<=10000
 *
 * 样例
 * 样例 1:
 * 	输入:
 * 		n = 10
 * 		prices = [4,4,5]
 * 		probability = [0.1,0.2,0.3]
 * 	输出:  0.440
 *
 * 	解释：
 * 	选择第2和第3个学校。
 *
 * 样例 2:
 * 	输入:
 * 		n = 10
 * 		prices = [4,5,6]
 * 		probability = [0.1,0.2,0.3]
 * 	输出:  0.370
 *
 * 	解释:
 * 	选择第1和第3个学校。
 *
 * 	解析：成功概率=（1-失败概率1）*（1-失败概率2）*...
 */
public class backPackIX {
    /**
     * @param n: Your money
     * @param prices: Cost of each university application
     * @param probability: Probability of getting the University's offer
     * @return: the  highest probability
     */
    public static double backpackIX(int n, int[] prices, double[] probability) {
        // write your code here
        double[] f = new double[n+1];
        for (int i=0; i<=n; i++) {
            f[i] = 1;
        }
        for (int i=1; i<=prices.length; i++) {
            // 0-1背包 倒序优化空间复杂度
            for (int j=n; j>prices[i-1]; j--) {
                // 寻找失败概率最小
                f[j] = Math.min(f[j],f[j-prices[i-1]]*(1-probability[i-1]));
            }
        }

        return 1-f[n];
    }

    public static void main(String[] args) {
       int n = 10;
       int[] prices = {4,4,5};
       double[] probability = {0.1,0.2,0.3};
       double successProbability = backpackIX(n,prices,probability);
        System.out.println(successProbability);
    }
}
