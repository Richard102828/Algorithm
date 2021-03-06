package algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序的实现
 */
public class QuickSort {
    public static void main(String[] args)
    {
        int[] a = {1, 3, 9, 0};
        System.out.println(Arrays.toString(a));
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
    private static void quickSort(int[] source, int low, int high) {
        if (source == null || low == high) {
            return;
        }
        if (low < high) {
            int keyIndex = getIndex(source, low, high);
            quickSort(source, low, keyIndex - 1);
            quickSort(source, keyIndex + 1, high);
        }
    }

    private static int getIndex(int[] source, int low, int high) {
        int key = source[low];
        while (low < high) {
            while (low < high && source[high] > key) {
                high--;
            }
            source[low] = source[high];
            while (low < high && source[low] < key) {
                low++;
            }
            source[high] = source[low];
        }
        //将基准数填入数组中
        source[low] = key;
        return low;
    }

    private void testSort(int[] num, int left, int right) {
        if (num == null || left >= right)
            return;
        if (left < right) {
            int index = testIndex(num, left, right);
            testSort(num, left, index - 1);
            testSort(num, index + 1, right);
        }
    }

    private int testIndex(int[] num, int left, int right) {
        int key = num[left];
        while (left < right) {
            while (left < right && num[right] > key)
                right--;
            num[left] = num[right];
            while (left < right && num[left] < key)
                left++;
            num[right] = num[left];
        }
        num[left] = key;
        return left;
    }
}
