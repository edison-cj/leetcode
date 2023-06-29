package wcj.string;

import org.testng.annotations.Test;

import java.util.Map;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/2/24 13:22
 */
public class StringTest {

    @Test
    public void TestReverseString() {
        char[] s = {'h','e','l','l','o'};
        Solution.reverseString(s);
        System.out.println(s);
    }

    @Test
    public void TestReverseStr() {
        String s = "hello";
        System.out.println(Solution.reverseStr(s,2));
    }

    @Test
    public void TestReplaceSpace() {
        String s = "We are happy.";
        System.out.println(Solution.replaceSpace(s));
    }

    @Test
    public void TestReverseWords() {
        String s = "the sky is blue";
        System.out.println(Solution.reverseWords(s));
    }

    @Test
    public void TestReverseLeftWords() {
        String s = "lrloseumgh";
        System.out.println(Solution.reverseLeftWords(s,6));
    }

    @Test
    public void TestRepeatedSubstringPattern() {
        String s = "ababab";
        System.out.println(Solution.repeatedSubstringPattern(s));
    }
}

class Solution {

    /*
     * @Description  344. 反转字符串
     * @author   Edison
     * @date    2023/2/24 13:22
     * @Param   [s]
     * @return  void
     */
    public static void reverseString(char[] s) {

        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }


    /*
     * @Description  541. 反转字符串 II
     * @author   Edison
     * @date    2023/2/24 13:31
     * @Param   [s, k]
     * @return  java.lang.String
     */
    public static String reverseStr(String s, int k) {

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += 2 * k) {
            int left = i;
            int right;
//            if (i + k > chars.length) {
//                right = chars.length - 1;
//            } else {
//                right = i + k - 1;
//            }
            right = Math.min(i + k - 1,chars.length - 1);
            while (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }

        return new String(chars);
    }


    /*
     * @Description  剑指 Offer 05. 替换空格
     * @author   Edison
     * @date    2023/2/24 14:06
     * @Param   [s]
     * @return  java.lang.String
     */
    public static String replaceSpace(String s) {
        StringBuffer str = new StringBuffer();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                str.append("%20");
            } else {
                str.append(s.charAt(i));
            }
        }

        return new String(str);
    }


    /*
     * @Description  151. 反转字符串中的单词
     * @author   Edison
     * @date    2023/2/24 14:24
     * @Param   [s]
     * @return  java.lang.String
     */
    public static String reverseWords(String s) {

        char[] arr = s.toCharArray();
        int k = 0;
        MyReverse(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                continue;
            }
            int tempCur = i;
            while (i < arr.length && arr[i] != ' ') i++;
            for (int j = tempCur; j < i; j++) {
                if (j == tempCur) MyReverse(arr, tempCur , i - 1);
                arr[k++] = arr[j];
                if (j == i - 1) {
                    if (k < arr.length) {
                        arr[k++] = ' ';
                    }
                }
            }
        }

        if (k == 0) {
            return null;
        } else {
            return new String(arr, 0, (k == arr.length) && (arr[k - 1] != ' ') ? k : k - 1);
        }
    }
    public static void MyReverse(char[] ch, int i, int j) {
        char temp;
        while (i < j) {
            temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
            i++;
            j--;
        }
    }

    /*
     * @Description  剑指 Offer 58 - II. 左旋转字符串
     * @author   Edison
     * @date    2023/2/24 15:28
     * @Param   [s, n]
     * @return  java.lang.String
     */
    public static String reverseLeftWords(String s, int n) {
        if (n > s.length()) {
            return s;
        }
        char[] chars = s.toCharArray();
//        for (int i = 0; i < n; i++) {
//            char temp = chars[0];
//            for (int j = 0; j < chars.length; j++) {
//                if (j < chars.length - 1) chars[j] = chars[j + 1];
//                else chars[j] = temp;
//            }
//        }

        MyReverse(chars, 0, n - 1);
        MyReverse(chars, n, chars.length - 1);
        MyReverse(chars, 0, chars.length - 1);
        return new String(chars);
    }


    /*
     * @Description  28. 找出字符串中第一个匹配项的下标
     * @author   Edison
     * @date    2023/2/24 15:51
     * @Param   [haystack, needle]
     * @return  int
     */
    public static int strStr(String haystack, String needle) {

        if (needle.length() == 0) {
            return 0;
        }

        int[] next = new int[needle.length()];
        getNext(next,needle);

        int j = -1;
        for (int i = 0; i < haystack.length(); i++) {
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            if (j == needle.length() - 1) {
                return i - needle.length() + 1;
            }
        }
        return -1;
    }

    public static void getNext(int[] next, String s) {
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


    /*
     * @Description  459. 重复的子字符串
     * @author   Edison
     * @date    2023/2/24 17:15
     * @Param   [s]
     * @return  boolean
     */
    public static boolean repeatedSubstringPattern(String s) {

        /*
         * @Description  暴力解法
         * @author   Edison
         * @date    2023/3/5 14:50
         * @Param   [s]
         * @return  boolean
         */
        /*
        for (int i = 0; i < s.length() / 2; i++) {
            int tt = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (j == (tt + 1) * (i + 1)) {
                    tt++;
                }
                if (s.charAt(j - tt * (i + 1)) != s.charAt(j)) {
                    break;
                }
                if ((j == s.length() - 1) && (j == ((tt + 1) * (i + 1) - 1))) {
                    return true;
                }
            }
        }

        return false;
        */

        /*
         * @Description  KMP解法
         * @author   Edison
         * @date    2023/3/5 14:50
         * @Param   [s]
         * @return  boolean
         */
        if (s.length() == 0) {
            return false;
        }
        int[] next = new int[s.length()];
        int len = s.length();
        myGetNext(next, s);

        if ((next[len - 1] > 0) && (len % (len - next[len - 1]) == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static void myGetNext(int[] next, String s) {
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
