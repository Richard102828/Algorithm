package array;

/**
 * 《剑指Offer》第二版，面试题 4：二维数组中的查找
 *
 *      在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 *      完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 较好的解法：
 *      和右上角的元素比较不断的缩小范围直至找到目标整数
 */
public class Four {
    public static void main(String[] args) {
        int[][] array = {{2, 3, 5}, {3, 4, 6}, {4, 6, 7}};
        System.out.println(findNum1(7, array));
    }

    /**
     * 和右上角的元素比较不断的缩小范围直至找到目标整数
     * @param target
     * @param array
     * @return
     */
    private static boolean findNum1(int target, int[][] array) {
        //鲁棒性
        if (target < 0 || array == null) {
            return false;
        }

        //算法
        boolean isFind = false;
        int row = 0;
        int column = array[0].length - 1;
        if (column >= 0) {
            while (column >= 0 &&  row < array.length) {
                if (target == array[row][column]) {
                    isFind = true;
                    break;
                } else if (target < array[row][column]) {
                    column--;
                } else if (target > array[row][column]) {
                    row++;
                }
            }
        }
        return isFind;
    }
}
