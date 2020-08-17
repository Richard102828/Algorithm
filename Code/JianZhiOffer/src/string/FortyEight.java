package string;

/**
 * 《剑指Offer》第二版，面试题 48: 最长不含重复字符的子字符串
 *
 *      请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *      假设字符串中只包含'a'~'z'之间的字符，例如在字符串"arabcacfr"中，
 *      最长的不含重复字符的子字符串是"acfr"，长度为4
 *
 * 较好的解法：
 *      1. 动态规划，定义f(i)表示以第i个字符为结尾的不含重复字符的子字符串长度
 *          a. 不重复：f(i) = f(i - 1) + 1
 *          b. 重复情况：(d表示重复字符之间的距离)
 *              i. d <= f(i - 1)，说明重复字符在上一个子字符串内，舍弃第一次出现的该字符，然后重置长度
 *                  f(i) = d
 *              ii. d > f(i - 1)，说明重复字符不在上一个字符串中，我们就不用管了，它已经不在我们上一次
 *                  计算的长度内了，所以，f(i) = f(i - 1) + 1
 *          c. 当然，我们要的是最大长度，所以还需要对最大值进行保存
 *          d. 如何判断字符是否重复？
 *              i. 最笨的方法就是每次循环都遍历一遍我们走过的字符，这样效率很低
 *              ii. 我们要的就是一个标志，是否重复？是？不是？所以我们可以设置一个字符数组，从`a~z`26个字符，
 *                  并设置初值，每次循环如果重复了就改变数组中的值，这样我们只需要去数组中找对应字符的值是否改变即可
 */
public class FortyEight {
    boolean isInvalidInput = false;
    private int getMaxLength(String str) {
        //鲁棒性
        if (str == null || str.length() <= 0) {
            isInvalidInput = true;
            return 0;
        }
        int currentLen = 0;
        int maxLen = 0;
        int[] position = new int[26];
        for (int i = 0; i < 26; i++) {
            position[i] = -1;
        }
        for (int i = 0; i < str.length(); i++) {
            int prevIndex = position[str.charAt(i) - 'a'];
            int d = i - prevIndex;
            if (prevIndex == -1 || d > currentLen) {
                currentLen++;
            } else {
                if (maxLen < currentLen)
                    maxLen = currentLen;
                currentLen = d;
            }
            position[str.charAt(i) - 'a'] = i;
        }
        if (currentLen > maxLen)
            maxLen = currentLen;
        return maxLen;
    }
}
