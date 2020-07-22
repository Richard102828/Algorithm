package algorithm;

/**
 * 《剑指Offer》第二版，面试题 15: 二进制中1的个数
 *
 *      输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示
 *
 * 较好的解法：（属于位运算）
 *      书上给出的三种：注意（整数包括负数！！！）
 *          1. 将整数右移
 *          2. 将1不断的左移
 *          3. 把一个整数减去1之后再与原来的整数做位与运算，得到的结果相当于将原整数的二进制表示中最右边的1变成0
 */
public class Fifteen {
    public static void main(String[] args) {
        System.out.println(numberOf1ByRightMove(10));
        System.out.println(numberOf1ByLeftMove(10));
        System.out.println(numberOf1ByToBeChangeOne(10));
    }

    /**
     * 将整数右移
     * @param num
     * @return
     */
    private static int numberOf1ByRightMove(int num) {
        int count = 0;
        while (num != 0) {
            if ((num & 1) != 0) {
                count++;
            }
            num = num >> 1;
        }
        return count;
    }

    /**
     * 将1不断的左移
     * @param num
     * @return
     */
    private static int numberOf1ByLeftMove(int num) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((num & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    /**
     * 把一个整数减去1之后再与原来的整数做位与运算，得到的结果相当于将原整数的二进制表示中最右边的1变成0
     * @param num
     * @return
     */
    private static int numberOf1ByToBeChangeOne(int num) {
        int count = 0;
        while (num != 0) {
            num = (num - 1) & num;
            count++;
        }
        return count;
    }
}
