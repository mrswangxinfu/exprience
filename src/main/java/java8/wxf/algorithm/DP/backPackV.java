package java8.wxf.algorithm.DP;

/**
 * 563 · 背包问题 V ---最值型
 * 算法
 * 中等
 * 通过率
 * 57%
 *
 * 题目题解笔记讨论排名
 * 描述
 * 给出 n 个物品, 以及一个数组, nums[i] 代表第i个物品的大小, 保证大小均为正数, 正整数 target 表示背包的大小, 找到能填满背包的方案数。
 * 每一个物品只能使用一次
 *
 * 样例
 * 给出候选物品集合 [1,2,3,3,7] 以及 target 7
 *
 * 结果的集合为:
 * [7]
 * [1,3,3]
 * 返回 2
 */
public class backPackV {
    public static int backPackV(int[] nums, int target) {
        // write your code here
        // f[i][j] is how much species prevous i-numbers make up target
        int[][] f = new int[nums.length+1][target+1];
        for (int i=0;i<=nums.length;i++) {
            for (int j=0; j<=target; j++) {
                // 初始化 即目标为0时，前i个数都有一种方法使目标为0，就是什么都不取
                if (j==0) {
                    f[i][j] = 1;
                    continue;
                }
                if (i>0) {
                    f[i][j] = f[i-1][j];
                    if (j-nums[i-1]>=0) {
                        f[i][j] +=f[i-1][j-nums[i-1]];
                    }
                }
            }
        }
        return f[nums.length][target];
    }
    // 优化空间复杂度为O(n)
    public static int backPackVOptimize(int[] nums, int target) {

        return -1;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,3,7};
        int target = 7;
        backPackV(nums,target);
    }
}
