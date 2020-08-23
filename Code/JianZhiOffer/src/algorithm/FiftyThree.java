package algorithm;

/**
 * 《剑指Offer》第二版，面试题 53: 在排序数组中查找数字
 *
 *      题目一：统计一个数字在排序数组中出现的次数
 *      题目二：0~n-1中缺失的数
 *          一个长度为n -1的递增排序数组中的所有数字都是唯一的，并且每个数字的都在范围0~n-1之内。
 *          在范围内0~n-1内的n个数字中有且只有一个数字不在该数组中，找出这个数字
 *      题目三：数组中数值和下标相等的元素。
 *          假设一个单调递增的数组里的每个元素都是整数并且是唯一的。
 *          找出数组中任意一个数值等于其下标的元素。比如在数组{-3， -1， 1， 3， 5}，数字3和它的下标相等
 *
 * 较好的解法：（一般这种简单的题目使用常规的顺序查找，尽管时间复杂度为O(n)，但也不是面试官想要的，一定会有比这中常规的方法时间复杂度还要低的）
 *      题目一：
 *          1. 使用二分查找，在排好序的数组中，相同的数字肯定是在一起的，我们只需要找到第一个与最后一个对应的下标就可以找到次数了
 *              i. 在二分查找的过程中，我们加一步，就是当找到该数字时，我们去看看前一个、后一个的数字是也不是不相同了
 *              ii. 不同的话就是第一个、最后一个数字了
 *      题目二；
 *          1. 分析题目，数组中有n-1个数字，但0~n-1有n个数字，少了一个，那么少了那个数的位置就由后面的数字补上了
 *          由于数组是递归的，数字对应到数字中，下标都应该是和数字本身相同的，但是少了一个后，后面的数字下标就与数字本身不同了
 *              i. 我们只需要找到第一个下标与本身不同的数字，他的下标就是缺少的数字
 *              ii. 使用二分查找的方式，和题目一类似
 *      题目三：
 *          1. 由于是排好序的，且为递增的，可以考虑使用二分查找
 *              i. 如果当前数字 == 下标，找到
 *              ii. 如果当前数字 < 下标，那么这个数字前面的数字也肯定小于下标，我们再去判断右边的即可
 *              iii. 如果当前数字 > 下标，同理，就找左边的
 */
public class FiftyThree {
    private boolean isInvalidInput = false;

    /**
     * 题目一：二分查找的方式
     * @param num
     * @param k
     * @param length
     * @return
     */
    private int getNumCounts(int[] num, int k, int length) {
        //鲁棒性
        if (num == null || length <= 0)
            return 0;
        int firstNumIndex = getFirstNum(num, k,0, length - 1);
        int lastNumIndex = getLastNum(num, k,0, length - 1);
        return lastNumIndex - firstNumIndex + 1;
    }

    private int getFirstNum(int[] num, int k, int start, int end) {
        int midIndex = (start + end) >> 1;
        if (num[midIndex] == k) {
            if (midIndex > 0 && num[midIndex - 1] != k)
                return midIndex;
            else
                end = midIndex - 1;
        } else if (num[midIndex] > k) {
            end = midIndex - 1;
        } else {
            start = midIndex + 1;
        }
        return getFirstNum(num, k, start, end);
    }

    private int getLastNum(int[] num, int k, int start, int end) {
        int midIndex = (start + end) >> 1;
        if (num[midIndex] == k) {
            if (midIndex > 0 && num[midIndex + 1] != k)
                return midIndex;
            else
                start = midIndex + 1;
        } else if (num[midIndex] > k) {
            end = midIndex - 1;
        } else {
            start = midIndex + 1;
        }
        return getLastNum(num, k, start, end);
    }

    /**
     * 题目二：二分查找的方式
     * @param num
     * @param length
     * @param start 传入0
     * @param end   传入length - 1
     * @return
     */
    private int getMissingNum(int[] num, int length, int start, int end) {
        //鲁棒性
        if (num == null || length <= 0) {
            isInvalidInput = true;
            return 0;
        }
        int midIndex = (start + end) >> 1;
        if (num[midIndex] != midIndex) {
            if (midIndex > 0 && num[midIndex - 1] == midIndex - 1)
                return midIndex;
            else
                end = midIndex - 1;
        } else {
            start = midIndex + 1;
        }
        return getMissingNum(num, length, start, end);
    }

    /**
     * 题目三：二分查找的方式
     * @param num
     * @param length
     * @return
     */
    private int getNumEqualIndex(int[] num, int length) {
        //鲁棒性
        if (num == null || length <= 0) {
            isInvalidInput = true;
            return -1;
        }
        int start = 0;
        int end = length - 1;
        while (start <= end) {
            int midIndex = (start + end) >> 1;
            if (num[midIndex] == midIndex) {
                return num[midIndex];
            } else if (num[midIndex] > midIndex) {
                end = midIndex - 1;
            } else {
                start = midIndex + 1;
            }
        }
        return -1;
    }
}
