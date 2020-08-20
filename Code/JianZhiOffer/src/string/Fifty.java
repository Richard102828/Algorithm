package string;

/**
 * 《剑指Offer》第二版，面试题 50: 第一个只出现一次的字符
 *
 *      找出字符串中找出第一个只出现一次的字符，比如输入“abacceff",则输出'b'
 *
 * 较好的解法：(首先得考虑大数问题)
 *      1. 可以使用哈希表，键为字符，值为出现的次数，这样循环一次数组，得到所有字符的次数后
 *      再去循环一次数组，并向哈希表中找对应的次数，找到为1的则可以输出了（这里就是第一个为1的）
 *      2. 如果没有中文和特殊的字符的话，可以根据ASCII码来实现一个数组，数组中存放对应字符的次数
 *          a. 数组长度为256
 * 相关题目：
 *      扩展一：
 *          定义一个函数，输入两个字符串，从第一个字符串中删除在第二个字符串中出现过的所有字符。
 *          比如第一个字符串"google"，第二个字符串为"aeiou"，删除后得到"ggl".
 *      解法：
 *          使用一个哈希表来保存第二个字符串，然后循环一次第一个字符串就行了，因为哈希表中查找时间复杂度为O(1)，
 *          所以总的来说只需要O(n)的时间复杂度即可，不过还需要O(n)的额外的空间复杂度
 *      扩展二：
 *          定义一个函数，删除字符串中所有重复出现的字符。
 *      解法：
 *          类似的，使用哈希表来实现
 *      扩展三：
 *          变位词，如果两个单词含有相同的字母且每个字母出现的次数还一样，那么这两个单词互为变位词。
 *          定义一个函数判断两个字符串是不是互为变位词。
 *      解法：
 *          类似的，使用哈希表实现，一个哈希表就够了，第一个字符串字符出现次数存到数组后，扫描第二个字符串时，
 *          减去上一个字符串的出现次数。全部为0了则是变位词
 *  题目二：
 *      字符流中第一个只出现一次的字符。
 *      这次字符串是动态变化的了，比如现在只从字符流中读取了两个字符为"go"
 *      那么字符流中第一个只出现一次的字符是'g'，等到从字符流中读取了前6个字符"google"时，第一个只出现一次的字符变成了'l'.
 *  解法：
 *      与面试题50是类似的，使用一个哈希表来实现，不过对存入的数据要进行区分
 *          最开始为-1
 *          读取到了一个就存对应的索引
 *          如果重复了就更新为-2
 *      最后只需要循环一次哈希表，从中找到索引最小的那个即可
 */
public class Fifty {
    private static boolean isInvalidInput = false;

    public static void main(String[] args) {
        System.out.println(getFirstOnceChar("dfjk233k3"));
        System.out.println(deleteFromAnother("We Are Students", "west"));
        System.out.println(deleteAllRepeatChar("keyi dangran d "));
        System.out.println(isAnagram("bianweici", "ciweibian"));
        AppearOnceInStream inStream = new AppearOnceInStream();
        inStream.insert('d');
        inStream.insert('a');
        inStream.insert('a');
        inStream.insert('a');
        inStream.insert('c');
        System.out.println(inStream.getFirstChar());
    }

    /**
     * 根据ASCII码，使用数组的方式
     * @param str
     * @return
     */
    private static char getFirstOnceChar(String str) {
        //鲁棒性
        if (str == null || str.length() <= 0) {
            isInvalidInput = true;
            return '\0';
        }
        int[] times = new int[256];
        for (int i = 0; i < str.length(); i++)
            times[str.charAt(i)]++;
        for (int i = 0; i < str.length(); i++) {
            if (times[str.charAt(i)] == 1)
                return str.charAt(i);
        }
        return ' ';
    }

    /**
     * 扩展一，这里使用一个简单的数组来代替哈希表
     * @param str
     * @param other
     * @return
     */
    private static String deleteFromAnother(String str, String other) {
        //鲁棒性
        if (str == null || other == null || str.length() <= 0 || other.length() <= 0) {
            isInvalidInput = true;
            return null;
        }
        int[] hash = new int[256];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < other.length(); i++) {
            hash[other.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (hash[str.charAt(i)] == 0)
                sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 扩展二，使用一个简单的数组来代替哈希表
     * @param str
     * @return
     */
    private static String deleteAllRepeatChar(String str) {
        //鲁棒性
        if (str == null || str.length() <= 0) {
            isInvalidInput = true;
            return null;
        }
        int[] hashTable = new int[256];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            hashTable[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (hashTable[str.charAt(i)] <= 1)
                sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 扩展三，使用一个简单的数字来代替哈希表
     * @param first
     * @param second
     * @return
     */
    private static boolean isAnagram(String first, String second) {
        //鲁棒性
        if (first == null || second == null
                || first.length() <= 0 || second.length() <= 0
                || first.length() != second.length()) {
            isInvalidInput = true;
            return false;
        }
        int[] hashTable = new int[256];
        for (int i = 0; i < first.length(); i++) {
            hashTable[first.charAt(i)]++;
        }
        for (int i = 0; i < second.length(); i++) {
            hashTable[second.charAt(i)]--;
        }
        for (int i = 0; i < first.length(); i++) {
            if (hashTable[first.charAt(i)] != 0)
                return false;
        }
        return true;
    }

    /**
     * 题目二，对于流的处理不再是一个函数就可以了，我们这里使用一个类来实现，对于数据的插入要进行处理
     */
   static class AppearOnceInStream {
        private int index;
        private int[] hashTable = new int[256];

        public AppearOnceInStream() {
            for (int i = 0; i < hashTable.length; i++) {
                hashTable[i] = -1;
            }
        }

        public void insert(char c) {
            if (hashTable[c] == -1)
                hashTable[c] = index;
            else
                hashTable[c] = -2;
            index++;
        }

        public char getFirstChar() {
            if (hashTable == null)
                return ' ';
            int minIndex = Integer.MAX_VALUE;
            char firstChar = ' ';
            for (int i = 0; i < hashTable.length; i++) {
                if (hashTable[i] >= 0 && hashTable[i] < minIndex) {
                    minIndex = hashTable[i];
                    firstChar = (char)i;
                }
            }
            return firstChar;
        }
    }
}
