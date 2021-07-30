package java8.wxf.algorithm.DP;

/**
 * 562 · 背包问题 IV ---最值型
 * 算法
 * 中等
 * 通过率
 * 60%
 *
 * 题目题解笔记讨论排名
 * 描述
 * 给出 n 个物品, 以及一个数组, nums[i]代表第i个物品的大小, 保证大小均为正数并且没有重复, 正整数 target 表示背包的大小, 找到能填满背包的方案数。
 * 每一个物品可以使用无数次
 *
 * 样例
 * 样例1
 *
 * 输入: nums = [2,3,6,7] 和 target = 7
 * 输出: 2
 * 解释:
 * 方案有:
 * [7]
 * [2, 2, 3]
 * 样例2
 *
 * 输入: nums = [2,3,4,5] 和 target = 7
 * 输出: 3
 * 解释:
 * 方案有:
 * [2, 5]
 * [3, 4]
 * [2, 2, 3]
 */
public class backPackIV562 {
    /**
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackIV(int[] nums, int target) {
        // write your code here
        int[] f = new int[target+1];
        // 等同二维数组的f[i][0]=1, 即前i个数都有一种方法放入0容量的背包，就是什么都不放。
        f[0] = 1;
        for (int i=1; i<=nums.length; i++) {
            // 最值型，需要顺序计算
            for (int j=1; j<=target; j++) {
                if (j-nums[i-1]>=0) {
                    f[j] = f[j] + f[j-nums[i-1]];
                }
            }
        }
        return f[target];
    }
}
