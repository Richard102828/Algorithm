package other;

/**
 * 《剑指Offer》第二版，面试题 47: 礼物的最大值
 *
 *      在一个mxn的棋盘的每一格斗放油一个礼物，每个礼物都有一定的价值（大于0）从棋盘的左上角开始，
 *      每次可以往右边或者下边移动一格，知道到达棋盘的右下角。
 *      给定一个棋盘和上面的礼物，计算我们最多可以拿到多少价值的礼物
 *
 * 较好的解法：
 *      1. 首先一下子就想到了，可以使用递归的方式来求
 *          a. 每次不是向下，就是向右，求得所有可能，取最大值即可
 *      2. 动态规划，(i,j)位置的最大值，取决于它的上一步：(i, j-1)，(i-1,j)，可以使用循环来实现
 *      3. 对动态规划进行优化，还没有看懂···
 */
public class FortySeven {
    /**
     * 递归方式
     * @param gifts 使用一维数组来表示这个棋盘
     * @param rows
     * @param cols
     * @return
     */
    private int getMaxGiftValues(int[] gifts, int rows, int cols) {
        //鲁棒性
        if (gifts == null || gifts.length <= 0 || rows <= 0 || cols <= 0)
            return 0;
        //最后一个参数是用来表示最大值的，使用数组是因为值传递的原因
        int[] max = new int[] {0};
        select(gifts, rows, cols, 0, 0, 0, max);
        return max[0];
    }

    /**
     * 递归具体实现
     * @param gifts
     * @param rows
     * @param cols
     * @param row
     * @param col
     * @param max
     * @return
     */
    private void select(int[] gifts, int rows, int cols, int row,
                        int col, int val, int[] max) {
        //递归出口
        if (row > rows || col > cols) return;
        val += gifts[row * cols + col];
        //达到右下角
        if (row + 1 == rows && col + 1 == cols){
            if (val > max[0])
                max[0] = val;
        }
        select(gifts, rows, cols, row + 1, col, val, max);
        select(gifts, rows, cols, row, col + 1, val, max);
    }

    /**
     * 动态规划
     * @param gifts
     * @param rows
     * @param cols
     * @return
     */
    private int getMaxGift(int[] gifts, int rows, int cols) {
        //鲁棒性
        if (gifts == null || gifts.length <= 0 || rows <= 0 || cols <= 0)
            return 0;
        int[][] maxValues = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int left = 0;
                int up = 0;
                if (i > 0)
                    up = maxValues[i - 1][j];
                if (j > 0)
                    left = maxValues[i][j - 1];
                maxValues[i][j] = Math.max(up, left) + gifts[i * cols + j];
            }
        }
        return maxValues[rows - 1][cols -1];
    }
}
