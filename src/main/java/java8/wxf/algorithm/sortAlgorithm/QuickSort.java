package java8.wxf.algorithm.sortAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 快速排序:
 *     随机选择数组中一个值作为基准值进行比较切换,改变基准值下标,小于等于基准值在左边,大于等于基准值在右边
 *     1．先从数列中取出一个数作为基准数。
 *     2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
 *     3．再对左右区间重复第二步，直到各区间只有一个数。
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] arr = {8,45,12,2,5,6,3,12,32,15,11};
//        new QuickSort().quickSort(arr,0, arr.length-1);
        recallQuickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public void quickSort(int[] arr, int startIndex, int endIndex) {
        // TODO: 可以使用栈+循环,取代递归
        // 递归结束条件： 当基准值左右边都没有数据时
        if (startIndex >= endIndex) {
            return;
        }
        // 根据基准值排序并获取以基准值的下标
        int pivotIndex = partition1(arr, startIndex, endIndex);
//        int pivotIndex = partition2(arr, startIndex, endIndex);
        // 以基准值进行划分后的左半部分
        quickSort(arr, startIndex, pivotIndex-1);
        // 以基准值进行划分后的右半部分
        quickSort(arr, pivotIndex+1, endIndex);
    }

    public static void recallQuickSort(int[] arr, int startIndex, int endIndex) {
        // 递归退出条件
        if (startIndex >= endIndex) {
            return;
        }
        int pivotIndex = recallPartition(arr, startIndex, endIndex);

        recallPartition(arr, startIndex, pivotIndex-1);

        recallPartition(arr, pivotIndex+1, endIndex);
    }

    // 挖坑法------想办法使基准值左边都小于基准值，基准值右边都大于基准值
    /**返回基准值的下标*/
    public int partition1(int[] arr, int startIndex, int endIndex) {
        // 选择第一个数为基准值
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        // index用于保存坑位
        int index = startIndex;
        // 最外层循环保证可以左右切换
        while (left<=right) {
            // 因为最左边为基准值所以先比较右边
            while (left<=right) {
                // 找到小于基准值的right值
                if (pivot>arr[right]) {
                    // 将右边的值填到左边的值
                    arr[left] = arr[right];
                    // 记录坑位改变
                    index = right;
                    // 被填后，左指针移动到下一个
                    left++;
                    // 退出循环去比较左边
                    break;
                }
                // 继续寻找比基准值小的right值
                right--;
            }
            // 比较左边
            while (left<=right) {
                // 找到大于基准值的left值
                if (pivot<arr[left]) {
                    // 将大于基准值的left值填入上一次小于基准值的right值的坑
                    arr[right] = arr[left];
                    // 记录坑位改变
                    index = left;
                    // 被填后，移动指针到前一个right
                    right--;
                    // 退出循环去比较右边
                    break;
                }
                left++;
            }
        }
        // 将基准值填充到最后坑
        arr[index] = pivot;
        // 将基准值所在下标返回
        return index;
    }
    // 指针交换法
    //
    //
    // 对于: 4, 7, 6, 5, 3, 2, 8, 1
    //
    // 开局和挖坑法相似，我们首先选定基准元素Pivot，并且设置两个指针left和right，指向数列的最左和最右两个元素：

    //接下来是第一次循环，选择第一个作为基准值pivot,从right指针开始，把指针所指向的元素和基准元素做比较。如果大于等于pivot，则指针向左移动；
    // 如果小于pivot，则right指针停止移动，切换到left指针。
    //
    //在当前数列中，1<4，所以right直接停止移动，换到left指针，进行下一步行动。
    //
    //轮到left指针行动，把指针所指向的元素和基准元素做比较。如果小于等于pivot，则指针向右移动；如果大于pivot，则left指针停止移动。
    //由于left一开始指向的是基准元素，判断肯定相等，所以left右移一位。
    // 由于7 > 4，left指针在元素7的位置停下。这时候，我们让left和right指向的元素进行交换。
    //接下来，我们进入第二次循环，重新切换到right向左移动。right先移动到8，8>2，继续左移。由于2<4，停止在2的位置。
    //切换到left，6>4，停止在6的位置。
    //元素6和2交换。
    //进入第三次循环，right移动到元素3停止，left移动到元素5停止。
    //元素5和3交换。
    //进入第四次循环，right移动到元素3停止，这时候请注意，left和right指针已经重合在了一起。
    //当left和right指针重合之时，我们让pivot元素和left与right重合点的元素进行交换。此时数列左边的元素都小于4，数列右边的元素都大于4，这一轮交换终告结束。
    /**返回基准值的下标*/
    public int partition2(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        // 左右指针不相等时
        while (left != right) {
            // 比较顺序,因为基准值选择最左边值所以要先从右边开始
            // 比较右边,大于基准则移动指针
            while (left<right && arr[right]>pivot) {
                right--;
            }
            // 比较左边,小于基准则移动指针
            while (left<right && arr[left]<=pivot ) {
                left++;
            }
            // 当找到小于基准值的right值与找到大于基准值的left值时left与right进行交换指针
            if (left<right) {
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
            }
        }
        // 左右指针相等时把最后一个坑填好
        int p2 = arr[startIndex];
        arr[startIndex] = arr[left];
        arr[left] = p2;
        return left;
    }

    // 回忆挖坑法-------基准值要大于左边的所有值，小于右边的所有值
    public static int recallPartition(int[] arr, int startIndex, int endIndex) {
        // 选择第一个为基准
        int pivot = arr[startIndex];
        // 保存记录坑位
        int pit = startIndex;
        int left = startIndex;
        int right = endIndex;

        while (left <= right) {
            // 先比较右边
            while (left <= right) {
                // 当右边小于基准值则交换，并去比较左边
                if (pivot > arr[right]) {

                    arr[left] = arr[right];
                    // 坑位改变
                    pit = right;
                    left++;
                    break;
                }
                // 继续寻找比基准值小的right值
                right--;
            }
            // 比较左边
            while (left <= right) {
                // 发现大于基准值的left值就退出，去比较右边
                if (pivot < arr[left]) {
                    arr[right] = arr[left];
                    // 坑位改变
                    pit = left;
                    right--;
                    break;
                }
                // 继续寻找比基准值大的left值
                left++;
            }
        }

        // 最后的坑位保存基准值
        arr[pit] = pivot;
        return pit;
    }

    // 回忆双指针法----
}
