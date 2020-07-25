package other;

/**
 * 《剑指Offer》第二版，面试题 19: 正则表达式匹配
 *
 *      请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
 *      而'*'表示它前面的字符可以出现任意次（包含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。
 *      例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 *
 * 较好的解法：(出现数组越界问题)
 *      根据模板中的字符类型，总体可以分为三种情况：
 *          1. 普通的字符
 *          2. 字符为: '.'
 *          3. 字符为: '*'
 *              a. '*'前面的字符相等
 *                  i. 一次
 *                  ii. 0次
 *                  iii. 多次
 *              b. '*'前面的字符不等，当做0次
 */
public class NinTeen {
    public static void main(String[] args) {
        System.out.println(match(new char[] {'a', 'a', 'a'},
                new char[] {'a', 'b', '*', 'a', 'c', '*', 'a'}, 0, 0));
    }

    /**
     * 这里使用递归的方式，并用数组来表示字符串
     * @param str
     * @param pattern
     * @return
     */
    private static boolean match(char[] str, char[] pattern, int s, int p) {
        //鲁棒性
        if (str == null || pattern == null || str.length == 0 || pattern.length == 0) {
            throw new IllegalStateException("无效的输入");
        }

        //算法
        if (s < str.length && p == pattern.length) {
            return false;
        }
        if (s == str.length && p == pattern.length) {
            return true;
        }
        if (pattern[p] == str[s] && pattern[p + 1] != '*' && s < str.length -1 && p < pattern.length - 1) {
            return match(str, pattern, s + 1, p + 1);
        } else if (pattern[p] == '.' && pattern[p + 1] != '*' && s < str.length -1 && p < pattern.length - 1) {
            return match(str, pattern, s + 1, p + 1);
        } else if (pattern[p + 1] == '*' && p < pattern.length - 1){
            //分为两种情况：'ch(代表字符)*' 与字符串中字符的相等，或者不等（就当做出现0次来处理）
            if (pattern[p] == str[s] && s < str.length - 1) {
                //相等又分为三种情况，字符串后移一位+模板后移两位；或者字符串后移一位+模板不移动；或者字符串不移动+模板后移两位
                return match(str, pattern, s + 1, p + 2)
                        || match(str, pattern, s, p + 2)
                        || match(str, pattern, s + 1, p);
            } else {
                return match(str, pattern, s, p + 2);
            }
        } else {
            //不匹配
            return false;
        }
    }
}
