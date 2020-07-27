package linkedlist;

/**
 * 《剑指Offer》第二版，面试题 22: 链表中倒数第k个节点
 *
 *      输入一个链表，输出该链表中倒数第k个结点
 *      链表结构为单链表
 *
 * 较好的解法：
 *      1. 这个问题看似简单，实则不然，特别是关于鲁棒性的检查
 *          a. 使用顺序遍历的方式，但是我们知道的是倒数第k个，但不知道链表的长度
 *              i. 规定只能使用一层循环，在这层循环中，我们要完成所有的任务
 *                  I. 找到链表长度 n，才能找到从头往尾数的节点的位置 n-k+1
 *                  II. 鲁邦性的检查！！！这个k没有规定是否大于链表长度
 *
 * 相关题目：
 *      求链表的中间节点
 * 解法：
 *      1. 和上面题型是类型的，不过这次没有给出具体的位置数字
 *          a. 还是可以求，中间节点与整个链表的比例是：1:2
 *          b. 所以我们还是设置两个指针，一个指针走一步，另外一个指针走两步就能达到这种1:2的效果了
 */
public class TwentyTwo {

    static class Node {
        String value;
        Node next;
    }

    public static void main(String[] args) {
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();
        Node node6 = new Node();
        node1.value = "node1";
        node1.next = node2;
        node2.value = "node2";
        node2.next = node3;
        node3.value = "node3";
        node3.next = node4;
        node4.value = "node4";
        node4.next = node5;
        node5.value = "node5";
        node5.next = node6;
        node6.value = "node6";
        node6.next = null;
        System.out.println(findKthToTail(node1, 3).value);
        //错误的输入，测试鲁棒性
        System.out.println(findKthToTail(node1, 20));
    }

    /**
     * 链表长度：我们可以设置两个指针，利用他们的差值找到长度，第一个指针先走k-1步，然后第二个指针这时候才开始从头走
     *      这样当第一个指针走到头时，第二个指针所在的节点就是我们要找的倒数第k个节点
     *  k是否大于链表长度？
     * @param head
     * @param k
     * @return
     */
    private static Node findKthToTail(Node head, int k) {
        //鲁棒性
        if (head == null || k <= 0) {
            return null;
        }
        //算法
        Node first = head;
        Node second = null;
        for (int i = 0; i < k - 1; i++) {
            //鲁棒性：当 k>链表长度时
            if (first.next == null)
                return null;
            else
                first = first.next;
        }
        second = head;
        while (first.next != null) {
            second = second.next;
            first = first.next;
        }
        return second;
    }
}
