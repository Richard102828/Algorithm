package other;

/**
 * 《剑指Offer》第二版，面试题 46: 把数字翻译成字符串
 *
 *      给定一个数字，我们按照如下的规则把它翻译成字符串
 *          0 -> a
 *          1 -> b
 *          2 -> c
 *          ...
 *          25 -> z
 *      一个数字可能有多种翻译，比如12258有五种，分别是"bccfi", "bwfi","bczi","mcfi", "mzi"
 *      请实现一个函数，用来计算一个数字有多少种不同的翻译方法
 *
 * 较好的解法：
 *      1. 找规律：满足条件：f(i) = f(i+1) + g(i, i+1)*f(i+2)，g要么为1，要么为0，f(i+1)、f(i+2)表示，
 *      要么一次翻译一个数字，要么一次翻译两个数字
 *          a. 这样进行递归，但是这样的递归会有重复的地方，如：12234->12,234/->1,2234->1,2,234
 *      2. 递归从最大问题开始自上而下解决问题，有了重复的地方，我们可以从最小的子问题开始自下而上的解决问题，消除重复地方
 *          a. 从数字的右边往左边翻译，f(i) = f(i-1) + g(i, i+1)*f(i-2)
 */
public class FortySix {
    public static void main(String[] args) {
    }

    /**
     * 第二种方法
     * @param num
     * @return
     */
    private static int countOfTranslation(int num) {
        //鲁棒性
        if (num < 0)
            return 0;
        String temp = String.valueOf(num);
        int len = temp.length();
        int[] counts = new int[len];
        //最后一个，肯定是1
        counts[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            int digit1 = temp.charAt(i) - '0';
            int digit2 = temp.charAt(i + 1) - '0';
            int mergeDigit = digit1 * 10 + digit2;
            //判断是否满足可以两个数字进行翻译的范围
            if (mergeDigit >= 10 && mergeDigit <= 25) {
                if (i == len - 2)
                    counts[i] = counts[i + 1] + 1;
                else
                    counts[i] = counts[i + 1] + counts[i + 2];
            } else {
                counts[i] = counts[i + 1];
            }
        }
        return counts[0];
    }
}
