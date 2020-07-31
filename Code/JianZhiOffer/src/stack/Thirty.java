package stack;

import java.util.LinkedList;
import java.util.Random;

/**
 * 《剑指Offer》第二版，面试题 30: 包含 min 函数的栈
 *
 *      定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 *      要求push、pop、min方法的时间复杂度都为O(1)
 *
 * 较好的解法：
 *      1. 这个栈中实现两个小栈，外加一个代表栈最小值的变量
 *          a. 一个栈来装我们push进去的元素
 *          b. 另一个栈用来装最小值的集合
 *              i. 因为当前的最小值是要随着我们添加元素进行变化的，所以每push一次，就将最小值加入到这个栈中
 *              ii. 当然，每次都要进行比较，如果当前最小值还是最小的，就再将它push到最小栈中
 *          c. 每次push元素，要去判断是否要更新最小值变量的值，每次添加到最小栈中的元素就是这个变量
 *
 */
public class Thirty {
    public static void main(String[] args) {
        Stack stack = new Stack();
        for (int i = 0; i < 5; i++) {
            int temp = new Random().nextInt(5);
            stack.push(temp);
            System.out.println(temp);
            System.out.println("最小");
            System.out.println(stack.min());
        }
        for (int i = 0; i < 5; i++) {
            stack.pop();
            System.out.println(stack.min());
        }
    }

    /**
     * 里面使用linkedList作为栈
     */
    private static class Stack {
        private static int minNumber;
        private static LinkedList<Integer> stackNums = new LinkedList<>();
        private static LinkedList<Integer> stackMin = new LinkedList<>();

        public void push(Integer e) {
            if (stackNums.size() == 0) {
                minNumber = e;
            } else {
                if (e < minNumber)
                    minNumber = e;
            }
            stackNums.push(e);
            stackMin.push(minNumber);
        }

        public Integer pop() {
            if (stackNums.size() > 0 && stackMin.size() > 0) {
                stackMin.pop();
                if (stackNums.size() > 0 && stackMin.size() > 0)
                    minNumber = stackMin.getFirst();
                else
                    minNumber = 0;
                return stackNums.pop();
            }
            return 0;
        }

        public Integer min() {
            return minNumber;
        }
    }
}
