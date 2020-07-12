package string;

/**
 * 《剑指Offer》第二版，面试题 5: 替换空格
 *
 *      请实现一个函数，将一个字符串中的空格替换成 “%20”
 *      例如，当字符串为 “We Are Happy”.则经过替换之后的字符串为 “We%20Are%20Happy”
 *      要求：在原字符中修改（内存大小足够）
 *
 * 较好的解法：
 *      使用两个指针，分别指向原来字符串的末尾、新字符串的末尾。
 *      然后不断的向前移动，并复制到后面去，遇到空格则向后面写入%20
 *      直到两个指针指向同一个位置时，替换成功
 */
public class Five {
    public static void main(String[] args) {
        char[] str = new char[30];
        str[0] = 'W';
        str[1] = 'e';
        str[2] = ' ';
        str[3] = 'A';
        str[4] = 'r';
        str[5] = 'e';
        str[6] = ' ';
        str[7] = 'H';
        str[8] = 'a';
        str[9] = 'p';
        str[10] = 'p';
        str[11] = 'y';
        System.out.println(findBlank(str, 11));
    }

    /**
     * 使用两个指针，分别指向原来字符串的末尾、新字符串的末尾。
     * 然后不断的向前移动，并复制到后面去，遇到空格则向后面写入%20
     * 直到两个指针指向同一个位置时，替换成功
     *
     * 自己给定条件，原字符串长度给出来了，不然还挺难求的~
     * 这里用字符数组代表字符串，不然就得用StringBuilder咯~
     * @param str
     * @param usedLength
     * @return
     */
    private static char[] findBlank(char[] str, int usedLength) {
        //鲁棒性
        if (usedLength <= 0 || str == null) {
            return null;
        }

        //算法
        int blankNum = 0;
        for (int i = 0; i < usedLength; i++) {
            if (str[i] == ' ') {
                blankNum++;
            }
        }
        int targetLength = usedLength + blankNum * 2;
        int oldPoint = usedLength;
        int newPoint = targetLength;
        while (oldPoint >= 0 && oldPoint < newPoint) {
            if (str[oldPoint] != ' ') {
                str[newPoint--] = str[oldPoint--];
            } else {
                str[newPoint--] = '0';
                str[newPoint--] = '2';
                str[newPoint--] = '%';
                oldPoint--;
            }
        }
        return str;
    }
}
