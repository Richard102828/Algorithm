package array;

/**
 * 《剑指Offer》第二版，面试题 51: 数组中的逆序对
 *
 *      在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 *      输入一个数组,求出这个数组中的逆序对的总数.
 *
 * 较好的解法：
 *      1. 使用归并排序的方式，只是在左子数组中的某个元素大于右子数组某个元素时，多加一步——计算逆序对个数即可。
 *          a. 进行比较的两个指针不再是分组中的头指针了，而是最后一个指针（最后一个元素是最大的）
 *          b. 这样，当比较出了大小之后，对于小的分组中剩下的元素就不用再进行比较了
 *              i. 而剩下的元素个数为：大的指针-小的头部-打的分组的长度。即大指针之前的元素减去自己分组中的长度
 *
 * 我的解法：
 *      1. 排序，然后找规律求解，不过要解决相等的情况
 */
public class FiftyOne {
    private static boolean isInvalidInput = false;
    private static int getInversePairsCount(int[] num, int left, int right) {
        if (num == null) {
            isInvalidInput = true;
            return 0;
        }

        int count = 0;
        if (left < right) {
            int mid = (left + right) >> 1;
            int l = getInversePairsCount(num, left, mid);
            int r = getInversePairsCount(num, mid + 1, right);
            int merge = merge(num, left, mid, right);
            count = l + r + merge;
        }
        return count;
    }

    /**
     * 不再是从每个分组的头部开始比较了，是从尾部开始比较
     * @param num
     * @param left
     * @param mid
     * @param right
     * @return
     */
    private static int merge(int[] num, int left, int mid, int right) {
        int[] temp = new int[num.length];
        int len = (right - left) >> 1;
        int leftEnd = mid;
        int rightEnd = right;
        int tempIndex = right;
        int count = 0;

        while (leftEnd >= left && rightEnd >= mid + 1) {
            if (num[leftEnd] > num[rightEnd]) {
                temp[tempIndex--] = num[leftEnd--];
                count += leftEnd - left - len;
            } else
                temp[tempIndex--] = num[rightEnd--];
        }

        while (leftEnd >= left)
            temp[tempIndex--] = num[leftEnd--];
        while (rightEnd >= mid + 1)
            temp[tempIndex--] = num[rightEnd--];
        return count;
    }
}
