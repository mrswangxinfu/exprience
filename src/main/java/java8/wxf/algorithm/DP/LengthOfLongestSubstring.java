package java8.wxf.algorithm.DP;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 *
 * 输入: s = ""
 * 输出: 0
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 *
 *
 * 解题思路：1、滑动窗口（双指针）
 *          2、桶填充清除（ASCII表）
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
//        System.out.println(slidingWindow("abcabcbb") );
        System.out.println(slidingWindowPlus("1231jkppo"));
    }

    // 滑动窗口法，解决！TODO: 有待优化
    public static int slidingWindow(String s) {
        char[] arrChar = s.toCharArray();
        // 使用前后指针作为滑动窗口的左右边界，max保存当前最长子串（滑动窗口的最大宽度）的长度
        int pre=0,next=1,max = 1;
        Set<Character> set = new HashSet<>();
        // 考虑边界
        if (arrChar.length == 0) {
            return 0;
        }
        // 初始化时，将数组第一个字符放入set中
        for (set.add(arrChar[pre]); next < arrChar.length; ) {
            if ((arrChar[pre] != arrChar[next]) && set.add(arrChar[next])) {
                max = Math.max(max, set.size());
            }
            // 当窗口内存在重复字符时
            else {
                for (;;) {
                    if (arrChar[pre] == arrChar[next]) {
                        // 集合置空
                        set.clear();
                        int p = pre;
                        while (p < next) {
                            set.add(arrChar[++p]);
                        }
                        // 将左指针移动到与右指针相等的下一个位置
                        pre++;
                        break;
                    }
                    pre++;
                }

            }
            next++;
        }

        return max;
    }

    /**
     * 优化滑动窗口
     *
     * 规律：
     * 以 {(a)bcabcbb}(a)bcabcbb 开始的最长字符串为 {(abc)abcbb}(abc)abcbb；
     * 以 {a(b)cabcbb}a(b)cabcbb 开始的最长字符串为 {a(bca)bcbb}a(bca)bcbb；
     * 以 {ab(c)abcbb}ab(c)abcbb 开始的最长字符串为 {ab(cab)cbb}ab(cab)cbb；
     * 以 {abc(a)bcbb}abc(a)bcbb 开始的最长字符串为 {abc(abc)bb}abc(abc)bb；
     * 以 {abca(b)cbb}abca(b)cbb 开始的最长字符串为 {abca(bc)bb}abca(bc)bb；
     * 以 {abcab(c)bb}abcab(c)bb 开始的最长字符串为 {abcab(cb)b}abcab(cb)b；
     * 以 {abcabc(b)b}abcabc(b)b 开始的最长字符串为 {abcabc(b)b}abcabc(b)b；
     * 以 {abcabcb(b)}abcabcb(b) 开始的最长字符串为 {abcabcb(b)}abcabcb(b)。
     *
     * @param s 字符串
      * @return int
     */
    public static int slidingWindowPlus(String s) {
        char[] arrChar = s.toCharArray();

        // 左指针,指向开始的字符串，作为窗口的左边界，当出现重复字符时，重复字符的前一个字符就是窗口右边界
        int left = -1;
        int max = 0;
        Set<Character> set = new HashSet<>();
        // 临界判断
//        if (arrChar.length == 0) {
//            return 0;
//        }

        // 比较以i开始的字符子串的最大长度，i>=0 && i<arrChar.length
        for (int i = 0; i < arrChar.length; i++) {
            if (i != 0) {
                // 每次都先移除最左边的字符
                set.remove(arrChar[i-1]);
            }
            // 若不出现重复字符则不断向右移动指针
            while ( left+1 < arrChar.length && set.add(arrChar[left+1])) {
                ++left;
            }
            // max表示（以i开始的字符串的最大长度）与 之前max的比较
            max = Math.max(max, set.size());
        }

        return max;
    }
}
