package algorithm.search;

public class CommonBinarySearch {
    public static void main(String[] args) {

    }

    //二分查找
    private static int search(int[] num, int target) {
        if (num == null)
            return -1;
        int low = 0;
        int high = num.length - 1;
        int middle = 0;
        while (low <= high) {
            middle = (low + high) >> 1;
            if (num[middle] > target) {
                high = middle - 1;
            } else if (num[middle] < target) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
