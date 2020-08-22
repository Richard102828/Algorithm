package algorithm.sort;

/**
 * 归并排序的实现
 * 先分，后和
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] num = {7, 4, 5, 6};
        mergeSort(num, 0, num.length - 1);
        for (int a: num) {
            System.out.println(a);
        }
    }

    /**
     * 使用递归方式进行分割
     * @param num
     * @throws Exception
     */
    private static void mergeSort(int[] num, int left, int right) {
        if (num == null)
            return;
        if (left < right) {
            int mid = (left + right) >> 1;
            mergeSort(num, left, mid);
            mergeSort(num, mid + 1, right);
            mergeCore(num, left, mid, right);
        }
    }

    /**
     * 合并
     * @param num
     * @param left
     * @param mid
     * @param right
     */
    private static void mergeCore(int[] num, int left, int mid, int right) {
        int[] temp = new int[num.length];
        int leftStart = left;
        int rightStart = mid + 1;
        int tempIndex = left;

        while (leftStart <= mid && rightStart <= right) {
            if (num[leftStart] < num[rightStart])
                temp[tempIndex++] = num[leftStart++];
            else
                temp[tempIndex++] = num[rightStart++];
        }

        while (leftStart <= mid)
            temp[tempIndex++] = num[leftStart++];

        while (rightStart <= right)
            temp[tempIndex++] = num[rightStart++];

        //拷贝给原数组
        for (int i = left; i < tempIndex; i++)
            num[i] = temp[i];
    }
}
