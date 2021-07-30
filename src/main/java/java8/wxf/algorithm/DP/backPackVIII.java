package java8.wxf.algorithm.DP;

import java.util.ArrayList;
import java.util.List;

/**
 * 799 · 背包问题VIII ---多重背包 ---最值型
 * 算法
 * 中等
 * 通过率
 * 38%
 *
 * 题目题解笔记讨论排名
 * 描述
 * 给一些不同价值和数量的硬币。找出[1，n]范围内的总值有多少种形成方式？
 *
 * 样例
 * 样例 1:
 * 	输入:
 * 		n = 5
 * 		value = [1,4]
 * 		amount = [2,1]
 * 	输出:  4
 *
 * 	解释:
 * 	可以组合出1，2，4，5
 *
 * 样例 2:
 * 	输入:
 * 		n = 10
 * 		value = [1,2,4]
 * 		amount = [2,1,1]
 * 	输出:  8
 *
 * 	解释:
 * 	可以组合出1-8所有数字。
 */
public class backPackVIII {
    /**
     * @param n: the value from 1 - n
     * @param value: the value of coins
     * @param amount: the number of coins
     * @return: how many different value
     */

    public static int backPackVIII(int n, int[] value, int[] amount) {
        //    boolean[][] f = new boolean[value.length+1][n+1];
        //     int type = 0;
        //     f[value.length][0] = true;
        //     for (int i=1; i<=value.length;i++) {
        //         f[i-1][0] = true;
        //         for (int j=n; j>=value[i-1]; j--) {
        //             int k=1;
        //             f[i][j] = f[i-1][j];
        //             while ((k<=amount[i-1])&&(j-k*value[i-1]>=0)) {
        //                 f[i][j] = f[i][j]||f[i-1][j-k*value[i-1]];
        //                 k++;
        //             }
        //         }
        //     }
        //     for (int i=1; i<=value.length; i++) {
        //         for (int j=1; j<=n; j++) {
        //             if (f[i][j]) {
        //                 type++;
        //             }
        //         }
        //     }
        //     return type;
        // TODO 此方法超时，有1000多ms
        int[] f = new int[n+1];// 用于记录1-n出现了哪些数
        int[] p = new int[n+1];

        for (int i=1; i<=value.length;i++) {
            // amount[i-1]*value[i-1]>=n则变成完全背包问题
            if (amount[i-1]*value[i-1]>=n) {
                for (int j=1; j<=n; j++) {
                    if (j-value[i-1]>=0) {
                        p[j] = Math.max(p[j],p[j-value[i-1]]+value[i-1]);
                        f[p[j]] = p[j];
                    }
                }
            }
            // 否则多重背包
            else {
                for (int j=n; j>=1; j--) {
                    int k=1;
                    while ((k<=amount[i-1])&&(j-k*value[i-1]>=0)) {
                        p[j] = Math.max(p[j],p[j-k*value[i-1]]+k*value[i-1]);
                        f[p[j]] = p[j];
                        k++;
                    }
                }
            }
        }
        // 利用f[0]计数
        for (int i=1; i<f.length; i++) {
          if (f[i]!=0) {
              f[0]++;
          }
        }
        return f[0];
    }

    // 转0-1背包处理
    // TODO 时间也超时
    public static int backPackVIII01(int n, int[] value, int[] amount) {
        List<Integer> newValue = new ArrayList<>();
        int[] f = new int[n+1];
        int[] p = new int[n+1];
        for (int i=0; i<value.length; i++) {
            for (int j=1; j<=amount[i]&&value[i]*j<=n; j++) {
                newValue.add(value[i]);
            }
        }

        for (int i=1; i<=newValue.size(); i++) {
            // 0-1背包倒序优化空间复杂度为O(n)
            for (int j=n; j>0; j--) {
                if (j-newValue.get(i-1)>=0) {
                    p[j] = Math.max(p[j],p[j-newValue.get(i-1)]+newValue.get(i-1));
                    if (f[p[j]] != p[j]) {
                        f[p[j]] = p[j];
                        f[0]++;
                    }
                }
            }
        }
        return f[0];
    }

    // 二进制优化时间复杂度至O(N*M*lg(amount))
    public static int backPackVIIIBinary(int n, int[] value, int[] amount) {
        List<Integer> newValue = new ArrayList<>();
        int[] f = new int[n+1];
        int[] p = new int[n+1];
        for (int i=0; i<value.length; i++) {
            // 把每种的数量amount[i]拆分为二进制（2的指数倍相加），
            // 比如13 = 1+2+4+6，除了余数6其他都是2的指数倍；
            for (int binary=1; binary<amount[i]; binary<<=1) {
                amount[i] -= binary;
                newValue.add(binary*value[i]);
            }
            // 判断余数
            if (amount[i]>0) {
                newValue.add(amount[i]*value[i]);
            }
        }

        for (int i=1; i<=newValue.size(); i++) {
            // 0-1背包倒序优化空间复杂度为O(n)
            for (int j=n; j>0; j--) {
                if (j-newValue.get(i-1)>=0) {
                    p[j] = Math.max(p[j],p[j-newValue.get(i-1)]+newValue.get(i-1));
                    if (f[p[j]] != p[j] && p[j]!=0) {
                        f[p[j]] = p[j];
                        ++f[0];
                    }
                }
            }
        }
        return f[0];
    }
    // 单调队列优化 O(M*N)
    public static int backPackVIIIQueue(int n, int[] value, int[] amount) {

        return 0;
    }
    public static void main(String[] args) {
//        int n = 10;
//        int[] value = {2,1,1};
//        int[] amount = {1,2,4};
        int n = 9069;
        int[] value = {880,548,836,568,525,605,198,187,290,286,551,247,52,428,119,101,62,591,416,369};
        int[] amount = {690,572,719,592,402,453,829,73,458,853,538,255,548,520,520,310,780,806,91,143};
//        System.out.println(backPackVIII(n, value, amount));
//        backPackVIII01(n, value, amount);
        backPackVIIIBinary(n,value,amount);
    }
}
