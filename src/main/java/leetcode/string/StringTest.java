package leetcode.string;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/4/7 10:07
 */
public class StringTest {

    Solution solution = new Solution();



}

class Solution {

    /*
     * @Description  344. 反转字符串
     * @author   Edison
     * @date    2023/4/7 10:08
     * @Param   [s]
     * @return  void
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }

    /*
     * @Description  541. 反转字符串 II
     * @author   Edison
     * @date    2023/4/7 10:12
     * @Param   [s, k]
     * @return  java.lang.String
     */
    public String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i += 2 * k) {
            int left = i;
            int right = Math.min(i + k - 1, ch.length - 1);
            while (left < right) {
                char temp = ch[left];
                ch[left++] = ch[right];
                ch[right--] = temp;
            }
        }
        return new String(ch);
    }

    /*
     * @Description  剑指 Offer 05. 替换空格
     * @author   Edison
     * @date    2023/4/7 10:22
     * @Param   [s]
     * @return  java.lang.String
     */
    public String replaceSpace(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return new String(sb);
    }

    /*
     * @Description  151. 反转字符串中的单词
     * @author   Edison
     * @date    2023/4/7 10:27
     * @Param   [s]
     * @return  java.lang.String
     */
    public String reverseWords(String s) {
        char[] ch = s.toCharArray();
        myReverse(ch, 0, ch.length - 1);
        int k = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ' ') {
                continue;
            }
            int tempCur = i;
            while (i < ch.length && ch[i] != ' ') {
                i++;
            }
            myReverse(ch, tempCur, i - 1);
            for (int j = tempCur; j < i; j++) {
                ch[k++] = ch[j];
                if (j == i - 1) {
                    if (k < ch.length) {
                        ch[k++] = ' ';
                    }
                }
            }
        }
        return new String(ch, 0, (k == ch.length && ch[k - 1] != ' ') ? k : k - 1);
    }
    void myReverse(char[] ch, int start, int end) {
        while (start < end) {
            char temp = ch[start];
            ch[start++] = ch[end];
            ch[end--] = temp;
        }
    }

    /*
     * @Description  剑指 Offer 58 - II. 左旋转字符串
     * @author   Edison
     * @date    2023/4/7 10:47
     * @Param   [s, n]
     * @return  java.lang.String
     */
    public String reverseLeftWords(String s, int n) {
        char[] ch = s.toCharArray();
        myReverse(ch, 0, n - 1);
        myReverse(ch, n, ch.length - 1);
        myReverse(ch, 0, ch.length - 1);
        return new String(ch);
    }

    /*
     * @Description  28. 找出字符串中第一个匹配项的下标
     * @author   Edison
     * @date    2023/4/7 11:16
     * @Param   [haystack, needle]
     * @return  int
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int[] next = new int[needle.length()];
        getNext(next, needle);
        int j = -1;
        for (int i = 0; i < haystack.length(); i++) {
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            if (j == needle.length() - 1) {
                return i - j;
            }
        }
        return -1;
    }
    void getNext(int[] next, String s) {
        int j = -1;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) {
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
                j = next[j];
            }
            if (s.charAt(i) == s.charAt(j + 1)) {
                j++;
            }
            next[i] = j;
        }
    }

    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        int[] next = new int[len];
        myGetNext(next, s);
        if ((next[len - 1] > 0) && (len % (len - next[len - 1]) == 0)) {
            return true;
        } else {
            return false;
        }
    }

    void myGetNext(int[] next, String s) {
        int j = 0;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
    }
}

