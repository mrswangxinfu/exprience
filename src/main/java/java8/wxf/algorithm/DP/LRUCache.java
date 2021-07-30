package java8.wxf.algorithm.DP;

import java.util.*;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *  
 *
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *  
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *  
 *
 * 提示：
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 3000
 * 0 <= value <= 104
 * 最多调用 3 * 104 次 get 和 put
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 *
 *
 * 解析： 使用双向链表+哈希表 O(1)时间解决
 */

public class LRUCache {

    private Map<Integer, Integer> map;
    private Map<Integer, SelfLinkedList.Node<Integer,Integer>> cacheMap;
    private int capacity;
    private final int oldIndex = 0;
    // 将list用作栈
    private List<Integer> stack = new ArrayList<>();
    private SelfLinkedList<Integer, Integer> stackLink;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        stackLink = new SelfLinkedList<>();
        cacheMap = new HashMap<>();
        this.capacity = capacity;
    }
    @Deprecated
    // O(n)会超时,应使用O(1)
    public int getOld(int key) {
        Integer value = map.get(key);
        if (null != value) {
            for (int i = 0; i < stack.size(); i++) {
                if (key == stack.get(i)) {
                    stack.remove(i);
                    break;
                }
            }
            stack.add(key);
            System.out.println(value);
            return value;
        }
        System.out.println(-1);
        return -1;
    }
    @Deprecated
    public void putOld(int key, int value) {
        Integer oldValue = map.put(key, value);
        if (null == oldValue) {
            if (--capacity < 0) {
                Integer old = stack.remove(oldIndex);
                map.remove(old);
                capacity = 0;
            }
        } else {
            for (int i = 0; i < stack.size(); i++) {
                if (key == stack.get(i)) {
                    stack.remove(i);
                    break;
                }
            }
        }
        stack.add(key);
        System.out.println("null");
    }

    public int get(int key) {
        /**
         * 1、根据key获取 node, 有则将链表中的key删除，再从尾部添加，并返回key对应的值
         * 2、如果没有则直接返回-1；
         */
        SelfLinkedList.Node<Integer,Integer> oldNode = cacheMap.get(key);
        // 存在
        if (null != oldNode) {
            // O(1)时间删除
            stackLink.remove(oldNode);
            stackLink.add(oldNode);
            System.out.println(oldNode.item);
            return oldNode.item;
        }
        System.out.println(-1);
        // 下面方法同上
//        if (cacheMap.containsKey(key)) {
//            Integer val = cacheMap.get(key).item;
//            put(key, val);
//            System.out.println(val);
//            return val;
//        }
//        System.out.println(-1);
        return -1;
    }

    public void  put(int key, int value) {
        /**
         * 节点尾部存最近使用，头部存很久没有使用
         *
         * 1、创建新节点
         * 2、将节点放入map中，若存在则修改为新节点的值，并根据老的node删除链表对应的节点；
         *    不存在则添加.并判断容量是否还有，没有则删除链表的头节点，将容量置0
         * 3、将节点添加到尾部
         */
        SelfLinkedList.Node<Integer, Integer> node = new SelfLinkedList.Node<>(key,value);
        // hashMap的put方法对于已经有数据的操作具有set的效果
        SelfLinkedList.Node<Integer, Integer> old = cacheMap.put(key,node);
        if (old == null) {
            if (--capacity < 0) {
                cacheMap.remove(stackLink.remove().key);
                capacity = 0;
            }
        }
        else {
            stackLink.remove(old);
        }
        stackLink.add(node);
        System.out.println("null");
        // 下面方法同上
//        SelfLinkedList.Node<Integer,Integer> node = new SelfLinkedList.Node<>(key,value);
//        if (cacheMap.containsKey(key)) {
//            stackLink.remove(cacheMap.get(key));
//        } else {
//            if (--capacity<0) {
//                SelfLinkedList.Node<Integer,Integer> f = stackLink.remove();
//                cacheMap.remove(f.key);
//            }
//        }
//        stackLink.add(node);
//        cacheMap.put(key,node);
//        System.out.println("null");
    }
    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);
