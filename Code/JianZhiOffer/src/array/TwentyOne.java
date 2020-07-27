package array;

import java.util.Arrays;

/**
 * 《剑指Offer》第二版，面试题 21：调整数组的顺序使奇数位于偶数前面
 *
 *      输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 *      使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分
 *
 * 较好的解法：
 *      1. 类似快排，只是判断的条件不同
 *          a. 考虑代码的扩展性，应将判断逻辑抽离出来
 * 我的解法：
 *      1. 如果能使用额外的空间，空间复杂度O(n)、时间按复杂度O(n)
 *          a. 遍历原始数组，奇数移到新数组前半部分，偶数移到新数组后半部分即可
 *
 */
public class TwentyOne {
    public static void main(String[] args) {
        int[] data = {2, 3, 5, 1, 4, 1};
        reorderOddEven(data, data.length);
        System.out.println(Arrays.toString(data));
    }

    /**
     * 快排方式
     * @param data
     * @param length
     */
    private static void reorderOddEven(int[] data, int length) {
        //鲁棒性
        if (data == null || length <= 0) {
            return;
        }
        //算法
        int head = 0;
        int tail = length - 1;
        while (head < tail) {
            //奇数后移
            while (head < tail && !isEven(data[head]))
                head++;
            //偶数前移
            while (head < tail && isEven(data[tail]))
                tail--;
            if (head < tail) {
                int temp = data[head];
                data[head] = data[tail];
                data[tail] = temp;
            }
        }
    }

    /**
     * 逻辑抽离，是否为偶数
     * @param n
     * @return
     */
    private static boolean isEven(int n) {
        return (n & 1) == 0;
    }
}
