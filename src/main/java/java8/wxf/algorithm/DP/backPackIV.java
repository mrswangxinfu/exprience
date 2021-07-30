package java8.wxf.algorithm.DP;

/**
 * 564 · 组合总和 IV ---最值型
 * 算法
 * 中等
 * 通过率
 * 45%
 *
 * 题目题解笔记讨论排名
 * 描述
 * 给出一个都是正整数的数组 nums，其中没有重复的数。从中找出所有的和为 target 的组合个数。
 *
 * 一个数可以在组合中出现多次。
 * 数的顺序不同则会被认为是不同的组合。
 *
 * 样例
 * 样例1
 *
 * 输入: nums = [1, 2, 4] 和 target = 4
 * 输出: 6
 * 解释:
 * 可能的所有组合有：
 * [1, 1, 1, 1]
 * [1, 1, 2]
 * [1, 2, 1]
 * [2, 1, 1]
 * [2, 2]
 * [4]
 * 样例2
 *
 * 输入: nums = [1, 2] 和 target = 4
 * 输出: 5
 * 解释:
 * 可能的所有组合有：
 * [1, 1, 1, 1]
 * [1, 1, 2]
 * [1, 2, 1]
 * [2, 1, 1]
 * [2, 2]
 */
public class backPackIV {
    /**
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackVI(int[] nums, int target) {
        // write your code here
        // f[i][j] = sum(f[i-1][j-k*nums[i-1]]){k=0,1,2...target/nums[i-1]}
        // i is nums's index,j is one of {0,1,2...target}
        // f[i][j] is how much species prevous i-numbers make up j
        //将target值放在最外循环，可以顺序计算k从0到target/nums[i-1]的值
        int[] f = new int[target+1];
        f[0] = 1;
        for (int i=1; i<=target; i++) {
            for (int j=1; j<=nums.length; j++) {
                if (i>=nums[j-1]) {
                    f[i]+=f[i-nums[j-1]];
                }
            }
        }
        return f[target];
    }
}
