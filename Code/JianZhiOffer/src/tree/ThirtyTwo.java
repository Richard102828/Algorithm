package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 《剑指Offer》第二版，面试题 32: 从上到下打印二叉树
 *
 *      题目一：不分行，从上往下打印出二叉树的每个节点，同层节点从左至右打印。即层序遍历（广度优先遍历）
 *      题目二：分行，每打印完一层，就换行
 *      题目三：之字打印二叉树（Z型也差不多的）
 *          a. 第一层从左到右打印，第二层从右到左打印，第三层从左到右打印···
 *
 * 较好的解法：
 *      1. 就是二叉树的层序遍历
 *          a. 可以使用队列，题目就是一个先进先出的结构
 *          b. 使用一个变量，表示当前出队的节点
 *              i. 根节点: 入队，然后出队打印，并将根节点的左右节点入队
 *              ii. 其余节点: 出队打印，将节点的左右节点入队，重复
 *      2. 分行，还是使用队列，打印上操作一样
 *          a. 设置两个变量，一个表示当前层还剩多少个要打印，另一个表示下一层要打印多少个
 *              i. 当前层变量没有打印完时，下一层变量继续增加
 *              ii. 当前层变量打印完时，下一层变量停止增加，然后加下一层变量赋值给当前层变量
 *      3. 之字（Z字）打印，并且分行了
 *          a. 找到规律：奇数行是从左到右打印的，偶数行是从右到左打印的
 *              i. 可以使用两个栈，分别来装奇数行与偶数行的数字
 *              ii. 如果当前打印的是奇数行，则下一行要打印偶数行，所以先将当前节点的左子节点入偶数栈
 *              iii. 如果当前打印的是偶数行，则一行要打印奇数行，所以先将当前节点的右子节点入奇数栈
 *
 */
public class ThirtyTwo {

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }

    public static void main(String[] args) {
        //构建二叉树
        TreeNode root = new TreeNode();
        TreeNode node1 = new TreeNode();
        TreeNode node2 = new TreeNode();
        TreeNode node3 = new TreeNode();
        TreeNode node4 = new TreeNode();
        TreeNode node5 = new TreeNode();
        root.value = 0;
        node1.value = 1;
        node2.value = 2;
        node3.value = 3;
        node4.value = 4;
        node5.value = 5;
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        printTreeFromTopToBottom(root);
        System.out.println();
        printTreeFromTopToBottomDivide(root);
        System.out.println();
        printTreeFromTopToBottomByZ(root);
    }

    /**
     * 不分行打印
     * @param node
     */
    private static void printTreeFromTopToBottom(TreeNode node) {
        //鲁棒性
        if (node == null)
            return;
        //算法
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        TreeNode tempNode = null;
        while (!queue.isEmpty()) {
            tempNode = queue.poll();
            System.out.print(tempNode.value);
            if (tempNode.left != null)
                queue.add(tempNode.left);
            if (tempNode.right != null)
                queue.add(tempNode.right);
        }
    }

    /**
     * 分行打印
     * @param node
     */
    private static void printTreeFromTopToBottomDivide(TreeNode node) {
        //鲁棒性
        if (node == null)
            return;
        //算法
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        TreeNode tempNode = null;
        int rightNowNeedToPrintNums = 1;
        int nextNeedToPrintNums = 0;
        while (!queue.isEmpty()) {
            tempNode = queue.poll();
            System.out.print(tempNode.value);
            rightNowNeedToPrintNums--;
            if (tempNode.left != null) {
                queue.add(tempNode.left);
                nextNeedToPrintNums++;
            }
            if (tempNode.right != null) {
                queue.add(tempNode.right);
                nextNeedToPrintNums++;
            }
            if (rightNowNeedToPrintNums == 0) {
                rightNowNeedToPrintNums = nextNeedToPrintNums;
                nextNeedToPrintNums = 0;
                System.out.println();
            }
        }
    }

    /**
     * 之字(Z字)打印
     * 有空指针问题，还未解决
     * @param node
     */
    private static void printTreeFromTopToBottomByZ(TreeNode node) {
        //鲁棒性
        if (node == null)
            return;
        //算法
        LinkedList<TreeNode> oddStack = new LinkedList<>();
        LinkedList<TreeNode> evenStack = new LinkedList<>();
        oddStack.add(node);
        TreeNode tempNode = null;
        while (!oddStack.isEmpty() || !evenStack.isEmpty()) {
            if (!oddStack.isEmpty()) {
                while (!oddStack.isEmpty()) {
                    tempNode = oddStack.pop();
                    System.out.print(tempNode.value);
                    if (tempNode.left != null)
                        evenStack.push(tempNode.left);
                    if (tempNode.right != null)
                        evenStack.push(tempNode.right);
                }
                System.out.println();
            } else {
                while (!evenStack.isEmpty()) {
                    tempNode = evenStack.pop();
                    System.out.print(tempNode.value);
                    if (tempNode.left != null)
                        oddStack.push(tempNode.right);
                    if (tempNode.right != null)
                        oddStack.push(tempNode.left);
                }
                System.out.println();
            }
        }
    }
}
