package algorithm;

/**
 * 《剑指Offer》第二版，面试题 14: 剪绳子
 *
 *      给你一根长度为n的绳子，把绳子剪成m段（m、n都是整数且m > 1, n > 1）,m段绳子的长度依然是整数，求m段绳子的长度乘积最大为多少？
 *      比如绳子长度为8，我们可以分成2段、3段、4段...8段，只有分成3段且长度分别是2、3、3时能得到最大乘积18
 *
 * 较好的解法：
 *      书上给的动态规划、贪婪算法
 */
public class Fourteen {
    public static void main(String[] args) {
        System.out.println(maxProductAfterCutting1(8));
        System.out.println(maxProductAfterCutting2(8));
    }

    /**
     * 动态规划
     * @param length
     * @return
     */
    private static int maxProductAfterCutting1(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }
        int[] products = new int[length + 1];
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;
        //从下往上
        for (int i = 4; i <= length; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int product = products[j] * products[i - j];
                if (product > max) {
                    max = product;
                }
            }
            products[i] = max;
        }
        return products[length];
    }

    /**
     * 贪婪算法
     * @param length
     * @return
     */
    private static int maxProductAfterCutting2(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }

        int timesOf3 = length / 3;
        if (length - timesOf3 * 3 == 1) {
            timesOf3--;
        }
        //把剩下的长度拿来减为2的小段，这里长度应该会是4
        int timeOf2 = (length - timesOf3 * 3) / 2;
        return (int) (Math.pow(3, timesOf3) * Math.pow(2, timeOf2));
    }
}
