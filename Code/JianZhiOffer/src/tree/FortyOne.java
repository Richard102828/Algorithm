package tree;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 《剑指Offer》第二版，面试题 41: 数据流中的中位数
 *
 *      如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 *      如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *          中位数：指排序好的数字中的中间数，（分为奇数->中间数，偶数->中间两个数字的平均值）
 * 较好的解法：
 *      1. 对于中位数来说，左边的数都要小于它，右边的数都要大于它。
 *          a. 而左边的第一个数就是左边数字中最大的，右边第一个数是右边数字中最小的
 *              i. 这样就能够想到使用最大堆、最小堆来分别实现
 *          b. 而实际上，我们并不需要对左边、右边的全部数字进行排序，只需要找到最大值、最小值即可
 *              i. 所以最大堆、最小堆是最适合的，堆中插入数据的时间复杂度为O(logn)，查找数据的时间复杂度仅为O(1)
 *          c. 接下来考虑要将数据均分给两个堆的情况
 *              i. 规定默认为：数据流中数据个数为奇数则分给最小堆，个数为偶数则分给最大堆
 *              ii. 插入数据的时候，要对数据进行判断，例如：当前是奇数个数据，新数据应该插入最小堆，但是新数据小于最大堆的根节点
 *              那么，就应该将最大堆的根节点（最大值）换到最小堆中，然后将新数据插入最大堆中。偶数个时要进行类似的判断
 */
public class FortyOne {
    private PriorityQueue<Integer> minPQ = new PriorityQueue<>();
    private PriorityQueue<Integer> maxPQ = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    private int count;

    private void insert(Integer num) {
        //鲁棒性，处理第一次插入数据，以防后面发生空指针异常
        if (count == 0) {
            maxPQ.offer(num);
            //数据为偶数个
        } else if ((count & 1) == 0) {
            if (num > minPQ.peek()) {
                int temp = minPQ.poll();
                minPQ.offer(num);
                maxPQ.offer(temp);
            } else {
                maxPQ.offer(num);
            }
            //数据为奇数个
        } else if ((count & 1) == 1) {
            if (num < maxPQ.peek()) {
                int temp = maxPQ.poll();
                maxPQ.offer(num);
                minPQ.offer(temp);
            } else {
                minPQ.offer(num);
            }
        }
        count++;
    }

    private Integer getMedian() {
        //因为最小堆要多一个数，所以中间数在最小堆中的
        if ((count & 1) == 0)
            return minPQ.peek();
        else
            return (minPQ.peek() + maxPQ.peek()) >> 1;
    }
}
