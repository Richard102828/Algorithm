package algorithm;

/**
 * 《剑指Offer》第二版，面试题 11: 旋转数组中的最小数字
 *
 *      把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 *      输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 *      例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0
 *
 * 较好的解法：
 *      1. O(n)算法：顺序查找即可，找到第一个比前面数组元素小的元素，就是最小的了
 *      2. 二分查找 O(logn)：算是二分查找的变种了，因为不知道谁是最小的元素，所以利用旋转数组这个特性，与中间值比较的元素是左右两个指针的元素
 *      特别注意这个旋转数组的特殊情况
 *          a. 正常情况：如：12345 -> 45123
 *          b. 特殊情况1：如：12345 -> 12345
 *          c. 特殊情况2：如：01111 -> 11101 这个时候考虑顺序查找即可
 *
 */
public class Eleven {
    public static void main(String[] args) {
        int[] nums = {4, 5, 1, 2, 3};
        try {
            System.out.println(min(nums, nums.length));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * 正常情况a,特殊情况1,使用二分查找
     * @param nums
     * @param length
     * @return
     */
    private static int min(int[] nums, int length) throws Exception {
        //鲁棒性
        if (nums == null || length <= 0) {
            throw new Exception("无效的输入");
        }
        //算法
        int index1 = 0;
        int index2 = length - 1;
        //特殊情况1
        int indexMid = index1;
        while (nums[index1] >= nums[index2]) {
            //循环出口
            if (index1 == index2 - 1) {
                indexMid = index2;
                break;
            }
            indexMid = (index1 + index2) / 2;
            //特殊情况2
            if (nums[index1] == nums[index2] && nums[index1] == nums[indexMid]) {
                return minInOrder(nums, index1, index2);
            }
            //正常情况
            if (nums[index1] <= nums[indexMid]) {
                index1 = indexMid;
            } else if (nums[index2] >= nums[indexMid]) {
                index2 = indexMid;
            }
        }
        return nums[indexMid];
    }

    /**
     * 特殊情况3，使用顺序查找
     * @param nums
     * @param index1
     * @param index2
     * @return
     */
    private static int minInOrder(int[] nums, int index1, int index2) {
        int result = nums[index1];
        for (int i = index1 + 1; i <= index2; i++) {
            if (result > nums[i] ) {
                result = nums[i];
            }
        }
        return result;
    }
}
