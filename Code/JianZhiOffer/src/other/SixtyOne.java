package other;

import java.util.Arrays;

/**
 * 《剑指Offer》第二版，面试题 61: 扑克牌中的顺子
 *
 *      从扑克牌中随机抽5张牌，判断是不是一个顺子，即这五张牌是不是连续的。
 *      2~10是数字本身，A为1，J为11，Q为12，K为13，而大小王可以看成任意数字。
 *
 * 较好的解法：
 *      1. 用大小王填补间隔。因为题目说了，大小王可以看成任意数字，说明大小王是可以变的，可以用来填补间隔，使不连续的变成连续的。
 *          a. 这里就把大小王看成0，那么怎么填补才能成为顺子呢，只要间隔数 <= 大小王数就可以了
 *          b. 有大小王
 *              i. 间隔数 <= 大小王数？
 *          c. 没有大小王
 *              i. 是否连续？
 *          d. 是否有相同的牌？有就不是顺子
 *
 */
public class SixtyOne {
    /**
     * 大小王规定为0，调用的时候，传入0即可
     * @param nums
     * @param length
     * @return
     */
    private boolean isContinuous(int[] nums, int length) {
        //鲁棒性
        if (length < 5)
            return false;
        int joker = 0;
        //排序O(nlogn)
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0)
                joker++;
        }
        //有无大小王，最终得到的结果的判读是类似的，所以直接合为一步了
        int diff = 0;
        for (int i = length - 1; i >= 0 ; i--) {
            //统计间隔数
            diff += nums[i] - nums[i - 1] - 1;
            //是否存在相同的牌
            if (diff + 1 == 0)
                return false;
        }
        return diff <= joker;
    }
}
