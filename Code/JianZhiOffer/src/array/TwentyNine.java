package array;

/**
 * 《剑指Offer》第二版，面试题 29: 顺时针打印矩阵
 *
 *      输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下矩阵：
 *          1 2 3 4
 *          5 6 7 8
 *          9 10 11 12
 *          13 14 15 16
 *      则依次打印出数字
 *          1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *
 * 较好的解法：
 *      1. 书上的那种解法（感觉很正常的解法，没有什么特殊的）
 *          a. 注意要对继续循环的条件找规律
 *          a. 还要注意处理最后一圈，只有四种情况
 */
public class TwentyNine {
    public static void main(String[] args) {

    }

    /**
     * 使用参数start用来做缩圈的变量
     * @param matrix
     * @param rows
     * @param columns
     */
    private static void printMatrixClockwise(int[][] matrix, int rows, int columns) {
        //鲁棒性
        if (matrix == null || rows <= 0 || columns <= 0)
            return;

        int start = 0;
        //这个循环的判断条件可以记住，是一种规律
        while (columns > start * 2 && rows > start * 2) {
            printMatrixCore(matrix, rows, columns, start);
            start++;
        }
    }

    /**
     * 注意要处理最后一圈，四种情况：一步、两步、三步、四步
     * @param matrix
     * @param rows
     * @param columns
     * @param start
     */
    private static void printMatrixCore(int[][] matrix, int rows, int columns, int start) {
        int endX = columns - 1 - start;
        int endY = rows - 1 - start;
        //从左到右，第一个不不需要处理，四种情况都能打印出来
        for (int i = start; i < endX; i++)
            System.out.println(matrix[start][i]);
        //从上到下
        if (start < endY) {
            for (int i = start; i < endY; i++)
                System.out.println(matrix[i][endY]);
        }
        //从右到左，列号是递减的
        if (start < endX && start < endY) {
            for (int i = endX; i >= start; --i)
                System.out.println(matrix[endY][i]);
        }
        //从下到上
        if (start < endY - 1 && start < endX) {
            for (int i = start; i < endY - 1; i++)
                System.out.println(matrix[start][i]);
        }
    }
}