//        cache.put(2,1);
//        cache.put(1,1);
//        cache.put(2,3);
//        cache.put(4,1);
//        cache.get(1);
//        cache.get(2);
//        cache.get(2);
//        cache.put(2,6);
//        cache.get(1);
//        cache.put(1,5);
//        cache.put(1,2);
//        cache.get(1);
//        cache.get(2);
//        cache.put(3,2);
//["LRUCache","put","put","get","put","get","put","get","get","get"]
//[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]] null null 1 null -1 null -1 3 4
        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.put(3,3);
        cache.get(2);
        cache.put(4,4);
        cache.get(1);
        cache.get(3);
        cache.get(4);


    }
    /** 自定义双向链表*/
    private static class SelfLinkedList<K, E> {

        private transient Node<K, E> first;
        private transient Node<K, E> last;
        private transient int size = 0;
        private static class Node<K, E> {
            Node<K, E> pre;
            Node<K, E> next;
            E item;
            K key;
            public Node(Node<K, E> pre, K key, E element, Node<K, E> next) {
                this.pre = pre;
                this.item = element;
                this.next = next;
                this.key = key;
            }

            public Node(K key, E element) {
                this.item = element;
                this.key = key;
            }

            public String toString() {
                return "Node{key: " + key + " , value: " + item + "}";
            }
        }

        public boolean add(Node<K,E> node) {
            linkLast(node);
            return true;
        }
        private void linkLast(Node<K,E> n) {
            final Node<K, E> l = last;
            last = n;
            if (l == null) {
                first = n;
            }
            else {
//                if (l.pre == null) {
//                    first = l;
//                }
                l.next = n;
                n.pre = l;
            }
            size++;
        }
        public void remove(Node<K,E> node) {
            /** 删除节点 考虑 :
             * 1、当前节点是头节点，但不是尾节点
             * 2、当前节点是尾节点，但不是头节点
             * 3、当前节点是头节点又是尾节点(当前节点是链表中唯一节点)
             * 4、当前节点不是尾节点也不是头节点，即 不是唯一节点
             */
            Node<K,E> pre = node.pre;
            Node<K,E> next = node.next;
            // 当前节点是头节点，但不是尾节点
            if (node == first && node != last) {
                first = next;
                node.next = null;
            }
            // 当前节点是尾节点，但不是头节点
            else if (node == last && node != first) {
                last = pre;
                node.pre = null;
            }
            // 当前节点是头节点又是尾节点(当前节点是链表中唯一节点)
            else if (pre == null && next == null) {
                first = null;
                last = null;
            }
            // 当前节点不是尾节点也不是头节点，即 不是唯一节点
            else {
                pre.next = next;
                next.pre = pre;
                node.pre = null;
                node.next = null;
            }

            // 下面同理
//            if (first == node && last == node) {
//                first = null;
//                last = null;
//            } else if (last == node) {
//                node.pre.next = null;
//                last = node.pre;
//            } else if (first == node) {
//                node.next.pre = null;
//                first = node.next;
//            } else {
//                node.pre.next = node.next;
//                node.next.pre = node.pre;
//            }
            size--;
        }
        public Node<K, E> remove() {
            return removeFirst();
        }
        private Node<K,E> removeFirst(){
            if (first == null) {
                throw new NoSuchElementException();
            }
            Node<K,E> oldNode = first;
            Node<K,E> next = first.next;

            if (next == null) {
                last = null;
                first = null;
            } else {
                first.next = null;
                first = next;
                next.pre = null;
            }
            size--;
            return oldNode;
        }

        public void remove(int index) {
            checkElementIndex(index);
            unlink(node(index));
        }
        private void unlink(Node<K,E> n) {
            Node<K,E> pre = n.pre;
            Node<K,E> next = n.next;
            if (null == pre) {
                first = next;
            } else {
                pre.next = next;
                n.next = null;
            }

            if (null == next) {
                last = pre;
            } else {
                next.pre = pre;
                n.pre = null;
            }
            n.item = null;
            n.key = null;
            size--;
        }
        public boolean add(int index, Node<K,E> n ) {
            checkPositionIndex(index);
            if (size == index) {
                linkLast(n);
                return true;
            }
            else if (0 == index) {
                linkFirst(n);
                return true;
            }
            else {
                Node<K,E> node = node(index-1);
                Node<K,E> next = node.next;
                node.next = n;
                next.pre = n;
            }
            size++;
            return true;
        }
        public Node<K, E> get(int index) {
            checkElementIndex(index);
            return node(index);
        }
        public Node<K,E> set(int index, Node<K,E> n) {
            checkElementIndex(index);
            Node<K, E> oldNode = node(index);
            oldNode.pre.next = n;
            n.pre = oldNode.next;
            oldNode.next = null;
            oldNode.pre = null;
            return oldNode;
        }
        public int size(){
            return size;
        }
        private void linkFirst(Node<K,E> n) {
            final Node<K, E> f = first;
            first = n;
            if (f == null) {
                last = n;
            }
            else {
                f.pre = n;
                n.next = f;
            }
            size++;
        }
        Node<K,E> node(int index) {
            if (index >= (size >> 1)) {
                Node<K,E> x = last;
                for (int i = size - 1; i > index; i--)
                    x = x.pre;
                return x;
            }
            else {
                Node<K,E> x = first;
                for (int i = 0; i < index; i++)
                    x = x.next;
                return x;
            }
        }

        private void checkElementIndex(int index) {
            if (!isElementIndex(index)) {
                throw new IndexOutOfBoundsException(outOfBoundMessage(index));
            }
        }

        private boolean isElementIndex(int index) {
            return index >= 0 && index < size;
        }
        private boolean isPositionIndex(int index) {
            return index >= 0 && index <= size;
        }
        private String outOfBoundMessage(int index) {
            return "Index: " + index + ", Size: " + size;
        }
        private void checkPositionIndex(int index) {
            if (!isPositionIndex(index)) {
                throw new IndexOutOfBoundsException(outOfBoundMessage(index));
            }
        }
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            Node<K,E> f = first;
            for (int i = 0; i < size; i++) {
                builder.append(f.toString());
                if (i != size - 1) {
                    builder.append(", ");
                }
                f = f.next;
            }
            builder.append("]");
            return builder.toString();
        }
    }
}
