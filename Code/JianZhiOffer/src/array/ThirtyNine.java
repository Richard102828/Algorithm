package array;

/**
 * 《剑指Offer》第二版，面试题 39：数组中出现次数超过一半的数字
 *
 *      数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *      例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 *      由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 *
 * 较好的解法：
 *      1. 根据题目的特点来看，要找的是超过数组长度一半的数字，那么这个数字放在数组中，无论怎样放置，数组中间的数字一定是它
 *          a. 所以我们只需要对数组进行排序即可。可以使用快排来实现
 *      2. 根据数组的特点，数组中有一个数字出现次数超过了一半，说明这个数字的出现次数比其他所有数字的出现次数之和加起来还要多
 *          a. 两个变量，result表示当前数字，count表示当前数字的计数值。在遍历到一个新的数字时候，计数为1，
 *          将该数字赋值给result，之后遇到的数字如果和result相同，那么count加1,；遇到不一样的count减1，直到count等于0，
 *          此时需要重新初始化，即将新的数字赋值给result并令count为1......这样遍历完所有数字时，最后result表示的数值就可能
 *          是我们要找的值，注意是可能，因为输入数组可能本身就不满足里面有某个数字出现次数超过一半。
 *          因此和上面的找中位数方法一样，得到的result还需要进一步检验
 * 我的解法：
 *      1. 使用时间复杂度为O(n)，空间复杂度为O(n)，当然没有上面的解法好
 *          a. 循环数组，创建哈希表，表中存的是数字与数字出现的次数，当有数字对应的次数超过数组长度一半时就找到了
 */
public class ThirtyNine {
    public static void main(String[] args) {
        int[] nums = {2, 1, 2};
        System.out.println(moreThanHalfNum(nums, nums.length));
    }

    /**
     * 使用快排方式
     * @param nums
     * @param length
     * @return
     */
    private static int moreThanHalfNum(int[] nums, int length) {
        //鲁棒性
        if (nums == null || length <= 0) {
            return 0;
        }
        int low = 0;
        int high = length - 1;
        int index = position(nums, low, high);
        int middle = length >> 1;
        while (index != middle) {
            if (index > middle) {
                high = index - 1;
                index = position(nums, low, high);
            } else {
                low = index + 1;
                index = position(nums, low, high );
            }
        }
        int result = nums[middle];
        if (checkMoreThanHalf(nums, length, result))
            return result;
        return 0;
    }

    /**
     * 快排实现
     * @param nums
     * @param low
     * @param high
     * @return
     */
    private static int position(int[] nums, int low, int high) {
        int key = nums[low];
        while (low < high) {
            while (nums[high] > key && low < high)
                high--;
            nums[low] = nums[high];
            while (nums[low] < key && low < high)
                low++;
            nums[high] = nums[low];
        }
        nums[low] = key;
        return low;
    }

    /**
     * 判断最终结果是不是出现了超过数组长度的一半的次数
     * @param nums
     * @param length
     * @return
     */
    private static boolean checkMoreThanHalf(int[] nums, int length, int result) {
        int times = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] == result)
                times++;
        }
        if ((times << 1) > length) {
            return true;
        }
        return false;
    }

    /**
     * 根据数组特点来实现
     * @param nums
     * @return
     */
    private static int moreThanHalfNum(int[] nums) {
        int length = 0;
        if (nums == null || nums.length <= 0)
            return 0;
        else
            length = nums.length;

        int result = nums[0];
        int times = 0;
        for (int i = 0; i < length; i++) {
            if (times == 0) {
                result = nums[i];
                times = 1;
            } else if (nums[i] == result) {
                times++;
            } else {
                times--;
            }
        }
        if (checkMoreThanHalf(nums, length, result))
            return result;
        else
            return 0;
    }
}
