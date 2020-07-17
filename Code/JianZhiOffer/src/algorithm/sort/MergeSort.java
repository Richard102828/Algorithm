package algorithm.sort;

/**
 * 归并排序的实现
 */
public class MergeSort {
    public static void main(String[] args) {

    }

    private static void mergeSort(int[] num) throws Exception{
        if (num == null) {
            throw new Exception("无效的输入");
        }
        mergeCore(num, num.length);
    }

    private static void mergeCore(int[] num, int length) {
        int[] temp = new int[length];
        int index1 = 0;
        int index2 = 0;
        
    }
}
