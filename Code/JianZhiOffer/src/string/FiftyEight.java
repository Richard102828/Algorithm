package string;

/**
 * 《剑指Offer》第二版，面试题 58: 翻转字符串
 *
 *      题目一：翻转字符串
 *          输入一个英文句子，翻转句子中单词的顺序，但单词内的顺序不变。为简单起见，标点符号和普通字母一样处理。
 *          例如输入"I am a student."则输出"student. a am I"
 *
 *      题目二：左旋转字符串
 *          字符串的左旋操作是把字符串前面的若干个字符转移到字符串的尾部。
 *          比如输入字符串"abcdefg"和一个数字2，则左旋转后得到字符串"cdefgab"
 *
 * 较好的解法：
 *      题目一：
 *          1. 以空格为分隔符
 *              a. 可以使用split方法，但这种方式不能处理多个空格连续的情况
 *              b. 可以记录空格的位置，这样就可以处理连续的空格了
 *          2. 两次翻转：先整体，再局部
 *              a. 先将整个字符串翻转(所有字符都翻转了)，这时候单词内的顺序也变了
 *              b. 再将单词一个一个的翻转顺序
 *                  i. 设置两个指针，开始都单词指向头部，然后后移尾指针，直到指针指向的值为空格，就可以开始翻转了
 *                  ii. 翻转完毕后，头指针指向空格，就后移，直到不是空格。
 *                  iii. 而尾指针也跟着头指针移动，头指针停止移动后，尾指针继续后移，直到下一个空格出现
 *
 *      题目二：
 *          1. 通过题目一的翻转来解决这个问题，实际上就是分成了两部分，举个例子：AB CD EF，2
 *              a. 分成两部分（AB, CD EF），分别进行旋转（得到BA，FE DC）
 *                  i. 这里就是两次翻转，第一个部分翻转，第二个部分再翻转，一共两次
 *              b. 再整体翻转（BA, FE DC） -> （CD ED, AB），这是第三次翻转
 *
 */
public class FiftyEight {

    /**
     * 题目一：第二种方式，两次翻转，先整体，后局部
     * @param str
     * @return
     */
    private String reversString(String str) {
        //鲁棒性，要注意全是空格的字符串
        if (str == null || str.trim() == "")
            return null;
        int start = 0;
        int end = 0;
        char[] chars = str.toCharArray();
        //整体
        revers(chars, start, chars.length - 1);
        //局部
        while (start < end) {
            if (chars[start] == ' ') {
                start++;
                end++;
            } else if (chars[start] != ' ') {
                end++;
            } else if (chars[end] == ' ') {
                revers(chars, start, end - 1);
                start = end;
            }
        }
        return new String(chars);
    }

    /**
     * 题目一：翻转
     * @param chars
     * @param start
     * @param end
     * @return
     */
    private void revers(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 题目二：左旋转字符串，三次翻转
     * @param str
     * @param len
     * @return
     */
    private String leftRotateString(String str, int len) {
        //鲁棒性，注意全为空格的字符串
        if (str == null || str.trim() == "" || len <= 0)
            return null;
        char[] chars = str.toCharArray();
        //第一次翻转，字符串第一部分
        revers(chars, 0, len - 1);
        //第二次翻转，字符串第二部分
        revers(chars, len, chars.length - 1);
        //第三次整体翻转，整体字符串
        revers(chars, 0, chars.length - 1);
        return new String(chars);
    }
}
