package other;

/**
 * 《剑指Offer》第二版，面试题 64: 求1+2+3+...+n
 *
 *      求1+2+3+...+n
 *      要求不能使用乘除法、for、while、if、else、switch、case等关键词以及三元运算符等
 *
 * 较好的解法：
 *      1. 递归
 *          a. 虽然说递归也需要 if 语句来终止，但是我们可以使用其他的 布尔值加上短路与 来代替这个if的功能
 *      2. 数学公式
 *          a. n(n+1)/2，不能用乘除法，那么我们就使用java提供的Math工具类中的函数即可
 *
 */
public class SixtyFour {

    /**
     * 递归的方式
     * @param n
     * @return
     */
    private int getSumOfN(int n) {
        int sum = n;
        boolean b = n > 0 && (sum += getSumOfN(n - 1)) > 0;
        return sum;
    }

    private int getSum(int n) {
        return (int) (Math.pow(n, 2) + n) >> 1;
    }
}
