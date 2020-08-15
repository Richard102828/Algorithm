package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 《剑指Offer》第二版，面试题 45: 把数组排列成最小的数
 *
 *      输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *      例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 *
 * 较好的解法：(首先得考虑大数问题)
 *      1. 全排列，这里使用字符串来解决大数问题
 *          a. 不过我们还可以多想想，这些数组全部排列起来，我们实际上要的是最小的
 *              i. 举例：两个数排列有两种情况，并不是数小的排在前面得到的就是最小的
 *              ii. 而是排列后的数进行比较后，我们才能决定出哪种排列是最小的
 *          b. 所以，可以在进行排列之前，先进行排序，不过这个排序的规则是将排列后的两个数进行比较后，来决定哪个数“小”
 *              i. 根据我们制定的排序规则来进行排序，可以使用Comparator
 */
public class FortyFive {

    private String getMinNumber(int[] num, int length) {
        //鲁棒性
        if (num == null || length <= 0)
            return null;
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            strings.add(String.valueOf(i));
        }
        strings.sort((a, b) -> (a + "" + b).compareTo(b + "" + a));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            sb.append(strings.get(i));
        }
        return sb.toString();
    }
}
