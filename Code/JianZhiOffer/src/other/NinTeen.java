package other;

求1+2+3+...+n,要求不能使用乘除法、for、while、if、else、switch、case等关键词以及三元运算符等。
public class NinTeen {
    public static void main(String[] args) {
        System.out.println(match(new char[] {'a', 'a', 'a'},
                new char[] {'a', 'b', '*', 'a', 'c', '*', 'a'}, 0, 0));
    }

    /**
     * 这里使用递归的方式，并用数组来表示字符串
     * @param str
     * @param pattern
     * @return
     */
    private static boolean match(char[] str, char[] pattern, int s, int p) {
        //鲁棒性
        if (str == null || pattern == null || str.length == 0 || pattern.length == 0) {
            throw new IllegalStateException("无效的输入");
        }

        //算法
        if (s < str.length && p == pattern.length) {
            return false;
        }
        if (s == str.length && p == pattern.length) {
            return true;
        }
        if (pattern[p] == str[s] && pattern[p + 1] != '*' && s < str.length -1 && p < pattern.length - 1) {
            return match(str, pattern, s + 1, p + 1);
        } else if (pattern[p] == '.' && pattern[p + 1] != '*' && s < str.length -1 && p < pattern.length - 1) {
            return match(str, pattern, s + 1, p + 1);
        } else if (pattern[p + 1] == '*' && p < pattern.length - 1){
            //分为两种情况：'ch(代表字符)*' 与字符串中字符的相等，或者不等（就当做出现0次来处理）
            if (pattern[p] == str[s] && s < str.length - 1) {
                //相等又分为三种情况，字符串后移一位+模板后移两位；或者字符串后移一位+模板不移动；或者字符串不移动+模板后移两位
                return match(str, pattern, s + 1, p + 2)
                        || match(str, pattern, s, p + 2)
                        || match(str, pattern, s + 1, p);
            } else {
                return match(str, pattern, s, p + 2);
            }
        } else {
            //不匹配
            return false;
        }
    }
}
