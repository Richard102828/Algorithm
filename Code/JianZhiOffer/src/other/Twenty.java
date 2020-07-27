package other;

/**
 * 《剑指Offer》第二版，面试题 20: 表示数值的字符串
 *
 *      请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *      例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 *      但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 *
 * 较好的解法：（有点问题）
 *      1. 找到了表示数值的模板：有整数：A[.[B]][e|EC]或者 无整数：.B[e|EC]
 *          a. 例如：123.45e+6
 *          b. 分别处理模板中对应的 A B C . e E
 */
public class Twenty {
    public int index = 0;
    public static void main(String[] args) {
        Twenty twenty = new Twenty();
        System.out.println(isNumber(new char[] {'1', '2'}, twenty));
        System.out.println(twenty.index);
    }

    /**
     * 使用数组来表示字符串
     * @param str
     * @param twenty 由于值传递问题，这里使用一个对象包装了index
     * @return
     */
    private static boolean isNumber(char[] str, Twenty twenty) {
        //鲁棒性
        if (str == null) {
            throw new IllegalStateException("无效的输入");
        }
        //算法
        //整数部分
        boolean isNumber = scanInteger(str, twenty);
        //小数部分
        if (str[twenty.index] == '.') {
            isNumber = scanUnsignedInteger(str, twenty) || isNumber;
        }
        //e|E部分
        if (str[twenty.index] == 'e' || str[twenty.index] == 'E') {
            if (twenty.index < str.length - 1) {
                System.out.println(twenty.index);
                ++twenty.index;
                isNumber = isNumber && scanInteger(str, twenty);
            } else
                isNumber = false;
        }
        return isNumber;
    }

    /**
     * 扫描符号部分 A、C
     * @param str
     * @return
     */
    private static boolean scanInteger(char[] str, Twenty twenty) {
        if (str[twenty.index] == '+' || str[twenty.index] == '-') {
            if (twenty.index < str.length - 1) {
                twenty.index++;
            }
        }
        return scanUnsignedInteger(str, twenty);
    }

    /**
     * 扫描0-9数字部分 B
     * @param str
     * @param twenty
     * @return
     */
    private static boolean scanUnsignedInteger(char[] str, Twenty twenty) {
        int temp = twenty.index;
        while (str[twenty.index] <= '9' && str[twenty.index] >= '0' && twenty.index < str.length - 1) {
            twenty.index++;
        }
        return twenty.index > temp;
    }
}
