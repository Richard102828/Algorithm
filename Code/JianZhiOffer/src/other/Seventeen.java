package other;

/**
 * 《剑指Offer》第二版，面试题 17: 打印从1到最大的n位十进制数（大数问题）
 *
 *      输入数字n，按顺序打印处1到最大的n位十进制数，比如输入3，则打印1~999之间的数
 *
 * 较好的解法：
 *  分了两种，分别代表数组、字符串的实现。
 *  其中，字符串的实现使用StringBuilder来实现
 *      （这里的实现与书上的不同，因为Java的String对象是不可变对象，每次修改元素的值，都会产生一个String对象，感觉这些空间是没必要的）
 *      1. 在字符串上模拟数字的加法
 *          a. 元素的进位要进行处理
 *          b. 判断最大值
 *          c. 如何打印
 *      2. 将问题转化为数字排列问题：用递归来实现全排列
 * 我的解法：
 *    使用数组来表示大数，根据传入的n来初始化数组大小
 */
public class Seventeen {
    public static void main(String[] args) {
        //数组实现
        printOneToMaxOfNDigitsByArray(3);
        //递归实现
        printOneToMaxOfNDigits(3);
    }

    /**
     * 使用数组的方式来实现
     * @param n
     */
    private static void printOneToMaxOfNDigitsByArray(int n) {
        //鲁棒性
        if (n <= 0) {
            throw new IllegalStateException("无效的输入");
        }
        int[] number = new int[n];
        while (!increment(number)) {
            printNumber(number);
        }
    }

    /**
     * 对数组中的元素加一，直到达到最大值
     * 无论何时都是个位开始加的（即最后一位）
     * @param number
     * @return
     */
    private static boolean increment(int[] number) {
        if (number == null) {
            return true;
        }
        //用来进位的
        int nTakeOver = 0;
        boolean isOverFlow = false;
        //从最后一个元素开始到第一个元素，所以循环为倒序
        for (int i = number.length - 1; i >= 0 ; i--) {
            int nSum = number[i] + nTakeOver;
            if (i == number.length - 1)
                nSum++;
            //进位判断
            if (nSum >= 10) {
                //溢出？
                if (i == 0)
                    isOverFlow = true;
                else {
                    nSum -= 10;
                    nTakeOver = 1;
                    number[i] = nSum;
                }
            } else {
                number[i] = nSum;
                break;
            }
        }
        return isOverFlow;
    }

    /**
     * 打印数组元素
     * 只在碰到第一个不为0的元素时，开始打印
     * 每次打印完换行
     * @param number
     */
    private static void printNumber(int[] number) {
        boolean isBeginning = false;
        boolean isWrap = false;
        for (int i = 0; i < number.length; i++) {
            if (!isBeginning && number[i] != 0)
                isBeginning = true;
            if (i == number.length - 1)
                isWrap = true;

            if (isBeginning) {
                if (!isWrap)
                    System.out.print(number[i]);
                else
                    System.out.println(number[i]);
            }
        }
    }

    /**
     * 使用字符串的方式来实现
     * 算法与数组是类似的
     * @param n
     */
    private static void printOneToMaxOfNDigitsByString(int n) {
        if (n <= 0) {
            throw new IllegalStateException("无效的输入");
        }
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.setCharAt(i, '0');
        }
        while (!increment(sb, n)) {
            printNumber(sb, n);
        }
    }

    /**
     * 使用字符串的方式来实现
     * 算法同数组
     * @param sb
     * @param n
     * @return
     */
    private static boolean increment(StringBuilder sb, int n) {
        //写到这里感觉没啥好写的了，就和数组一样的。不同的就只有赋值的方法调用方式而已
        return false;
    }

    /**
     * 使用字符串的方式来实现
     * 算法同数组
     * @param sb
     * @param n
     */
    private static void printNumber(StringBuilder sb, int n) {
        boolean isBeginning = false;
        boolean isWrap = false;
        for (int i = 0; i < n; i++) {
            if (!isBeginning && sb.charAt(i) != 0)
                isBeginning = true;
            if (i == n - 1)
                isWrap = true;

            if (isBeginning) {
                if (!isWrap)
                    System.out.print(sb.charAt(i));
                else
                    System.out.println(sb.charAt(i));
            }
        }
    }

    /**
     * 通过递归的方式来实现
     * @param n
     */
    private static void printOneToMaxOfNDigits(int n) {
        if (n <= 0)
            throw new IllegalStateException("无效的输入");

        int[] number = new int[n];
        //控制第一位的数字情况
        for (int i = 0; i < 10; i++) {
            number[0] = i;
            printOneToMaxOfNDigitsRecursively(number, n, 0);
        }
    }

    /**
     * 递归的控制第n-1位到第二位的数字
     * @param number
     * @param length
     * @param index
     */
    private static void printOneToMaxOfNDigitsRecursively(int[] number, int length, int index) {
        if (index == length - 1) {
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[index + 1] = i;
            printOneToMaxOfNDigitsRecursively(number, length, index + 1);
        }
    }
}
