package java8.wxf.dataStructure;

import java.util.TreeMap;

/**
 * TreeMap底层是红黑树
 *
 * put方法：不允许存在key为null
 *   1、先判断根节点root是否存在，
 *      不存在则先使用compare(key, key)比较方法验证key是否为null，为null则报NPE,不为null再创建，将添的节点作为根节点，直接返回null。
 *   2、存在根节点root则进而判断比较器comparator是否存在，
 *      存在则调用比较器comparator的比较方法从根节点开始二分法遍历树来比较key，通过比较找到可以作为key的父节点或者找到与key相等的节点，
 *      若找到与key相等的节点直接设值并返回老值。
 *   3、不存在比较器则调用key的比较方法进行上述比较。
 *   4、通过比较找到可作为key的父节点的parent后，根据parent、key、value创建新节点。根据之前比较的结果，将新节点作为left或right节点
 *   5、fixAfterInsertion修复插入数据对树的影响--即通过左旋、右旋使红黑树达到平衡。最后返回null
 *
 * get方法：
 *   1、根据key通过getEntry(key)获取数据项p，判断p是否为null,null则直接返回，否则返回p的value.
 *   2、getEntry(key)原理：判断comparator比较器是否存在，存在则根据root节点遍历树比较，
 *                        不存在则使用key的比较方法根据root节点遍历树比较。
 *
 * remove方法：
 *   1、先根据getEntry(key)判断是否存在数据项p，不存在直接返回null。
 *   2、存在则通过deleteEntry(p)删除该数据项并返回该数据项p的值。
 *   deleteEntry(p)原理：判断是叶子节点还是中间节点，中间节点则寻找后继节点，然后通过左旋右旋达到平衡
 *
 */
public class MyTreeMap {
    public static void main(String[] args) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put(null, "value1");
        System.out.println(treeMap.get("key1"));
    }
}
