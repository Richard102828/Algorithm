package other;

import java.util.LinkedList;
import java.util.List;

/**
 * 《剑指Offer》第二版，面试题 62: 圆圈中最后剩下的数字
 *
 *      （这是著名约瑟夫环问题）
 *      0, 1, 2...,n - 1这n个数字排成一个圆圈，一开始从数字0开始，从这个圆圈里删除第m个数字；
 *      然后从被删除的数字后一位开始计数，继续删除第m个数字...重复这个过程，直到最后只剩一个数字为止。
 *      求出这个圆圈里剩下的最后一个数字。
 *
 * 较好的解法：
 *      1. 用环形链表模拟圆圈，java中没有提供环形链表结构，我们可以通过对指针的跳转实现环形的效果（当然，也可以自己实现一个环形链表）
 *          a. 删除元素：指针走了指定了步数，就可以删除元素了
 *          b. 指针的跳转：
 *              i. 走到链表末尾时，需要跳转到头部
 *              ii. 删除链表末尾元素时，也要跳转到头部
 *      2. 使用取余操作模拟环形问题，经观察得到公式：removeIndex = （removeIndex + (m -1)） % list.size
 *          a.  int removeIndex = 0;
 *              while (list.size() > 1) {
 *                  // 关键是这句
 *                  removeIndex = (removeIndex + m - 1) % list.size();
 *                  list.remove(removeIndex);
 *              }
 *      3. 约瑟夫问题：(暂时没有理解到，记住公式)
 *          a. 递归公式：要得到n个数字的序列中最后剩下的数字，只需要得到 n - 1 个数字的序列中最后剩下的数字，以此类推
 *                         0                       n = 1
 *              f(n,m) =   [f(n-1, m) + m] % n     n > 1
 */
public class SixtyTwo {

    /**
     * 第一种解法，使用环形链表模拟圆圈
     * @param n
     * @param m
     * @return
     */
    private int getLastRemaining(int n, int m) {
        //鲁棒性
        if (n < 1 || m < 1)
            return -1;
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int p = 0;
        while (list.size() > 1) {
            //没有用 <=，因为p所代表的是下标索引
            for (int i = 1; i < m; i++) {
                p++;
                if (p == list.size())
                    p = 0;
            }
            list.remove(p);
            //删除的是末尾的元素
            if (p == list.size())
                p = 0;
        }
        return list.get(0);
    }

    /**
     * 第三种解法，约瑟夫问题，递归公式，时间按复杂度O(n)
     * @param n
     * @param m
     * @return
     */
    private int getLast(int n, int m) {
        //鲁棒性
        if (n < 1 || m < 1)
            return -1;
        int last = 0;
        for (int i = 2; i < n; i++) {
            last = (last + m) % i;
        }
        return last;
    }
}
