package leetcode.hashtable;

import org.testng.annotations.Test;

import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/4/6 10:41
 */
public class HashTableTest {

    Solution solution = new Solution();

    @Test
    public void isAnagramTest() {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(solution.isAnagram(s, t));
    }

    @Test
    public void baseNeg2Test() {
        System.out.println(solution.baseNeg2(6));
        boolean[] used = new boolean[2];
        for (boolean use : used) {
            System.out.print(use + " ");
        }
    }

    @Test
    public void findAnagramsTest() {
        String s = "cbaebabacd";
        String t = "abc";
        List<Integer> list = solution.findAnagrams(s, t);
        for (int l : list) {
            System.out.print(l + " ");
        }
    }

    @Test
    public void fourSumTest() {
        int[] nums = {2, 2, 2, 2, 2};
        int target = 8;
        List<List<Integer>> list = solution.fourSum(nums, target);
        for (List<Integer> l1 : list) {
            for (Integer l : l1) {
                System.out.print(l);
            }
            System.out.println();
        }
    }

}

class Solution {

    /*
     * @Description  242. 有效的字母异位词
     * @author   Edison
     * @date    2023/4/6 10:51
     * @Param   [s, t]
     * @return  boolean
     */
    public boolean isAnagram(String s, String t) {
        int[] num = new int[26];
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            num[t.charAt(i) - 'a']--;
            if (num[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  383. 赎金信
     * @author   Edison
     * @date    2023/4/6 11:03
     * @Param   [ransomNote, magazine]
     * @return  boolean
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] records = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            records[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (records[ransomNote.charAt(i) - 'a'] == 0) {
                return false;
            }
            records[ransomNote.charAt(i) - 'a']--;
        }
        return true;
    }

    /*
     * @Description  1017. 负二进制转换
     * @author   Edison
     * @date    2023/4/6 11:12
     * @Param   [n]
     * @return  java.lang.String
     */
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuffer str = new StringBuffer();
        while (n != 0) {
            int c = Math.abs(n % (-2));
            str.insert(0, c);
            n = (n - c) / -2;
        }
        return new String(str);
    }

    /*
     * @Description  438. 找到字符串中所有字母异位词
     * @author   Edison
     * @date    2023/4/6 14:57
     * @Param   [s, p]
     * @return  List<Integer>
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i <= s.length() - p.length(); i++) {
//            if (isFindAnagrams(s, p, i, i + p.length())) {
//                list.add(i);
//            }
//        }
        int[] record = new int[26];
        for (char ch : p.toCharArray()) {
            record[ch - 'a']++;
        }
        int low = 0;
        int high = 0;
        while (high < s.length()) {
            if (record[s.charAt(high) - 'a'] > 0) {
                record[s.charAt(high++) - 'a']--;
                if (high - low == p.length()) {
                    list.add(low);
                }
            } else {
                record[s.charAt(low++) - 'a']++;
            }
        }

        return list;
    }
    boolean isFindAnagrams(String s, String p, int start, int end) {
        int[] record = new int[26];
        for (int i = 0; i < p.length(); i++) {
            record[p.charAt(i) - 'a']++;
        }
        for (int i = start; i < end; i++) {
            record[s.charAt(i) - 'a']--;
        }
        for (int num : record) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  49. 字母异位词分组
     * @author   Edison
     * @date    2023/4/6 15:42
     * @Param   [strs]
     * @return  java.util.List<java.util.List<java.lang.String>>
     */
    public List<List<String>> groupAnagrams(String[] strs) {
//        List<List<String>> res = new ArrayList<>();
//        boolean[] used = new boolean[strs.length];
//        for (int i = 0; i < strs.length; i++) {
//            if (!used[i]) {
//                List<String> list = new ArrayList<>();
//                list.add(strs[i]);
//                for (int j = i + 1; j < strs.length; j++) {
//                    if (!used[j] && isGroup(strs[i], strs[j])) {
//                        used[j] = true;
//                        list.add(strs[j]);
//                    }
//                }
//                res.add(list);
//            }
//        }
//        return res;

        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
    boolean isGroup(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] record = new int[26];
        for (char ch : s.toCharArray()) {
            record[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
            if (record[ch - 'a'] == 0) {
                return false;
            }
            record[ch - 'a']--;
        }
        return true;
    }

    /*
     * @Description  349. 两个数组的交集
     * @author   Edison
     * @date    2023/4/6 16:19
     * @Param   [nums1, nums2]
     * @return  int[]
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        for (int num : nums2) {
            if (set.contains(num)) {
                res.add(num);
            }
        }
        int[] arr = new int[res.size()];
        int j = 0;
        for (int num : res) {
            arr[j++] = num;
        }
        return arr;
    }

    /*
     * @Description  350. 两个数组的交集 II
     * @author   Edison
     * @date    2023/4/6 16:29
     * @Param   [nums1, nums2]
     * @return  int[]
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) != 0) {
                list.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        int[] res = new int[list.size()];
        int j = 0;
        for (int num : list) {
            res[j++] = num;
        }
        return res;
    }

    /*
     * @Description  202. 快乐数
     * @author   Edison
     * @date    2023/4/6 16:45
     * @Param   [n]
     * @return  boolean
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (true) {
            n = getNum(n);
            if (n == 1) {
                return true;
            } else if (set.contains(n)) {
                return false;
            }
            set.add(n);
        }
    }
    int getNum(int n) {
        int num = 0;
        while (n != 0) {
            num += (n % 10) * (n % 10);
            n /= 10;
        }
        return num;
    }

    /*
     * @Description  1. 两数之和
     * @author   Edison
     * @date    2023/4/6 16:52
     * @Param   [nums, target]
     * @return  int[]
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (map.containsKey(num)) {
                res[0] = i;
                res[1] = map.get(num);
                break;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    /*
     * @Description  454. 四数相加 II
     * @author   Edison
     * @date    2023/4/6 17:07
     * @Param   [nums1, nums2, nums3, nums4]
     * @return  int
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int temp;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                temp = num1 + num2;
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        for (int num3 : nums3) {
            for (int num4 : nums4) {
                temp = -(num3 + num4);
                if (map.containsKey(temp)) {
                    count += map.get(temp);
                }
            }
        }
        return count;
    }

    /*
     * @Description  15. 三数之和
     * @author   Edison
     * @date    2023/4/6 17:21
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 3; i++) {
            if (nums[i] > 0) {
                return res;
            }
            int left = i + 1;
            int right = nums.length - 1;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                    right--;
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    /*
     * @Description  18. 四数之和
     * @author   Edison
     * @date    2023/4/6 19:01
     * @Param   [nums, target]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 4; i++) {
            if (nums[i] > target && nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int k = i + 1; k <= nums.length - 3; k++) {
                if (k > i + 1 && nums[k] == nums[k - 1]) {
                    continue;
                }
                int left = k + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[i] + nums[k] + nums[left] + nums[right] > target) {
                        right--;
                    } else if (nums[i] + nums[k] + nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[k]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        res.add(list);
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return res;
    }


}
