package java8.wxf.dataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 若n可以表示为2的i次幂，则对任意整数m取余等价于（m&(n-1)）
 *
 *
 *
 * final Node<K,V>[] resize() {
 *         //oldTab 为当前表的哈希桶
 *         Node<K,V>[] oldTab = table;
 *         //当前哈希桶的容量 length
 *         int oldCap = (oldTab == null) ? 0 : oldTab.length;
 *         //当前的阈值
 *         int oldThr = threshold;
 *         //初始化新的容量和阈值为0
 *         int newCap, newThr = 0;
 *         //如果当前容量大于0
 *         if (oldCap > 0) {
 *             //如果当前容量已经到达上限
 *             if (oldCap >= MAXIMUM_CAPACITY) {
 *                 //则设置阈值是2的31次方-1
 *                 threshold = Integer.MAX_VALUE;
 *                 //同时返回当前的哈希桶，不再扩容
 *                 return oldTab;
 *             }//否则新的容量为旧的容量的两倍。
 *             else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
 *                      oldCap >= DEFAULT_INITIAL_CAPACITY)//如果旧的容量大于等于默认初始容量16
 *                 //那么新的阈值也等于旧的阈值的两倍
 *                 newThr = oldThr << 1; // double threshold
 *         }//如果当前表是空的，但是有阈值。代表是初始化时指定了容量、阈值的情况
 *         else if (oldThr > 0) // initial capacity was placed in threshold
 *             newCap = oldThr;//那么新表的容量就等于旧的阈值
 *         else {}//如果当前表是空的，而且也没有阈值。代表是初始化时没有任何容量/阈值参数的情况               // zero initial threshold signifies using defaults
 *             newCap = DEFAULT_INITIAL_CAPACITY;//此时新表的容量为默认的容量 16
 *             newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);//新的阈值为默认容量16 * 默认加载因子0.75f = 12
 *         }
 *         if (newThr == 0) {//如果新的阈值是0，对应的是  当前表是空的，但是有阈值的情况
 *             float ft = (float)newCap * loadFactor;//根据新表容量 和 加载因子 求出新的阈值
 *             //进行越界修复
 *             newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
 *                       (int)ft : Integer.MAX_VALUE);
 *         }
 *         //更新阈值
 *         threshold = newThr;
 *         @SuppressWarnings({"rawtypes","unchecked"})
 *         //根据新的容量 构建新的哈希桶
 *             Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
 *         //更新哈希桶引用
 *         table = newTab;
 *         //如果以前的哈希桶中有元素
 *         //下面开始将当前哈希桶中的所有节点转移到新的哈希桶中
 *         if (oldTab != null) {
 *             //遍历老的哈希桶
 *             for (int j = 0; j < oldCap; ++j) {
 *                 //取出当前的节点 e
 *                 Node<K,V> e;
 *                 //如果当前桶中有元素,则将链表赋值给e
 *                 if ((e = oldTab[j]) != null) {
 *                     //将原哈希桶置空以便GC
 *                     oldTab[j] = null;
 *                     //如果当前链表中就一个元素，（没有发生哈希碰撞）
 *                     if (e.next == null)
 *                         //直接将这个元素放置在新的哈希桶里。
 *                         //注意这里取下标 是用 哈希值 与 桶的长度-1 。 由于桶的长度是2的n次方，这么做其实是等于 一个模运算。但是效率更高
 *                         newTab[e.hash & (newCap - 1)] = e;
 *                         //如果发生过哈希碰撞 ,而且是节点数超过8个，转化成了红黑树（暂且不谈 避免过于复杂， 后续专门研究一下红黑树）
 *                     else if (e instanceof TreeNode)
 *                         ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
 *                     //如果发生过哈希碰撞，节点数小于8个。则要根据链表上每个节点的哈希值，依次放入新哈希桶对应下标位置。
 *                     else { // preserve order
 *                         //因为扩容是容量翻倍，所以原链表上的每个节点，现在可能存放在原来的下标，即low位， 或者扩容后的下标，即high位。 high位=  low位+原哈希桶容量
 *                         //低位链表的头结点、尾节点
 *                         Node<K,V> loHead = null, loTail = null;
 *                         //高位链表的头节点、尾节点
 *                         Node<K,V> hiHead = null, hiTail = null;
 *                         Node<K,V> next;//临时节点 存放e的下一个节点
 *                         do {
 *                             next = e.next;
 *                             //这里又是一个利用位运算 代替常规运算的高效点：
 *                             利用哈希值 与 旧的容量，可以得到哈希值去模后，是大于等于oldCap还是小于oldCap，等于0代表小于oldCap，应该存放在低位，否则存放在高位
 *                             利用oldCap是2的i次方的特性（即除了最高位是1其他为0的特性）比较hash与oldCap大小。
 *                             if ((e.hash & oldCap) == 0) {
 *                                 //给头尾节点指针赋值
 *                                 if (loTail == null)
 *                                     loHead = e;
 *                                 else
 *                                     loTail.next = e;
 *                                 loTail = e;
 *                             }//高位也是相同的逻辑
 *                             else {
 *                                 if (hiTail == null)
 *                                     hiHead = e;
 *                                 else
 *                                     hiTail.next = e;
 *                                 hiTail = e;
 *                             }//循环直到链表结束
 *                         } while ((e = next) != null);
 *                         //将低位链表存放在原index处，
 *                         if (loTail != null) {
 *                             loTail.next = null;
 *                             newTab[j] = loHead;
 *                         }
 *                         //将高位链表存放在新index处
 *                         if (hiTail != null) {
 *                             hiTail.next = null;
 *                             newTab[j + oldCap] = hiHead;
 *                         }
 *                     }
 *                 }
 *             }
 *         }
 *         return newTab;
 *     }
 *
 *
 *
 *  final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
 *                    boolean evict) {
 *         //tab存放 当前的哈希桶， p用作临时链表节点
 *         Node<K,V>[] tab; Node<K,V> p; int n, i;
 *         //如果当前哈希表是空的，代表是初始化
 *         if ((tab = table) == null || (n = tab.length) == 0)
 *             //那么直接去扩容哈希表，并且将扩容后的哈希桶长度赋值给n
 *             n = (tab = resize()).length;
 *         //如果当前index的节 点是空的，表示没有发生哈希碰撞。 直接构建一个新节点Node，挂载在index处即可。
 *         //这里再啰嗦一下，index 是利用 哈希值 & 哈希桶的长度-1，替代模运算
 *         if ((p = tab[i = (n - 1) & hash]) == null)
 *             tab[i] = newNode(hash, key, value, null);
 *         else {//否则 发生了哈希冲突。
 *             //e
 *             Node<K,V> e; K k;
 *             //如果哈希值相等，key也相等，则是覆盖value操作
 *             if (p.hash == hash &&
 *                 ((k = p.key) == key || (key != null && key.equals(k))))
 *                 e = p;//将当前节点引用赋值给e
 *             else if (p instanceof TreeNode)//红黑树暂且不谈
 *                 e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
 *             else {//不是覆盖操作，则插入一个普通链表节点
 *                 //遍历链表
 *                 for (int binCount = 0; ; ++binCount) {
 *                     if ((e = p.next) == null) {//遍历到尾部，追加新节点到尾部
 *                         p.next = newNode(hash, key, value, null);
 *                         //如果追加节点后，链表数量》=8，则转化为红黑树
 *                         if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
 *                             treeifyBin(tab, hash);
 *                         break;
 *                     }
 *                     //如果找到了要覆盖的节点
 *                     if (e.hash == hash &&
 *                         ((k = e.key) == key || (key != null && key.equals(k))))
 *                         break;
 *                     p = e;
 *                 }
 *             }
 *             //如果e不是null，说明有需要覆盖的节点，
 *             if (e != null) { // existing mapping for key
 *                 //则覆盖节点值，并返回原oldValue
 *                 V oldValue = e.value;
 *                 if (!onlyIfAbsent || oldValue == null)
 *                     e.value = value;
 *                 //这是一个空实现的函数，用作LinkedHashMap重写使用。
 *                 afterNodeAccess(e);
 *                 return oldValue;
 *             }
 *         }
 *         //如果执行到了这里，说明插入了一个新的节点，所以会修改modCount，以及返回null。
 *
 *         //修改modCount
 *         ++modCount;
 *         //更新size，并判断是否需要扩容。
 *         if (++size > threshold)
 *             resize();
 *         //这是一个空实现的函数，用作LinkedHashMap重写使用。
 *         afterNodeInsertion(evict);
 *         return null;
 *     }
 *  小结：
 * * 运算尽量都用位运算代替，更高效。
 * * 对于扩容导致需要新建数组存放更多元素时，除了要将老数组中的元素迁移过来，也记得将老数组中的引用置null，以便GC
 * * 取下标 是用 哈希值 与运算 （桶的长度-1） i = (n - 1) & hash。 由于桶的长度是2的n次方，这么做其实是等于 一个模运算。但是效率更高
 * * 扩容时，如果发生过哈希碰撞，节点数小于8个。则要根据链表上每个节点的哈希值，依次放入新哈希桶对应下标位置。
 * * 因为扩容是容量翻倍，所以原链表上的每个节点，现在可能存放在原来的下标，即low位， 或者扩容后的下标，即high位。 high位= low位+原哈希桶容量
 * * 利用哈希值 与运算 旧的容量 ，if ((e.hash & oldCap) == 0),可以得到哈希值去模后，是大于等于oldCap还是小于oldCap，等于0代表小于oldCap，应该存放在低位，否则存放在高位。这里又是一个利用位运算 代替常规运算的高效点
 * * 如果追加节点后，链表数量》=8，则转化为红黑树
 * * 插入节点操作时，有一些空实现的函数，用作LinkedHashMap重写使用。
 *
 *
 *  与HashTable的区别
 * 与之相比HashTable是线程安全的，且不允许key、value是null。
 * HashTable默认容量是11。
 * HashTable是直接使用key的hashCode(key.hashCode())作为hash值，不像HashMap内部使用static final int hash(Object key)扰动函数对key的hashCode进行扰动后作为hash值。
 * HashTable取哈希桶下标是直接用模运算%.（因为其默认容量也不是2的n次方。所以也无法用位运算替代模运算）
 * 扩容时，新容量是原来的2倍+1。int newCapacity = (oldCapacity << 1) + 1;
 * Hashtable是Dictionary的子类同时也实现了Map接口，HashMap是Map接口的一个实现类；
 *
 *
 * 原文链接：https://blog.csdn.net/zxt0601/article/details/77413921
 */
public class MyHashMap implements Map{
    static int MAXIMUM = 1 << 30;
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
//        System.out.println(tableSizeFor(64*1024-1));
        System.out.println((Integer.MAX_VALUE/2+1));
        System.out.println(1<<30);
    }
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM) ? MAXIMUM : n + 1;
    }
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
