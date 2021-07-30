package java8.wxf.algorithm.DP;

/**
 * 92 · 背包问题  ---0-1背包 ---可行性型
 * 算法
 * 中等
 * 通过率
 * 35%
 * 描述
 * 在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A_{i}A
 * i
 * ​
 *
 *
 * 你不可以将物品进行切割。
 *
 * 样例
 * 样例 1：
 *
 * 输入：
 *
 * 数组 = [3,4,8,5]
 * backpack size = 10
 * 输出：
 *
 * 9
 * 解释：
 *
 * 装4和5.
 *
 * 样例 2：
 *
 * 输入：
 *
 * 数组 = [2,3,5,7]
 * backpack size = 12
 * 输出：
 *
 * 12
 * 解释：
 *
 * 装5和7.
 *
 * 挑战
 * O(n x m) 的时间复杂度 and O(m) 空间复杂度
 * 如果不知道如何优化空间，O(n x m) 的空间复杂度也可以通过.
 */
public class backPackI {
    public static int backPack(int m, int[] A) {
        // write your code here
        // f[i][j] is previous j-numbers max sum of A for limit i
//        int[][] f = new int[m+1][A.length+1];
//        int max;
//        for (int i=0; i<m+1; i++) {
//            max = 0;
//            f[i][0] = 0;
//            for (int j=1; j<=A.length; j++) {
//                f[i][j] = max;
//                if ((i-A[j-1])>=0) {
//                    f[i][j] = Integer.max(f[i-A[j-1]][j-1] + A[j-1],max) ;
//                    max = f[i][j];
//                }
//            }
//        }
//        return f[m][A.length];

        // 顺序不同代表的含义不同，f[i][w] 前i个物品是否可以拼出w大小
        int[][] f = new int[A.length+1][m+1];
        for (int j=1; j<=A.length; j++) {
            for (int i=0; i<m+1; i++) {
                f[j][i] = f[j-1][i];
                if ((i-A[j-1])>=0) {
                    f[j][i] = Integer.max(f[j-1][i-A[j-1]] + A[j-1], f[j-1][i]) ;
                }
            }
        }
        return f[A.length][m];
    }
    // 优化空间复杂度为：O(n)
    public static int backPackOn(int m, int[] A) {
        int[] f = new int[m+1];
        for (int j=1; j<=A.length; j++) {
            // 因为后面的数会将前面的数覆盖所以需要倒序
            for (int i=m; i>0; i--) {
                if ((i-A[j-1])>=0) {
                    // 等号左边的f[i]是当前位置的值，等号右边的f[i]是所保留上一次的f[i]相当于二维数组中f[j-1][i]
                    f[i] = Integer.max(f[i-A[j-1]] + A[j-1], f[i]);
                }
            }
        }
        return f[m];
    }



    //f[i][w] 前i个物品是否可以拼出w大小
    // f[i][w] = f[i-1][w-A[i-1]]||f[i-1][w]
    public static int backPackBooleanWay(int m, int[] A) {
        boolean[][] f = new boolean[A.length+1][m+1];
        int max = 0;
        f[0][0] = true;// 大小为0的空间可以装0个物品；
        for (int j=1; j<=A.length; j++) {
            for (int i=0; i<=m; i++) {
                f[j][i] = f[j-1][i];
                if (i-A[j-1]>=0) {
                    f[j][i] = f[j-1][i-A[j-1]]||f[j][i];
                }
            }
        }
        for (int i=1; i<=m; i++) {
            if (f[A.length][i]) {
                max = Integer.max(max,i);
            }
        }
        return max;
    }



    // 优化空间复杂度为：O(n)
    // TODO： 有bug, 使用boolean进行不能将空间复杂度降为O(n),
    //  因为要保存当前状态，而boolean只有两种状态不能区分上一次的所有状态
    public static int backPackBooleanWayII(int m, int[] A) {
        boolean[][] f = new boolean[2][m+1];
        int max = 0, old=1,now=0;
        f[0][0] = true;
        for (int j=1; j<=A.length; j++) {

            // 使用滚动数组 故逆序执行
            for (int i=m; i>=0; i--) {
                f[old][i] = f[now][i];
                if (i-A[j-1]>=0) {
                    f[old][i] = f[now][i-A[j-1]]||f[old][i];
                }
                old = now;
                now = 1-now;
            }
        }
        for (int j=0; j<2; j++) {
            for (int i=1; i<=m; i++) {
                if (f[j][i]) {
                    max = Integer.max(max,i);
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] A = {3,5,8,3};
        System.out.println( backPackOn(10,A));
//        backPackBooleanWayII(10,A);
    }
}
