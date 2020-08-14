package array;

/**
 * 《剑指Offer》第二版，面试题 42: 连续子数组的最大和
 *
 *      输入一个整型数组，数组里正负数都可能有，数组中的一个或者连续的多个整数组成一个子数组。求
 *      所有子数组的和的最大值，要求时间复杂度为O(n)
 *          如：{1, -2, 3, 10, -4, 7, 2, -5}，最大子数组为：{3, 10, -4, 7, 2}，最大和为：18
 *
 * 较好的解法：
 *      1. 时间复杂度要求为：O(n)，意思是只能循环一次数组，我们就一边循环一边进行求和
 *          a. 如果当前累加和是负数，那么它加上当前元素将使得新的累加和比当前元素还要小，
 *          此时应该将之前的累加和丢弃，从当前元素开始累加
 *          b. 同时还要设置一个变量来保存当前最大值
 *      2. 书上还给出了一种动态规划，代码实现上似乎是类似的，但是思想没有理解到···
 */
public class FortyTwo {
    //对鲁棒性进行区分
    boolean isInvalidInput = false;

    /**
     * 循环加法方式
     * @param nums
     * @return
     */
    private int getMaxSumOfArray(int[] nums) {
        //鲁棒性
        if (nums == null || nums.length <= 0) {
           isInvalidInput = true;
           return 0;
        }

        int currentSum = 0;
        int currentMax = nums[0];
        for (int i = 0; i < nums.length; i++) {
            //当前和小于0，从当前整数开始重新求和
            if (currentSum < 0)
                currentSum = nums[i];
            else
                currentSum += nums[i];
            //更新最大值
            if (currentSum > currentMax)
                currentMax = currentSum;
        }
        return currentMax;
    }
}
