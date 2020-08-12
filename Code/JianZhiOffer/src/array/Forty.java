package array;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * 《剑指Offer》第二版，面试题 40：最小的k个数
 *
 *      输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4
 *
 * 较好的解法：
 *      1. 很容易想的就是到排序之后然后取前面几个数就行了，使用快排的方式，时间复杂度就是O(nlogn)
 *      2. 根据上面的快排，我们多想一下就能想到，其实并不需要全部将数字进行排序，我们只是要找到最小的k个数就行了
 *          a. 所以，就能想到快排的partition函数返回的就是基准数的位置，而基准数前面的就是最小的几个数字
 *          b. 这样的算法时间复杂度为O(n)
 *      3. 我们还可以使用一个容器存储k个数字，循环所有数字，进行插入操作
 *          a. 每次插入操作时比较容器中的最大值，大，则不插入，小，则替换
 *              i. 这里我们要找容器中的最大值，就可以想到最大堆这个数据结构，使用PriorityQueue来实现
 *          b. 时间复杂度是O(nlogk)，空间复杂度O(n)
 */
public class Forty {
    public static void main(String[] args) {
        int[] nums = {4, 5, 1, 6, 2, 7, 3, 8};
        getLeastNumbers(nums, nums.length, 4);
        printLeastNumbers(nums, nums.length, 4);
    }

    /**
     *  快排partition方式
     * @param nums
     * @param length
     * @param k
     */
    private static void getLeastNumbers(int[] nums, int length, int k) {
        if (nums == null || length <= 0 || k <= 0)
            return;
        int start = 0;
        int end = length - 1;
        int index = partition(nums, start, end);
        while (index != k - 1) {
            if (index < k - 1) {
                start = index + 1;
                index = partition(nums, start, end);
            } else {
                end = index - 1;
                index = partition(nums, start, end);
            }
        }
        for (int i = 0; i < k; i++) {
            System.out.println(nums[i]);
        }
    }

    private static int partition(int[] nums, int start, int end) {
        int key = nums[start];
        while (start < end) {
            while (nums[end] > key && start < end)
                end--;
            nums[start] = nums[end];
            while (nums[start] < key && start < end)
                start++;
            nums[end] = nums[start];
        }
        nums[start] = key;
        return start;
    }

    /**
     * 使用最大堆来实现
     * @param nums
     * @param length
     * @param k
     */
    private static void printLeastNumbers(int[] nums, int length, int k) {
        if (nums == null || length <= 0 || k <= 0)
            return;
        //最大堆实现
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < length; i++) {
            if (i < 4) {
                maxHeap.add(nums[i]);
            } else {
                if (nums[i] < maxHeap.peek()) {
                    maxHeap.poll();
                    maxHeap.add(nums[i]);
                }
            }
        }
        Iterator<Integer> iterator = maxHeap.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
