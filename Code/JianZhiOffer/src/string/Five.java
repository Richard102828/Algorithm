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
 *
 * 相关题目：合并数组
 *      有两个有序的数组A1和A2，A1末尾有足够空间容纳A2。
 *      实现一个函数将A2的所有数字插入到A1中，并且所有数字是有序的。
 *
 * 较好的解法：
 *      和上面一题是类似的，通过指针进行
 */
public class Five {
    public static void main(String[] args) {
        //测试替换空格算法
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

        //测试合并数组算法
        int[] target = {1, 2, 3, 4, 5, 6, 7};
        int[] source = new int[14];
        source[0] = 1;
        source[1] = 2;
        source[2] = 3;
        source[3] = 4;
        source[4] = 6;
        source[5] = 7;
        source[6] = 9;
        for (int i : mergeArray(target, source, target.length, 7)) {
            System.out.println(i);
        }
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

    /**
     *  有两个有序的数组A1和A2，A1末尾有足够空间容纳A2。
     *  实现一个函数将A2的所有数字插入到A1中，并且所有数字是有序的。
     *
     *  有点问题···
     * @param target 要进行合并的数组
     * @param source 原来的数组，大小足够容纳target数组
     * @param targetUsedLength
     * @param sourceUsedLength
     * @return
     */
    private static int[] mergeArray(int[] target, int[] source, int targetUsedLength, int sourceUsedLength) {
        //鲁棒性
        if(target == null || source == null || targetUsedLength == 0 || sourceUsedLength == 0) {
            return null;
        }

        //算法
        int sourcePoint = sourceUsedLength - 1;
        int sourceLastPoint = source.length - 1;
        int targetPoint = targetUsedLength - 1;
        while (sourcePoint >= 0 && sourceLastPoint - 1 != sourcePoint) {
            if (target[targetPoint] > source[sourcePoint]) {
                source[sourceLastPoint--] = target[targetPoint--];
            } else {
                source[sourceLastPoint--] = source[sourcePoint--];
            }
        }
        return source;
    }
}
