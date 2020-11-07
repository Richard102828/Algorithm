package array;

/**
 * 《剑指Offer》第二版，面试题 56: 数组中数字出现的次数
 *
 *      题目一：数组中只出现一次的两个数字
 *              一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 *              要求时间复杂度为O(n)，空间复杂度为O(1)
 *
 *      题目二：数组中唯一出现一次的数字
 *              在一个数组中除了一个数字只出现一次之外，其他数字都出现了三次，请找出那个只出现一次的数字.
 *
 * 较好的解法：
 *      题目一：
 *          1. 相同的两个数字进行异或操作，结果为0，我们可以将数组中的所有数字进行异或，这样相同的都抵消了，只剩下两个只出现一次的不同的数字的异或结果了
 *              a. 而从这两个只出现一次的不同数字的异或结果中，我们可以找到第一个不为0的位（即为1的位），代表在这个位上，两个数字的值是不相同的
 *              b. 所以我们可以按照这个位是不是1，就能将这两个数字分为两个数组，而相同的数字肯定会分到同一个组上
 *              c. 接下来我们就可以进行异或，就能得到这两个只出现一次的数字了
 *
 *      题目二：
 *          1. 哈希表统计次数，但空间复杂度为O(n)
 *          2. 直接排序之后再去找，时间复杂度O(nlogn)
 *          3. 书上的解法，一个int型有32位，我们把所有数字对应的位数相应的加起来，然后去除以3,
 *              a. 能整除，说明那个只出现一次的数字在这个位上是0
 *              b. 不能整除，说明那个只出现一次的数字在这个位上是1
 *              c. 这样就能找到了，时间复杂度O(n)，空间复杂度为O(1)，因为我们只需要一个32位固定的数组即可
 *
 */
public class FiftySix {

    private boolean isInvalidInput = false;

    /**
     * 题目一：后两个参数用来传值的，因为java值传递的原因
     * @param data
     * @param length
     * @param num1
     * @param num2
     */
    private void findAppearOnceNums(int[] data, int length, int[] num1, int[] num2) {
        //鲁棒性
        if (data == null || length < 2)
            return;
        int result = 0;
        //找到两个只出现一次的数字异或的结果
        for (int i = 0; i < length; i++) {
            result ^= data[i];
        }
        int index = getIndexOfFirstDiffBit(result);
        //分组，并找到结果
        for (int i = 0; i < length; i++) {
            if (isBitOne(index, data[i]))
                num1[0] ^= data[i];
            else
                num2[0] ^= data[i];
        }
    }

    /**
     * 题目一中额外的任务：找到第一个不同的位
     * @param result
     * @return
     */
    private int getIndexOfFirstDiffBit(int result) {
        int index = 0;
        while (result != 0) {
            if ((result & 1) == 0)
                return index;
            result = result >> 1;
            index++;
        }
        return -1;
    }

    /**
     * 题目一种额外的任务：判断一个数字的第n位是不是1
     * @param index
     * @return
     */
    private boolean isBitOne(int index, int val) {
        val = val >> index;
        return (val & 1) == 0;
    }

    /**
     * 题目二：第三种方式，使用二进制的位之和能被3整除
     * @param data
     * @param length
     * @return
     */
    private int getAppearOnceNum(int[] data, int length) {
        //鲁棒性
        if (data == null || length < 4) {
            isInvalidInput = true;
            return 0;
        }
        int[] bitSum = new int[32];
        //从左到右->高位到低位
        for (int i = 31; i >= 0 ; i--) {
            int bitMark = 1;
            for (int j = 0; j < length; j++) {
                int bitValue = data[i] & bitMark;
                if (bitValue == 1)
                    bitSum[i] += bitValue;
            }
            bitMark = bitMark << 1;
        }

        /**
         * 下面的代码可以转为这样，我暂时还没有理解到
         *
         *         int result = 0;
         *       	// 转换成十进制时，从最高位开始，从由左至右第一个不为0的位开始
         *         for (int i = 0; i < 32; i++) {
         *             result = result << 1;
         *           	// bitSum[i] % 3为0说明只出现一次的那个数第i为也是0；反之亦反
         *             result  += bitSum[i] % 3;
         *         }
         *         return result;
         */

        //得到只出现一次的数字的二进制
        for (int i = 0; i < bitSum.length - 1; i++) {
            if (bitSum[i] % 3 == 0)
                bitSum[i] = 0;
            else
                bitSum[i] = 1;
        }

        //二进制转为十进制
        int value = 0;
        for (int i = 31; i >= 0; i--) {
            value += bitSum[i] * Math.pow(2, 31 - i);
        }
        return value;
    }
}
