package algorithm;

import netscape.security.UserTarget;

/**
 * 《剑指Offer》第二版，面试题 10：斐波那契数列
 *
 *      写一个函数，输入n，求斐波那契数列的第n项
 *
 * 较好的解法：
 *      使用循环迭代的方式，从底向上，从0~n来求，将重复的去除
 *      不要使用递归，递归会重复很多！！！
 *
 * 相关题目：
 *      青蛙跳台阶问题:
 *          一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *      矩形覆盖问题：
 *          我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * 解法：
 *      青蛙：看作斐波那契数列：
 *          到达1级台阶只有1种可能，到达2级台阶有2种可能；可记为f(1) = 1,f(2) = 2。
 *          要到达3级台阶，可以选择在1级台阶处起跳，也可以选择在2级台阶处起跳，所以只需求到达1级台阶的可能情况 + 到达2级台阶的可能情况，
 *          即f(3) = f(2) +f(1)
 *          同理到达n级台阶，可以在n-1级台阶起跳，也可在n-2级台阶起跳，f(n) = f(n-2)+f(n-1)
 *      矩形：看作斐波那契数列：
 *          一个小矩形的覆盖方式只有两种：横着或者竖着放。覆盖`2*n`的矩形的方法记为f(n)
 *              横着放：如在左上角横着放，那么它下面肯定要横着放一个，这样就剩下2*(n-2)个空间了，记为f(n-2)
 *              竖着放：如在左边竖着放，那么还剩下2*(n-1)个空间，记为f(n-1)
 *              所以，f(n) = f(n-1) + f(n-2)
 */
public class Ten {
    public static void main(String[] args) {
        System.out.println(fibonacci(3));
        System.out.println("frog 1、2: " + frogJumpingTheSteps(4));
    }

    /**
     * 斐波那契数列
     * @param n
     * @return
     */
    private static int fibonacci(int n) {
        //鲁棒性
        if (n < 0) {
            return 0;
        }

        //算法
        int result = 0;
        int fibReduceOne = 1;
        int fibReduceTwo = 0;
        if (n == 0) {
            return result;
        } else if (n == 1) {
            result++;
            return result;
        } else {
            for (int i = 2; i <= n; i++) {
                result = fibReduceOne + fibReduceTwo;
                fibReduceOne = result;
                fibReduceTwo = fibReduceOne;
            }
        }
        return result;
    }

    private static int frogJumpingTheSteps(int steps) {
        //鲁棒性
        if(steps < 0) {
            return 0;
        }

        //算法
        int methodNum = 0;
        int jumpOne = 1;
        int jumpTwo = 2;
        for (int i = 1; i < steps; i++) {
            methodNum = jumpOne + jumpTwo;
            jumpOne = methodNum;
            jumpTwo = jumpOne;
            steps--;
        }
        return methodNum;
    }
}