package java8.wxf.algorithm.DP;

/**
 * 给定一个链表，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 *  
 *
 * 进阶：
 *
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 */
public class HasCycle {
    // 快慢指针解决环，快指针是慢指针速度的二倍，若有环则会重合
    public static boolean hasCycle(ListNode<Integer> head) {
        ListNode<Integer> slow;
        ListNode<Integer> fast = head;
        while ((head)!=null) {
            // 慢指针走一步
            slow = head.next;
            if (null==fast.next||null==fast.next.next) {
                break;
            }
            // 快指针走两步
            fast = fast.next.next;
            if (slow==fast) {
                return true;
            }
        }
        return false;
    }
    public static class ListNode<T> {
        ListNode<T> next;
        T value;
        public ListNode(T value, ListNode<T> next) {
            this.next = next;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(3,null);
        head.next = new ListNode<>(2,null);
        head.next.next = new ListNode<>(0,null);
        head.next.next.next = new ListNode<>(-4,null);
        head.next.next.next.next = head.next;
//        while (head!=null) {
//            System.out.println(head.value);
//            head = head.next;
//        }
        boolean is = hasCycle(head);
        System.out.println(is);
    }
}
