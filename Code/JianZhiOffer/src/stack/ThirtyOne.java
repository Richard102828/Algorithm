package stack;

import java.util.LinkedList;

/**
 * 《剑指Offer》第二版，面试题 31: 栈的压入、弹出序列
 *
 *      输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出序列。
 *      假设压入栈的所有数字均不相等。
 *      例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列
 *      （注意：这两个序列的长度是相等的）
 *
 * 较好的解法：
 *      1. 使用一个辅助栈，两个指针分别指向入栈序列、弹栈序列的头部
 *          a. 一个循环去判断两个指针指向的数字是否相同
 *              i. 不同，则将入栈指针指向的数字压入辅助栈中，后移入栈指针
 *              ii. 相同，则两个指针分别后移，重复比较
 *          b. 入栈指针达到序列末尾了，就不再操作这个指针了，转去操作辅助栈，将栈顶元素与弹栈序列中的数字进行比较
 *              i. 相同，辅助栈弹栈，弹栈指针后移
 *              ii. 不同，则第二个序列不是该栈的弹出序列
 *
 */
public class ThirtyOne {
    public static void main(String[] args) {
        int[] pushOrder = {1, 2, 3, 4, 5};
        int[] popOrder = {3, 4, 5, 2, 1};
        System.out.println(isPopOrder(pushOrder, popOrder, pushOrder.length));
    }

    /**
     * 辅助栈使用LinkedList
     * @param pushOrder
     * @param popOrder
     * @param length
     * @return
     */
    private static boolean isPopOrder(int[] pushOrder, int[] popOrder, int length) {
        //鲁棒性
        if (pushOrder == null || popOrder == null || length <= 0)
            return false;
        //算法
        int pPush = 0;
        int pPop = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        //入栈指针应该总是先于弹栈指针到达末尾
        while (pPush <= length - 1) {
            if (pushOrder[pPush] != popOrder[pPop])
                stack.push(pushOrder[pPush]);
            else
                pPop++;
            pPush++;
        }
        //如果上面循环完了，辅助栈大小是空的，则直接可以判断是弹出序列了
        if (stack.isEmpty())
            return true;

        while (!stack.isEmpty()) {
            if (stack.peek() == popOrder[pPop]) {
                stack.pop();
                pPop++;
            } else {
                return false;
            }
        }
        return true;
    }
}
