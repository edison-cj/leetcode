package second.string;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/6/1 10:16
 */
public class StringTest {


}

class Solution {

    /*
     * @Description  344. 反转字符串
     * @author   Edison
     * @date    2023/6/1 10:16
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
     * @date    2023/6/1 10:23
     * @Param   [s, k]
     * @return  java.lang.String
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += 2 * k) {
            int start = i;
            int end = Math.min(chars.length - 1, start + k - 1);
            while (start < end) {
                char ch = chars[start];
                chars[start++] = chars[end];
                chars[end--] = ch;
            }
        }
        return new String(chars);
    }

    /*
     * @Description  151. 反转字符串中的单词
     * @author   Edison
     * @date    2023/6/1 10:38
     * @Param   [s]
     * @return  java.lang.String
     */
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        reverse(chars, start, chars.length - 1);
        int k = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                continue;
            }
            start = i;
            while (i < chars.length && chars[i] != ' ') i++;
            reverse(chars, start, i - 1);
            for (int j = start; j < i; j++) {
                chars[k++] = chars[j];
                if (j == i - 1) {
                    if (k < chars.length) chars[k++] = ' ';
                }
            }
        }
        return new String(chars, 0, (k == chars.length && chars[k - 1] != ' ') ? k : k - 1);
    }
    void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start++] = chars[end];
            chars[end--] = temp;
        }
    }



}


