package other;

/**
 * 《剑指Offer》第二版，面试题 44: 数字序列中某一位的数字
 *
 *      数字以0123456789101112131415....的格式序列化得到一个字符序列中，在这个序列中，
 *      第5位（从0开始计数）是5，第13位是1，第19位是4，等等。
 *      请写一个函数，求任意第n位对应的数字
 *
 * 较好的解法：
 *      1. 比较直接的解法，逐个列举
 *          a. 每列举一个数字，就记录当前序列的长度，当当前序列的位数和大于输入的位数n时，停止列举
 *          b. 第n位数一定在刚刚列举的数中
 *      2. 找规律，根据n位数的总位数之和逐渐缩小范围
 *          a. 如：0~9就有10位，10~99就有90*2位，100~999就有900*3位等等
 *          b. 不断的缩小范围，最后再进行计数
 */
public class FortyFour {
    private boolean isInvalidInput = false;

    /**
     * 第一种直接的方法，逐个列举
     * @param index
     * @return
     */
    private int digitAtIndex(int index) {
        //鲁棒性
        if (index < 0) {
            isInvalidInput = true;
            return 0;
        }
        int sum = 0;
        //无限循环
        for (int i = 0; true ; i++) {
            sum += countDigits(i);
            if (sum > index)
                return digitAt(i, sum - index - 1);
        }
    }

    /**
     * 得到一个数字中指定索引位置的数字
     * @param value
     * @param index
     * @return
     */
    private int digitAt(int value, int index) {
        return (int) ((value / Math.pow(10, index)) % 10);
    }

    /**
     * 计算一个数字有多少位
     * @param num
     * @return
     */
    private int countDigits(int num) {
        if (num == 0) return 1;
        int count = 0;
        while (num != 0) {
            num /= 10;
            count++;
        }
        return count;
    }

    /**
     * 第二种方法，找规律不断缩小范围
     * @param index
     * @return
     */
    private int digitAtIndex2(int index) {
        //鲁棒性
        if (index < 0) {
            isInvalidInput = true;
            return 0;
        }
        //i 代表位数，从1位数开始
        for (int i = 1; true; i++) {
            int num = countOfDigits(i);
            if (num > index)
                return digitAt2(index, i);
            //缩小范围
            index -= num;
        }
    }

    /**
     * 得到结果
     * @param index
     * @param digit
     * @return
     */
    private int digitAt2(int index, int digit) {
        //整除的第几个数
        int numIndex = index / digit;
        //多出来的位数
        int otherIndex = index % digit;
        int startNum = (int) Math.pow(10, digit - 1);
        int num = startNum;
        while (num < numIndex + startNum) {
            num++;
        }
        return digitAt(num + 1, otherIndex);
    }

    /**
     * 计算n位数一共有多少位
     * @param digit
     * @return
     */
    private int countOfDigits(int digit) {
        if (digit == 1) return 10;
        return (int) (9 * Math.pow(10, digit - 1)) * digit;
    }
}
