package stack;

import java.util.LinkedList;

/**
 * 《剑指Offer》第二版，面试题 9：两个栈实现队列
 *
 *      用两个栈实现一个队列，队列中含有两个栈的声明，请实现它的函数 appendTail 和 deleteHead,
 *      分别完成在队列尾部插入节点和在队列头部删除节点的功能
 *
 * 较好的解法：
 *      1. 两个栈，一个 “进栈”，一个 “出栈”
 *          b. 这样将元素都弄进另外一个栈里面，那个栈里面的元素顺序都是反着的，就相当于实现了一个队列了
 *
 * 相关题目：使用两个队列实现一个栈
 * 我的解法：
 *      1. 两个队列都是先进先出的，所以栈顶的元素就在队列末尾
 *          a. add操作：第一次选一个队列入队，后面的就向不为空的队列入队
 *          b. poll操作：先向另外一个空队列转移元素（只剩队列尾部一个），然后将这个出队即可
 */
public class Nine {

    static class Queue {
        LinkedList<Integer> inStack = new LinkedList<>();
        LinkedList<Integer> outStack = new LinkedList<>();

        void appendTail(int value) {
            inStack.push(value);
        }

        /**
         * 先转移元素，再进行删除
         */
        void deleteHead() {
            if (outStack.size() == 0) {
                while (inStack.size() != 0) {
                    outStack.push(inStack.pop());
                }
            }
            outStack.pop();
        }

        void printQueue() {
            if (outStack.size() <= 0) {
                while (inStack.size() != 0) {
                    outStack.push(inStack.pop());
                }
            }
            for (int i = 0; i < outStack.size(); i++) {
                System.out.println(outStack.get(i));
            }
        }
    }

    static class Stack {
        java.util.Queue queue1;
        java.util.Queue queue2;
        static boolean pushToQueueFirst;

        public Stack() {
            queue1 = new LinkedList<Integer>();
            queue2 = new LinkedList<Integer>();
            pushToQueueFirst = true;
        }

        void push(int value) {
            if (value < 0) {
                return;
            }
            if (pushToQueueFirst) {
                queue1.add(value);
            } else {
                queue2.add(value);
            }
        }

        int pop() {
            //转移元素
            if (queue1.size() != 0) {
                while (queue1.size() != 1) {
                    queue2.add(queue1.poll());
                }
            } else if (queue2.size() != 0) {
                while (queue2.size() != 1) {
                    queue1.add(queue2.poll());
                }
            }

            //弹栈
            int stackTop = 0;
            if (queue1.size() == 1) {
                stackTop = (int) queue1.poll();
                pushToQueueFirst = true;
            } else if (queue2.size() == 1) {
                stackTop = (int) queue2.poll();
                pushToQueueFirst = false;
            }
            return stackTop;
        }

        void printQueue() {
            if (queue1.size() != 0) {
                for (int i = 0; i < queue1.size(); i++) {
                    System.out.println(queue1.toArray()[i]);
                }
            } else if (queue2.size() != 0) {
                for (int i = 0; i < queue2.size(); i++) {
                    System.out.println(queue2.toArray()[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        for (int i = 0; i < 5; i++) {
            queue.appendTail(i);
        }
        queue.printQueue();
        System.out.println("删除队列头部元素");
        queue.deleteHead();
        queue.printQueue();

        Stack stack = new Stack();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        stack.printQueue();
        System.out.println("弹栈操作执行···");
        stack.pop();
        stack.printQueue();
    }
}