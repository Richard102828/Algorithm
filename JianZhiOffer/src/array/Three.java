package array;

import java.util.Arrays;

/**
 * 《剑指Offer》第二版，面试题 3：数组中重复的数字
 *
 *      在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 *      数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
 *      请找出数组中任意一个重复的数字。
 *      例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2或者3。
 *
 * 较好的解法：三种
 *      （排序后相邻元素两两比较）、（扫描数组，判断哈希表中是否存在）
 *      （利用题干信息：长度为n的数组里的所有数字都在0到n-1的范围内。进行下标的转换，边排序边比较）
 *
 * 有个变种：不允许修改原数组。这个算法看得有点懵，后面再实现（是通过二分法）来实现的
 */
public class Three {
    public static void main(String[] args) {
        int[] num = {2, 3, 5, 1, 4, 1};
        int[] repeatNum = new int[1];
        System.out.println(duplicate1(num, num.length, repeatNum));
        System.out.println(repeatNum[0]);
        System.out.println(duplicate2(num, num.length, repeatNum));
        System.out.println(repeatNum[0]);
    }

    /**
     * 排序后相邻元素两两比较
     * @param number
     * @param length
     * @param repeatNum
     * @return
     */
    private static boolean duplicate1(int[] number, int length, int[] repeatNum) {
        //鲁棒性
        if (number == null || length == 0) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (number[i] < 0 || number[i] > length - 1)
                return false;
        }

        //算法
        Arrays.sort(number);
        for (int i = 0; i < length - 1; i++) {
            if (number[i] == number[i + 1]) {
                repeatNum[0] = number[i];
                return true;
            }
        }
        return false;
    }

    /**
     * 利用题干信息
     * @param number
     * @param length
     * @param repeatNum
     * @return
     */
    private static boolean duplicate2(int[] number, int length, int[] repeatNum) {
        //鲁棒性
        if (number == null || length == 0) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (number[i] < 0 || number[i] > length - 1)
                return false;
        }

        //算法
        for (int i = 0; i < length; i++) {
            while (number[i] != i) {
                if (number[i] != number[number[i]]) {
                    //交换
                    int temp = number[i];
                    number[i] = number[number[i]];
                    number[number[i]] = temp;
                } else {
                    repeatNum[0] = number[i];
                    return true;
                }
            }
        }
        return false;
    }
}