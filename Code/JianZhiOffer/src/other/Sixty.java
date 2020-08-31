package other;

/**
 * 《剑指Offer》第二版，面试题 60: n个骰子的点数
 *
 *      把n个骰子扔在地上，所有骰子朝上的一面的点数之和为s。
 *      输入n，打印出s的所有可能的值出现的概率。
 *
 * 较好的解法：
 *      1. 递归的方式，直接硬算
 *          a. 固定一个，然后去求另外n-1个的情况，这样递归下去
 *          b. 因为一个骰子一共有6个面，6种可能，那么n个的话，他们的和在这个范围：n~6n
 *          c. 我们可以设置一个数组来对应这个结果可能出现的情况，记录的是结果出现的次数
 *              i. 数组大小：6n-n+1，从索引0开始对应着和为n的结果
 *          d. 最终我们要的是结果出现的概率，所以一共可能出现次数：6^n，我们用求出来的结果除以这个数就得到了结果的概率
 *      2. 动态规划（这个思想不是很懂···）
 *          a. f(n)=f(n-1)+f(n-2)+f(n-3)+f(n-4)+f(n-5)+f(n-6)
 *
 */
public class Sixty {

    /**
     * 方式一：使用递归的方式
     * @param n
     */
    private void printProbability(int n) {
        //鲁棒性
        if (n < 1)
            return;
        int[] sumTimes = new int[6 * n - n + 1];
        getProbability(n, n, 0, sumTimes);
        for (int i = 0; i < sumTimes.length; i++) {
            System.out.println(sumTimes[i] / Math.pow(6, n));
        }
    }

    /**
     * 方式一：递归计算结果出现的次数
     * @param n
     * @param currentNum
     * @param sum
     * @param sumTimes
     */
    private void getProbability(int n, int currentNum, int sum, int[] sumTimes) {
        //程序出口
        if (n == 0) {
            sumTimes[sum - n]++;
        } else {
            for (int i = 1; i <= 6; i++) {
                getProbability(n, currentNum - 1, sum + i, sumTimes);
            }
        }
    }

    /**
     * 方式二：使用动态规划，循环的方式
     * @param n
     */
    private void printProbabilityByFun(int n) {
        //鲁棒性
        if (n < 1)
            return;
        //二维数组，行对应第n个骰子，列对应点数之和
        int[][] f = new int[n + 1][6 * n + 1];
        //只有一个骰子的时候，点数和为i的情况只有一种
        for (int i = 1; i <= 6; i++) {
            f[1][i] = 1;
        }
        //有2~n个骰子
        for (int i = 2; i <= n; i++) {
            for (int j = i; j < i * 6; j++) {
                for (int k = 1; k > i && k <= 6; k++) {
                    f[i][j] += f[i - 1][j - k];
                }
            }
        }

        for (int i = n; i <= 6*n; i++) {
            System.out.println(f[n][i] / Math.pow(6, n));
        }
    }
}
