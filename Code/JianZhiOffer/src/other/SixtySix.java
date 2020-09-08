package other;

/**
 * 《剑指Offer》第二版，面试题 66: 构建乘积数组
 *
 *      给定一个数组A[0, 1,...n - 1],请构建一个数组B[0, 1,...n - 1]，
 *      其中B中的元素B[i] = A[0]*A[1]*...*A[i - 1]*A[i + 1]*...*A[n - 1]
 *      不能使用除法
 *
 * 较好的解法：
 *      1. 这个B中的元素就是少乘了一个 A[i]，不使用除法，我们可以使用位运算嘛！
 *          a. 这样先计算 A中总的元素之和，然后进行一次循环
 *              i. 每次循环使用位运算 >> n，n为被除数的2的幂
 *              ii. 然后给B赋值即可
 *      2. 观察题目，将B[i]拆分为两次求积，以少剩的 A[i]为分割线
 *          i. C[i] = A[0]*A[1]*...*A[i-1]，D[i] = A[i+1]*A[i+2]*...*A[n-1]
 *          ii. 观察可得：C[i] = C[i-1]*A[i-1]，D[i] = A[i+1]*D[i+1]
 *              a. 这里注意最后的B[n-1]，只能对应到C[i]中，C[n-1] = A[0]*A[1]*...*A[i-2]
 *              b. 所以，在算D[i]的时候，应该从n - 2开始算
 *          iii. 两次循环，第一次求C[i]，第二次求D[i]并乘起来赋值即可
 */
public class SixtySix {
    private int[] createMultiplyArray(int[] a, int length) {
        //鲁棒性
        if (a == null || length <= 0)
            return null;
        int[] b = new int[length];
        //这里b代表的是第一部分c
        b[0] = a[0];
        for (int i = 1; i < length; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        //第二部分d
        int d = 1;
        //从后面d[i+1]开始往前算的
        for (int i = length - 2; i >= 0; i--) {
            d *= a[i + 1];
            b[i] = b[i] * d;
        }
        return b;
    }
}
