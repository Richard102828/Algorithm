package algorithm;

/**
 * 《剑指Offer》第二版，面试题 12: 矩阵中的路径
 *
 *      请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 *      路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 *      如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 *          例如下面矩阵：
 *              a b t g
 *              c f c s
 *              j d e h
 *
 *      包含一条字符串"bfce"的路径，但是矩阵中不包含"abfb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，
 *      路径不能再次进入该格子。
 *
 * 较好的解法：
 *      书上给的回溯法
 */
public class Twelve {
    public static void main(String[] args) {
        char[][] matrix = {{'a', 'b', 't', 'g'},
                {'c', 'f', 'c', 's'},
                {'j', 'd', 'e', 'h'}};
        System.out.println(hasPath(matrix, matrix.length, matrix[0].length, "bfce"));
    }

    private static boolean hasPath(char[][] matrix, int rows, int cols, String str) {
        //鲁棒性
        if (matrix == null || rows <= 0 || cols <= 0 || str == null) {
            return false;
        }
        boolean[][] visited = new boolean[rows][cols];
        int pathLength = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathCore(matrix, rows, cols, i, j, str, pathLength, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasPathCore(char[][] matrix, int rows, int cols, int row,
                                       int col, String str, int pathLength,  boolean[][] visited) {
        if (pathLength == str.length() - 1) {
            return true;
        }
        boolean hasPath = false;
        if (row >= 0 && row < rows && col <cols
                && matrix[row][col] == str.charAt(pathLength)
                && !visited[row][col]) {
            pathLength++;
            hasPath = hasPathCore(matrix, rows, cols, row - 1, col, str, pathLength, visited)
                    || hasPathCore(matrix, rows, cols, row + 1, col, str, pathLength, visited)
                    || hasPathCore(matrix, rows, cols, row, col - 1, str, pathLength, visited)
                    || hasPathCore(matrix, rows, cols, row, col + 1, str, pathLength, visited);
            if (!hasPath) {
                pathLength--;
                visited[row][col] = false;
            }
        }
        return hasPath;
    }
}
