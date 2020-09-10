package string;

import java.util.Arrays;

/**
 * 《剑指Offer》第二版，面试题 67：字符串转整数
 *
 *      将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数（Integer.parseInt(s)）。
 *      数值为0或者字符串不是一个合法的数值则返回0
 *
 * 较好的解法：
 *      1. 将一个字符串转为整数的过程代码是很容易写出来的，但是要注意的是非法输入、边界值的考虑！！！
 *          a.非法输入避免与特殊情况的返回值相同，设置一个全局变量来区分
 *          b. 对于特殊情况，要进行单独的处理
 *              i. 第一个字符为"+"、"-"，直接判断下一个字符
 *              ii. 后面的字符，需要在 '0'~'9'之间，并且如果出现其他字符的话，就可以按不能转换直接返回了
 *              iii. 判断是否溢出？
 */
public class SixtySeven {
    private static boolean isInvalidInput = false;

    public static void main(String[] args) {
        int num = stringToInt("1234");
        System.out.println(num);
    }

    /**
     * 返回值的情况：0
     *      a. 如果isInvalidInput标志为 true，则属于非法输入
     *      b. 如果isInvalidInput标志为false，则转换后数字溢出了
     * @param s
     * @return
     */
    private static int stringToInt(String s) {
        if (s == null) {
            isInvalidInput = true;
            return 0;
        }
        int num = 0;
        boolean isNavigate = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(0) == '+' || s.charAt(0) == '-') {
                if (s.charAt(0) == '-') {
                    //是负数的话要给个标记
                    isNavigate = true;
                }
                //跳过+、-
                continue;
            }
            if (s.charAt(i) < '9' && s.charAt(i) >= '0') {
                num = num * 10 + (s.charAt(i) - '0');
            } else {
                isInvalidInput = true;
                return 0;
            }
            //判断溢出
            if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return isNavigate ?  -1 * num : num;
    }
}
