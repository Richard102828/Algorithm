package linkedlist;

/**
 * 《剑指Offer》第二版，面试题 52: 两个链表中的第一个公共节点
 *
 *      输入两个单链表，找出它们的第一个公共结点。
 *
 * 较好的解法：
 *      1. 观察两个具有公共节点的链表，可以发现，从第一个公共节点开始，后面的节点都是相同的
 *          a. 我们可以从后面往前遍历，找到最后一个相同的节点即可
 *          b. 因为是单链表，我们不能直接实现从后往前的遍历，我们可以借助栈来完成
 *          c. 所需的空间复杂度为O(n+m)，时间复杂度为O(n+m)
 *      2. 其实主要的难点就是这两个链表的长度，我们不知道是不是一样长的，如果长度相同的话，直接从头开始一边遍历一边比较即可
 *          a. 尽管长度不同，但是我们可以在某种意义上，使其长度相同
 *          b. 链表的遍历，就是使用指针，我们可以找到这两个链表的长度差，让较长的链表先遍历，然后再一起一边遍历一边比较即可
 *          c. 时间复杂度为O(n + m)，没有额外的空间消耗
 *      3. 第二种方法，我们的目的就是要让指针对齐，然后才开始遍历
 *          a. 其实可以只使用一次循环就能然指针对齐了
 *              i. 两个链表同时开始循环，走完后，就转到另一个链表上继续循环
 *              ii. 较短的那一个肯定会先走完，这时候它转到较长的链表上了
 *              iii. 当较长的链表转到较短的链表时，这时候两个指针就对齐了，就可以开始比较了
 */
public class FiftyTwo {
    static class Node {
        int value;
        Node next;
    }

    /**
     * 方式二
     * @param node1
     * @param node2
     * @return
     */
    private Node getFirstPublicNode(Node node1, Node node2) {
        //鲁棒性
        if (node1 == null || node2 == null || node1.next == null || node2.next == null)
            return null;
        Node temp = node1;
        int nodeCount1 = 0;
        int nodeCount2 = 0;
        while (temp.next != null) {
            nodeCount1++;
            temp = temp.next;
        }
        temp = node2;
        while (temp.next != null) {
            nodeCount2++;
            temp = temp.next;
        }
        Node p1 = node1;
        Node p2 = node2;
        int distance = Math.abs(nodeCount1 - nodeCount2);
        while (distance != 0) {
            if (nodeCount1 - nodeCount2 > 0)
                p1 = p1.next;
            else
                p2 = p2.next;
            distance--;
        }

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    /**
     * 方式三
     * @param node1
     * @param node2
     * @return
     */
    private Node getFirstNodeByEasily(Node node1, Node node2) {
        //鲁棒性
        if (node1 == null || node2 == null || node1.next == null || node2.next == null)
            return null;
        Node p1 = node1;
        Node p2 = node2;
        //一次循环完链表对齐，下次循环就找到节点了
        while (p1 != p2) {
            p1 = p1.next == null ? node2 : p1.next;
            p2 = p2.next == null ? node1 : p2.next;
        }
        return p1;
    }
}
