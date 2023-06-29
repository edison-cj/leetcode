package wcj.hashtable;

import org.testng.annotations.Test;

import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/2/22 15:44
 */
public class HashTableTest {


    @Test
    public void TestIsAnagram() {

    }

    @Test
    public void TestFindAnagrams() {
        String s = "cbaebabacd", p = "abc";
        System.out.println(Solution.findAnagrams(s, p));
    }

    @Test
    public void TestIntersection() {
        int[] num1 = {1,2,2,1};
        int[] num2 = {2,2};
        for (int num : Solution.intersection(num1,num2)) {
            System.out.println(num);
        }
    }

    @Test
    public  void TestIntersect() {
        int[] num1 = {1,2,2,1};
        int[] num2 = {2,2};
        for (int num : Solution.intersect(num1, num2)) {
            System.out.println(num);
        }
    }

    @Test
    public void TestIsHappy() {
        int n = 2;
        System.out.println(Solution.isHappy(n));
    }

    @Test
    public void TestThreeSum() {
        int[] nums = {-1,0,1,-1};
        System.out.println(Solution.threeSum(nums));
    }

    @Test
    public void TestFourSum() {
        int[] nums = {-2,-1,-1,1,1,2,2};
        int target = 0;
        System.out.println(Solution.fourSum(nums,target));
    }
}

class Solution {

    /*
     * @Description  242. 有效的字母异位词
     * @author   Edison
     * @date    2023/2/22 15:45
     * @Param   [s, t]
     * @return  boolean
     */
    public static boolean isAnagram(String s, String t) {
        int[] record = new int[26];
        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            record[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < record.length; i++) {
            if (record[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  383. 赎金信
     * @author   Edison
     * @date    2023/2/22 15:57
     * @Param   [ransomNote, magazine]
     * @return  boolean
     */
    public boolean canConstruct(String ransomNote, String magazine) {

        int[] record = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) {
            record[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < magazine.length(); i++) {
            if (record[magazine.charAt(i) - 'a'] != 0) {
                record[magazine.charAt(i) - 'a']--;
            }
        }
        for (int i = 0; i < record.length; i ++) {
            if (record[i] != 0) {
                return false;
            }
        }
        return true;
    }


    /*
     * @Description  49. 字母异位词分组
     * @author   Edison
     * @date    2023/2/22 16:08
     * @Param   [strs]
     * @return  java.util.List<java.util.List<java.lang.String>>
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arrys = str.toCharArray();
            Arrays.sort(arrys);
            String key = new String(arrys);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key,list);
        }
        return new ArrayList<List<String>>(map.values());
    }


    /*
     * @Description  438. 找到字符串中所有字母异位词
     * @author   Edison
     * @date    2023/2/22 16:32
     * @Param   [s, p]
     * @return  java.util.List<java.lang.Integer>
     */
    public static List<Integer> findAnagrams(String s, String p) {
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < s.length() - p.length(); i++) {
//            String arrys = s.substring(i, i + p.length());
//            if (isAnagram(arrys, p)) {
//                list.add(i);
//            }
//        }
//        return list;

        int[] cnt = new int[128];
        for (char c : p.toCharArray()) cnt[c]++;
        int lo = 0, hi = 0;
        List<Integer> res = new ArrayList<>();
        while (hi < s.length()) {
            if (cnt[s.charAt(hi)] > 0) {
                cnt[s.charAt(hi++)]--;
                if (hi - lo == p.length()) res.add(lo);
            } else {
                cnt[s.charAt(lo++)]++;
            }
        }
        return res;
    }


    /*
     * @Description  349. 两个数组的交集
     * @author   Edison
     * @date    2023/2/23 13:31
     * @Param   [nums1, nums2]
     * @return  int[]
     */
    public static int[] intersection(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums1) {
            hashSet.add(num);
        }
        for (int num : nums2) {
            if (hashSet.contains(num)) {
                set.add(num);
            }
        }

        int[] arr = new int[set.size()];
        int j = 0;
        for (int i : set) {
            arr[j++] = i;
        }

        return arr;
    }


    /*
     * @Description  350. 两个数组的交集 II
     * @author   Edison
     * @date    2023/2/23 14:26
     * @Param   [nums1, nums2]
     * @return  int[]
     */
    public static int[] intersect(int[] nums1, int[] nums2) {

        int[] record = new int[1005];
        List<Integer> list = new ArrayList<>();

        for (int num : nums1) {
            record[num]++;
        }
        for (int num : nums2) {
            if (record[num] != 0) {
                record[num]--;
                list.add(num);
            }
        }

        int[] arr = new int[list.size()];
        int j = 0;
        for (int num : list) {
            arr[j++] = num;
        }
        return arr;
    }


    /*
     * @Description  202. 快乐数
     * @author   Edison
     * @date    2023/2/23 15:12
     * @Param   [n]
     * @return  boolean
     */
    public static boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (true) {
            n = getSum(n);
            if (n == 1) {
                return true;
            }
            else if (set.contains(n)) {
                return false;
            }
            set.add(n);
        }
    }

    public static int getSum(int n) {
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
     * @date    2023/2/23 15:12
     * @Param   [nums, target]
     * @return  int[]
     */
    public int[] twoSum(int[] nums, int target) {

        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                res[0] = i;
                res[1] = map.get(temp);
                return res;
            }
            map.put(nums[i], i);
        }
        return null;
    }


    /*
     * @Description  454. 四数相加 II
     * @author   Edison
     * @date    2023/2/23 15:32
     * @Param   [nums1, nums2, nums3, nums4]
     * @return  int
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                int temp = a + b;
                if (map.containsKey(temp)) {
                    map.put(temp,map.get(temp) + 1);
                } else {
                    map.put(temp, 1);
                }
            }
        }
        for (int a : nums3) {
            for (int b : nums4) {
                int target = -(a + b);
                if (map.containsKey(target)) {
                    count += map.get(target);
                }
            }
        }

        return count;
    }


    /*
     * @Description  15. 三数之和
     * @author   Edison
     * @date    2023/2/23 15:52
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public static List<List<Integer>> threeSum(int[] nums) {

        ArrayList<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        int left;
        int right;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return lists;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    lists.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    right--;
                    left++;
                }

            }
        }
        return lists;
    }

    /*
     * @Description  18. 四数之和
     * @author   Edison
     * @date    2023/2/23 16:39
     * @Param   [nums, target]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {

        ArrayList<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (nums[i] > target && nums[i] >= 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (nums[i] + nums[j] > target && nums[i] + nums[j] > 0) {
                    break;
                }
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        lists.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        right--;
                        left++;
                    }
                }
            }
        }
        return lists;
    }

}

