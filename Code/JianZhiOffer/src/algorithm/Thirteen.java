package algorithm;

/**
 * 《剑指Offer》第二版，面试题 12: 矩阵中的路径
 *
 *      地上有一个m行和n列的方格。一个机器人从坐标(0,0)的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 *      但是不能进入行坐标和列坐标的数位之和大于k的格子。
 *      例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
 *      请问该机器人能够达到多少个格子？
 *
 * 较好的解法：
 *      和十二题类似的，使用回溯法
 */

public class Thirteen {
    public static void main(String[] args) {
        System.out.println(getMovingCount(3, 2, 2));
    }

    private static int getMovingCount(int threshold, int rows, int cols) {
        //鲁棒性
        if (threshold <= 0 || rows <= 0 || cols <= 0) {
            return 0;
        }

        //算法
        boolean[][] visited = new boolean[rows][cols];
        return getMovingCountCore(threshold, rows, cols, 0, 0, visited);
    }

    private static int getMovingCountCore(int threshold, int rows, int cols,
                                          int startRow, int startCol, boolean[][] visited) {
        if (startRow >= rows && startCol >= cols) {
            return 0;
        }
        int count = 0;
        if (check(threshold, startRow, startCol, rows, cols, visited)) {
            visited[startRow][startCol] = true;
            count = 1 + getMovingCountCore(threshold, rows, cols, startRow + 1, startCol, visited)
                    + getMovingCountCore(threshold, rows, cols, startRow - 1, startCol, visited)
                    + getMovingCountCore(threshold, rows, cols, startRow, startCol + 1, visited)
                    + getMovingCountCore(threshold, rows, cols, startRow, startCol - 1, visited);
        }
        return count;
    }

    private static boolean check(int threshold, int startRow, int startCol,
                                 int rows, int cols, boolean[][] visited) {
        if (startRow >= 0 && startCol >= 0
                && startRow < rows && startCol < cols
                && getDigitSum(startRow) + getDigitSum(startCol) <= threshold
                && !visited[startRow][startCol]) {
            return true;
        }
        return false;
    }

    private static int getDigitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
