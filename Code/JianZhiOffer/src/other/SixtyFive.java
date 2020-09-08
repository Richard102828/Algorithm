package other;

/**
 * 《剑指Offer》第二版，面试题 65: 不用加减乘除做加法
 *
 *      写一个函数，求两个整数之和，要求在函数体内不得使用"+"、"-"、"x"、"÷"四则运算符号
 *
 * 较好的解法：
 *      1. 不用四则运算就使用 位运算 咯~
 *          a. 对于二进制，不进位的的加法就是 异或运算，计算进位只需将两个数相与并向左移一位（也就是进位）
 *          b. 注意：如果还有进位，要不断的累加，直到没有进位，此时计算才停止
 * 扩展一：不引用新变量交换两个变量
 *      1. 就使用这两个变量，求和，然后做减法即可
 *          a = a + b;
 *          b = a - b;
 *          a  = a - b;
 *      2. 或者使用异或操作，两个相同的数字进行 异或操作 等于 0
 */
public class SixtyFive {

    /**
     * 不用加减剩除做加法
     * @param num1
     * @param num2
     * @return
     */
    private int add(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1 ^ num2;
            num2 = ((num1 & num2) << 1);
            num1 = temp;
        }
        return num1;
    }

    /**
     * 扩展一：不引用新变量交换两个变量
     * @param num1
     * @param num2
     * @return
     */
    private int[] noTempAdd(int num1, int num2) {
        num1 = num1 + num2;
        num2 = num1 - num2;
        num1 = num1 - num2;
        return new int[] {num1, num2};
    }
}
