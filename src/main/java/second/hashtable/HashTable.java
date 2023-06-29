package second.hashtable;

import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/5/31 10:09
 */
public class HashTable {
}

class Solution {

    /*
     * @Description  242. 有效的字母异位词
     * @author   Edison
     * @date    2023/5/31 10:14
     * @Param   [s, t]
     * @return  boolean
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] res = new int[26];
        for (char ch : s.toCharArray()) {
            res[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
            res[ch - 'a']--;
            if (res[ch - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  383.赎金信
     * @author   Edison
     * @date    2023/5/31 10:21
     * @Param   [ransomNote, magazine]
     * @return  boolean
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] num = new int[26];
        for (char ch : magazine.toCharArray()) {
            num[ch - 'a']++;
        }
        for (char ch : ransomNote.toCharArray()) {
            num[ch - 'a']--;
            if (num[ch - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  49.字母异位词分组
     * @author   Edison
     * @date    2023/5/31 10:25
     * @Param   [strs]
     * @return  List<List<String>>
     */
    public List<List<String>> groupAnagrams(String[] strs) {
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

    /*
     * @Description  438.找到字符串中所有字母异位词
     * @author   Edison
     * @date    2023/5/31 10:32
     * @Param   [s, p]
     * @return  java.util.List<java.lang.Integer>
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
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

    /*
     * @Description  349. 两个数组的交集
     * @author   Edison
     * @date    2023/5/31 10:46
     * @Param   [nums1, nums2]
     * @return  int[]
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        for (int num : nums2) {
            if (set.contains(num)) {
                resSet.add(num);
            }
        }
        return resSet.stream().mapToInt(x -> x).toArray();
    }

    /*
     * @Description  350. 两个数组的交集 II
     * @author   Edison
     * @date    2023/5/31 10:56
     * @Param   [nums1, nums2]
     * @return  int[]
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                list.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        return list.stream().mapToInt(x -> x).toArray();
    }

    /*
     * @Description  202. 快乐数
     * @author   Edison
     * @date    2023/5/31 11:05
     * @Param   [n]
     * @return  boolean
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNum(n);
        }
        return n == 1;
    }
    int getNum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }

    /*
     * @Description  1. 两数之和
     * @author   Edison
     * @date    2023/5/31 11:11
     * @Param   [nums, target]
     * @return  int[]
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                res[1] = i;
                res[0] = map.get(temp);
                break;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    /*
     * @Description  454. 四数相加 II
     * @author   Edison
     * @date    2023/5/31 11:17
     * @Param   [nums1, nums2, nums3, nums4]
     * @return  int
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                map.put(num1 + num2, map.getOrDefault(num1 + num2, 0) + 1);
            }
        }
        for (int num3 : nums3) {
            for (int num4 : nums4) {
                int temp = -(num3 + num4);
                count += map.getOrDefault(temp, 0);
            }
        }
        return count;
    }

    /*
     * @Description  15. 三数之和
     * @author   Edison
     * @date    2023/5/31 11:26
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                return list;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
        }
        return list;
    }

    /*
     * @Description  18. 四数之和
     * @author   Edison
     * @date    2023/5/31 11:35
     * @Param   [nums, target]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (nums[i] > target && nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int k = i + 1; k < nums.length - 2; k++) {
                if (k > i + 1 && nums[k] == nums[k - 1]) {
                    continue;
                }
                int left = k + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[k] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        list.add(Arrays.asList(nums[i], nums[k], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    }
                }
            }
        }
        return list;
    }

}
