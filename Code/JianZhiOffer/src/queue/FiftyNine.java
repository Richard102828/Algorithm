package queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 《剑指Offer》第二版，面试题 59: 队列的最大值
 *
 *      题目一：滑动窗口的最大值
 *          给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。
 *          例如，如果输入数组{2, 3, 4, 2, 6, 2, 5}以及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}
 *      题目二：队列的最大值
 *          定义一个队列，实现max方法得到队列中的最大值。
 *          要求入列、出列以及取最大值的方法时间复杂度都是O(1)
 *
 * 较好的解法：
 *      题目一：
 *          1. 基于最大堆的优先队列
 *              a. 堆里面就只存指定的个数，然后每次保存了最大值之后，就删除第一个元素，再添加元素，重复即可
 *          2. 使用双端队列，队列中存入的都是元素对应在数组上的索引
 *              a. 首先，队列头部存最大的元素
 *                  i. 如果，队列头部已经滑出滑动窗口中了，就删除它
 *                  ii. 来了一个比头部元素还要大的元素，就删除前面的所有元素，将这个元素位列队列头部
 *              b. 其次，队列尾部存的是有可能成为最大值的元素，因为当队列头部滑出后，它就是最大的了
 *                  i. 如果下一个元素比尾部元素大，那么更新尾部元素，因为当前的尾部元素既比对头元素小，
 *                  又没有即将加如的元素大，所以它是不可能成为最大的元素的
 *              c. 我们存入的是索引，根据索引来判断是否滑出滑动窗口
 *                  i. 当前访问元素 - 队列头部元素 >? 滑动窗口大小
 *      题目二：
 *          1. 与 “包含min函数的栈” 的实现类似的。使用两个队列，一个正常的存放数据，一个存最大值
 *          2. 与上题类似的，不过还是要用两个队列，一个正常的存放数据，一个存放最大值
 */
public class FiftyNine {

    public static void main(String[] args) {
        FiftyNine nine = new FiftyNine();
        ArrayList<Integer> list = nine.maxWindows2(new int[] {2, 3, 4, 2, 6, 1, 5, 1}, 3);
        for (int a: list) {
            System.out.println(a);
        }
    }

    /**
     * 题目一：使用双端队列来实现，队列中存元素的下标
     * @param num
     * @param size
     * @return
     */
    private ArrayList<Integer> maxWindows2(int[] num, int size) {
        //鲁棒性
        if (num == null || size <= 0)
            return null;
        ArrayList<Integer> results = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < num.length; i++) {
            //注意这里是一个while循环，这一步除了更新队尾元素的作用，还保证了最大值始终是在队列头部
            while (!deque.isEmpty() && num[i] >= num[deque.peekLast()])
                deque.pollLast();
            //滑出滑动窗口？
            if (!deque.isEmpty() && i - deque.peekFirst() >= size)
                deque.pollFirst();
            deque.addLast(i);
            //达到size大小，开始记录最大值
            if (i + 1 >= size) results.add(num[deque.peekFirst()]);
        }
        return results;
    }

    /**
     * 题目二：使用与 "含min函数的栈" 类似的思想实现
     */
    static class QueueWithMaxFun {
        private Queue<Integer> dataQueue = new LinkedList<>();
        private Queue<Integer> maxQueue = new LinkedList<>();

        private void push(Integer e) {
            if (e != null) {
                if (maxQueue.size() != 0) {
                    if (e >= maxQueue.peek())
                        maxQueue.add(e);
                    else
                        maxQueue.add(maxQueue.peek());
                } else {
                    maxQueue.add(e);
                }
                dataQueue.add(e);
            }
        }

        private Integer pop() {
            maxQueue.poll();
            return dataQueue.poll();
        }

        private Integer max() {
            return maxQueue.peek();
        }
    }
}
