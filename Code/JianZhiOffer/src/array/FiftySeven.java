package array;


/**
 * 《剑指Offer》第二版，面试题 57: 和为s的数字
 *
 *      题目一：和为s的数字
 *          输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S；
 *          如果有多对数字的和等于S，输出两个数的乘积最小的。
 *
 *      题目二：和为s的连续正数序列
 *          输入一个正数s，打印出所有何为s的连续正数序列（至少含有两个数）。
 *          例如输入15，由于1+2+3+4+5 = 4+5+6 = 7+8,所有打印出三个连续的序列1~5,4~6,7~8
 *
 * 较好的解法：
 *      题目一：
 *          1. 因为数组已经是递增的了，我们可以设置两个指针，一个指向最小的数（开头），一个指向最大的数（结尾）
 *              a. 这样将这两个指针所指向的数字相加，就能得到三种情况
 *                  i. 与给定的数相等，则输出
 *                  ii. 小于给定的数，则小的指针后移，再比较
 *                  iii. 大于给定的数，则大的指针迁移，再比较
 *
 *      题目二：
 *          1. 与题目一类似，我们可以借助两个指针，不过因为序列是我们自己定（变化的），所以就不能直接将指针设置到序列末尾了
 *              a. 我们将两个指针设置1，2
 *              b. 然后在求和比较给定的数，还是三种情况
 *                  i. 小于给定的数，后一个指针后移，继续比较
 *                  ii. 等于给定的数，输出，后一个指针后移，继续比较
 *                  iii. 大于给定的数，前一个指针后移，继续比较
 *              c. 注意一点，由于要的是一个序列（且是连续、递增的），至少有两个数字，所以前面的指针最多只能移到 (sum + 1) / 2的位置，
 *              再多后面肯定就不满足了
 *
 */
public class FiftySeven {

    /**
     * 第一题
     * @param nums
     * @param length
     * @param sum
     * @return
     */
    private int[] findNumsWithSum(int[] nums, int length, int sum) {
        //鲁棒性
        if (nums == null || length < 2)
            return null;
        int start = 0;
        int end = length - 1;
        int[] result = new int[2];
        while (start < end) {
            if (nums[start] + nums[end] == sum) {
                result[0] = nums[start];
                result[1] = nums[end];
            } else if (nums[start] + nums[end] > 0) {
                end--;
            } else {
                start++;
            }
        }
        return result;
    }

    /**
     * 第二题
     * @param sum
     */
    private void printContinuousSequence(int sum) {
        //鲁棒性
        if (sum <= 0)
            return;
        int start = 1;
        int end = 2;
        int mid = (sum + 1) >> 1;
        while (start < mid) {
            if (getSumFromStartToEnd(start, end) == sum) {
                printNums(start, end);
                mid++;
            } else if (getSumFromStartToEnd(start, end) < sum) {
                mid++;
            } else {
                start--;
            }
        }
    }

    /**
     * 求从start到end的序列和
     * @param start
     * @param end
     * @return
     */
    private int getSumFromStartToEnd(int start, int end) {
        int sum = 0;
        for (int i = start; i <= end ; i++) {
            sum += i;
        }
        return sum;
    }

    /**
     * 打印序列
     * @param start
     * @param end
     */
    private void printNums(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(i);
        }
    }
}
