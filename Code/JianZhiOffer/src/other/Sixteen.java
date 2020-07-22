package other;

/**
 * 《剑指Offer》第二版，面试题 16: 数值的整数次方
 *
 *      给定一个double类型的浮点数base和int类型的整数exponent
 *      求base的exponent次方。不得使用库函数直接实现，无需考虑大数问题。
 *
 * 较好的解法：（对于这个题目，要考虑全面）
 *      1. 正整数
 *      2. 0
 *      3. 负整数: 把次幂变为正数去算结果，结果变为倒数即可
 * 另外的解法：
 *                  (a ^ n/2) * (a ^ n/2)                 n为偶数
 *      公式：a^n =  (a ^ (n-1)/2) * (a ^ (n-1)/2) * a     n为奇数
 */
public class Sixteen {
    public static void main(String[] args) {
        System.out.println(power(2, 2));
        System.out.println(power(0, 1));
        System.out.println(power(2, -2));
        System.out.println(powerByFormula(2, -2));
    }
    private static double power(double base, int exponent) {
        //鲁棒性
        if (base == 0) {
            return 0;
        }
        if (exponent == 0 || base == 1) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }

        int unsignedExponent = exponent;
        if (exponent < 0) {
            unsignedExponent = -exponent;
        }
        double result = powerWithUnsigned(base, unsignedExponent);
        if (exponent < 0) {
            result = 1.0 / result;
        }
        return result;
    }

    private static double powerWithUnsigned(double base, int exponent) {
        double result = 1.0;
        for (int i = 1; i <= exponent ; i++) {
            result *= base;
        }
        return result;
    }

    /**
     * 使用公式 + 递归的方式
     * @param base
     * @param exponent
     * @return
     */
    private static double powerByFormula(double base, int exponent) {
        //鲁棒性
        if (base == 0) {
            return 0;
        }
        if (exponent == 0 || base == 1) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        int unsignedExponent = exponent;
        if (exponent < 0) {
            unsignedExponent = -exponent;
        }
        //偶数次幂除2
        double result = powerByFormula(base, unsignedExponent >> 1);
        result *= result;
        //奇数次幂除2后还要加一
        if ((exponent & 1) == 1) {
            result *= base;
        }
        return exponent < 0 ? 1.0 / result : result;
    }
}
