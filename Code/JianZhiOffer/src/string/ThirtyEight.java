package string;

import java.util.Arrays;

/**
 * 《剑指Offer》第二版，面试题 38：字符串的排列
 *
 *      输入一个字符串,打印出该字符串中字符的所有排列。
 *      例如：输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 *
 * 较好的解法：
 *      1. 这其实要求的是一个字符串的全排列。我们分步来求，分为第一个字符与后面的字符这两部分
 *          a. 先将第一个字符与后面的字符进行排列(依次进行交换)
 *          b. 然后将每一次排列得到的字符串执行下面的i操作，直到交换到最后一个字符停止，最后归位，以便进行a操作中第一个字符的下一次排列
 *              i. 不管第一个字符，将字符串分为后一个字符与后一个字符后面的所有字符这两部分，重复
 */
public class ThirtyEight {
    public static void main(String[] args) {
        permutation(new char[] {'a', 'b', 'c'});
    }

    private static void permutation(char[] str) {
        //鲁棒性
        if (str == null)
            return;
        permutationCore(str, 0);
    }

    /**
     * 使用循环加递归的方式实现
     * @param str
     * @param begin
     */
    private static void permutationCore(char[] str, int begin) {
        if (begin == str.length - 1) {
            System.out.println(Arrays.toString(str));
            return;
        }
        //执行步骤a
        for (int i = begin; i < str.length; i++) {
            char temp = str[begin];
            str[begin] = str[i];
            str[i] = temp;
            //执行步骤b
            permutationCore(str, begin + 1);
            //归位，然后进行第一个字符的下一次排列（交换）
            temp = str[i];
            str[i] = str[begin];
            str[begin] = temp;
        }
    }
}
