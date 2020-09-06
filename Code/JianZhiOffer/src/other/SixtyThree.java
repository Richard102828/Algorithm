package other;

/**
 * 《剑指Offer》第二版，面试题 63: 股票的最大利润
 *
 *      假设某股票的价格按照时间先后顺序存储在数组中，问买卖该股票一次可能获得的最大利润是多少？
 *      如一支股票在某段时间内的价格为{9, 11, 8, 5, 7, 12, 16, 14}
 *      那么能在价格为5的时候购入并在价格为16时卖出，能获得最大利润11
 *
 * 较好的解法：
 *      1. 获取最大的利润，就是指，数组中两个数的差值最大，不过，要注意的是这个是股票，有时间顺序的，只能是从前往后买
 *          a. 我们从头到尾扫描一次数组，去找最大的差值即可
 *              i. 需要两个变量来保存最小值与最大差值
 *              ii. 由于时间关系，差值应该是后面的数减去见面的最小值
 */
public class SixtyThree {
    private int maxDiff(int[] nums, int length) {
        //鲁棒性
        if (nums == null || length <= 0)
            return -1;
        int min = nums[0];
        int maxDiff = nums[1] - min;
        for (int i = 2; i < length; i++) {
            if (min > nums[i])
                min = nums[i];
            if (nums[i] - min > maxDiff)
                maxDiff = nums[i] - min;
        }
        return maxDiff;
    }
}
