package algorithm.sort;

/**
 * 冒泡排序
 * 时间复杂度：O(n ^ 2)
 */
public class BubbleSort {
    private static void bubbleSort(int[] arr, int length) {
        if (arr == null || length <= 0)
            return;
        for (int i = 0; i < length; i++) {
            //相邻元素从后往前比
            for (int j = length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 3, 3, 2};
        bubbleSort(arr, arr.length);
        for (int a: arr) {
            System.out.println(a);
        }
    }
}
