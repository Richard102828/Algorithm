package algorithm.sort;

/**
 * 简单选择排序
 *
 */
public class SimpleSelectSort {
    private static void selectSort(int[] arr, int length) {
        if (arr == null || length <= 0)
            return;
        int minIndex = 0;
        //从头往后
        for (int i = 0; i < length; i++) {
            minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[minIndex])
                    minIndex = j;
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 3, 3, 2};
        selectSort(arr, arr.length);
        for (int a: arr) {
            System.out.println(a);
        }
    }
}
