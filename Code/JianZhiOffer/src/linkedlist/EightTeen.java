package linkedlist;

/**
 * 《剑指Offer》第二版，面试题 18: 删除链表的结点
 *
 *      给定单向链表的头指针和一个结点指针，定义一个函数在O(1)时间内删除该结点。
 *      假设要删除的结点确实在链表中。
 *
 * 较好的解法：
 *      1. 由于是单向链表，所以想要删除一个节点的话，一般得顺序遍历到该节点的前一个节点
 *          a. 但是这样的话，时间复杂度就是O(n)了
 *      2. 其实，也不是非要找到一个节点的前一个节点的。我们可以通过将下一个节点的内容复制到当前节点，然后删除下一个节点就行了
 *          a. 找不到前一个节点，但是我们能找到下一个节点呀，脑筋一定要灵活一点
 *
 *  相关题目：删除链表中重复的节点
 *  我的解法：
 *      1. 首先，对于链表，先考虑指针，由于我们这里要进行删除，并且在删除后，要将链表给连接起来，所以这里使用两个指针
 *          a. 一个指针表示当前节点，用来比较值、移动
 *          b. 一个指针表示当前节点的前面第一个不重复的节点
 *          c. 确定函数参数为头节点（因为要进行顺序遍历，所以要从头节点开始）
 */
public class EightTeen {

    /**
     * 定义节点结构
     * 单向链表
     */
    static class Node {
        String value;
        Node next;
    }
    public static void main(String[] args) {

    }

    /**
     * 使用第二种解法，为三种情况
     * 1. 头节点
     * 2. 中间节点
     * 3. 尾结点 使用顺序遍历，找到节点的上一个节点，然后进行删除操作
     * @param head
     * @param node
     */
    private static void deleteNode(Node head, Node node) {
        //鲁棒性
        if (head == null || node == null) {
            throw new IllegalStateException("无效的输入");
        }
        //算法
        //删除头节点
        if (head == node) {
            node = null;
            head = null;
        } else if (head.next != null && node.next != null) {
            //删除中间节点
            Node nextNode = node.next;
            node.value = nextNode.value;
            node.next = nextNode.next;
            nextNode.next = null;
        } else {
            //删除尾结点
            Node nextNode = head;
            while (nextNode.next != node) {
               nextNode = nextNode.next;
            }
            nextNode.next = node.next;
            node.next = null;
        }
    }

    /**
     * 相关题目：删除链表中重复的节点
     * @param head
     */
    private static void deleteRepeatNodes(Node head) {
        //鲁棒性
        if (head == null || head.next == null) {
            return;
        }
        //算法
        Node pPreNode = null;
        Node pNode = head;
        while (pNode != null && pNode.next != null) {
            //删除重复一个value的节点
            String val = null;
            if (pNode.value == pNode.next.value) {
                val = pNode.value;
            while (val == pNode.value) {
                if (val != pNode.next.value) {
                    //将重复的最后一个节点的next指针置空
                    Node temp = pNode;
                    pNode = pNode.next;
                    temp.next = null;
                } else {
                    pNode = pNode.next;
                }
            }

            pPreNode.next = pNode;
            } else {
                pPreNode = pNode;
                pNode = pNode.next;
            }
        }
    }
}
