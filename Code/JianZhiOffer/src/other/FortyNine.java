package other;

/**
 * 《剑指Offer》第二版，面试题 49: 丑数
 *
 *      把只包含因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含因子7。
 *      习惯上我们把1当做是第一个丑数。
 *      求按从小到大的顺序的第N个丑数。
 *
 * 直接的解法：
 *      1. 最笨的，直接从第一个丑数1开始，然后循环自增的去判断数字是否是丑数
 *          a. 判断条件就是不断的去除以2、3、5看得到的结果是否为1
 *          b. 时间复杂度为O(n^2)，且效率较低
 * 较好的解法：
 *      1. 使用空间换时间：
 *          a. 可以这样讲，每一个丑数都是由另一个比它小的丑数乘以2、3、5得到的
 *          b. 设置一个数组，用来存放得到的丑数，找的下一个丑数通过数组中的每个丑数去乘以2、3、5，然后
 *          找到比当前数组中最大的丑数要大的数，再从这些数中找最小的
 *              i. 但是这样也有浪费时间的，我们要的只是乘以2、3、5后大于目前最大丑数的数中最小的，而
 *              前面小于这个数的丑数、还有大于这个数的丑数，都不需要了
 *              ii. 我们只需要找到这个数就行了
 */
public class FortyNine {
    private int getUglyNumber(int index) {
        //鲁棒性
        if (index <= 0)
            return 0;
        int[] uglyNums = new int[index];
        uglyNums[0] = 1;
        int multiply2 = uglyNums[0];
        int multiply3 = uglyNums[0];
        int multiply5 = uglyNums[0];
        for (int i = 1; i < index; i++) {
            uglyNums[i] = Math.min(multiply2 * 2, Math.min(multiply3 * 3, multiply5 * 5));
            //临界值
            if (uglyNums[i] == multiply2 * 2) multiply2++;
            if (uglyNums[i] == multiply3 * 3) multiply3++;
            if (uglyNums[i] == multiply5 * 5) multiply5++;
        }
        return uglyNums[index - 1];
    }
}
