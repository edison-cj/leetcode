package leetcode.array;


import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/3/31 10:07
 */
public class ArrayTest {

    Solution solution = new Solution();

    @Test
    public void smallerNumbersThanCurrentTest() {
        int[] nums = {8,1,2,2,3};
        nums = solution.smallerNumbersThanCurrent(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void uniqueOccurrencesTest() {
        int[] nums = {1,2,2,1,1,3};
        System.out.println(solution.uniqueOccurrences(nums));
    }

    @Test
    public void moveZeroesTest() {
        int[] nums = {0,1,0,3,12};
        solution.moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void removeElementTest() {
        int[] nums = {0,1,2,2,3,0,4,2};
        System.out.print(solution.removeElement(nums, 2));
    }

    @Test
    public void rotateTest() {
        int[] nums = {1,2,3,4,5,6,7};
        solution.rotate(nums, 3);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void reverseStrTest() {
        String s = "abcd";
        System.out.println(solution.reverseStr(s, 2));
    }

    @Test
    public void pivotIndexTest() {
        int[] nums = {1, 7, 3, 6, 5, 6};
        System.out.println(solution.pivotIndex(nums));
    }

    @Test
    public void searchRangeTest() {
        int[] nums = {5,7,7,8,8,10};
        int[] arr = new int[2];
        arr = solution.searchRange(nums, 6);
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }

    @Test
    public void sortArrayByParityIITest() {
        int[] nums = {4,2,5,7};
        nums = solution.sortArrayByParityII(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void removeDuplicatesTest() {
        int[] mums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(solution.removeDuplicates(mums));
    }

    @Test
    public void backspaceCompareTest() {
        String s = "ab##";
        String t = "c#d#";
        System.out.println(solution.backspaceCompare(s, t));
    }

    @Test
    public void sortedSquaresTest() {
        int[] nums = {-4,-1,0,3,10};
        nums = solution.sortedSquares(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void minSubArrayLenTest() {

    }

    @Test
    public void totalFruitTest() {
        int[] nums = {3,3,3,1,2,1,1,2,3,3,4};
        System.out.println(solution.totalFruit(nums));
    }

    @Test
    public void generateMatrixTest() {
        int n = 4;
        int[][] res = solution.generateMatrix(n);
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void spiralOrderTest() {
        int[][] nums = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int[] res = solution.spiralOrder(nums);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}

class Solution {

    /*
     * @Description  1365. 有多少小于当前数字的数字
     * @author   Edison
     * @date    2023/3/31 10:07
     * @Param   [nums]
     * @return  int[]
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = Arrays.copyOf(nums, nums.length);
        Arrays.sort(result);
        for (int i = 0; i < result.length; i++) {
            if (!map.containsKey(result[i])) {
                map.put(result[i], i);
            }
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = map.get(nums[i]);
        }
        return result;
    }

    /*
     * @Description  941. 有效的山脉数组
     * @author   Edison
     * @date    2023/3/31 10:21
     * @Param   [arr]
     * @return  boolean
     */
    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) {
            return false;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left + 1 < arr.length && arr[left] < arr[left + 1]) {
            left++;
        }
        while (right > 0 && arr[right - 1] > arr[right]) {
            right--;
        }
        if (left == right && left != 0 && right != arr.length - 1) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * @Description  1207. 独一无二的出现次数
     * @author   Edison
     * @date    2023/3/31 10:35
     * @Param   [arr]
     * @return  boolean
     */
    public boolean uniqueOccurrences(int[] arr) {
        int[] res = new int[2002];
        for (int i = 0; i < arr.length; i++) {
            res[1000 + arr[i]]++;
        }
        boolean[] flag = new boolean[1002];
        for (int i = 0; i < 2002; i++) {
            if (res[i] > 0) {
                if (!flag[res[i]]) {
                    flag[res[i]] = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * @Description  189. 轮转数组
     * @author   Edison
     * @date    2023/3/31 11:07
     * @Param   [nums, k]
     * @return  void
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    void reverse(int[] nums, int begin, int end) {
        while (begin < end) {
            int temp = nums[begin];
            nums[begin++] = nums[end];
            nums[end--] = temp;

        }
    }

    /*
     * @Description  541. 反转字符串 II
     * @author   Edison
     * @date    2023/3/31 11:29
     * @Param   [s, k]
     * @return  java.lang.String
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += 2 * k) {
            int left = i;
            int right = Math.min(chars.length - 1, i + k - 1);
            while (left < right) {
                char temp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = temp;
            }
        }
        return new String(chars);
    }

    /*
     * @Description  151. 反转字符串中的单词
     * @author   Edison
     * @date    2023/3/31 14:39
     * @Param   [s]
     * @return  java.lang.String
     */
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int k = 0;
        myReverse(chars, 0, chars.length - 1);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                continue;
            }
            int tempCur = i;
            while (i < chars.length && chars[i] != ' ') {
                i++;
            }
            myReverse(chars, tempCur, i - 1);
            for (int j = tempCur; j < i; j++) {
                chars[k++] = chars[j];
                if (j == i - 1) {
                    if (k < chars.length) {
                        chars[k++] = ' ';
                    }
                }
            }
        }
        if (k == 0) {
            return null;
        } else {
            return new String(chars, 0, (k == chars.length && chars[k - 1] != ' ' ? k : k - 1));
        }
    }
    void myReverse(char[] chars, int begin, int end) {
        while (begin < end) {
            char ch = chars[begin];
            chars[begin++] = chars[end];
            chars[end--] = ch;
        }
    }

    /*
     * @Description  724. 寻找数组的中心下标
     * @author   Edison
     * @date    2023/3/31 15:04
     * @Param   [nums]
     * @return  int
     */
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int leftSum = 0;
        int rightSum;
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                leftSum += nums[i - 1];
            }
            rightSum = sum - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    /*
     * @Description  34. 在排序数组中查找元素的第一个和最后一个位置
     * @author   Edison
     * @date    2023/3/31 15:22
     * @Param   [nums, target]
     * @return  int[]
     */
    public int[] searchRange(int[] nums, int target) {
        int index = findRange(nums, target);
        if (index == -1) {
            return new int[]{-1, -1};
        }
        int left = index;
        int right = index;
        while (left > 0 && nums[left] == nums[left - 1]) {
            left--;
        }
        while (right < nums.length - 1 && nums[right] == nums[right + 1]) {
            right++;
        }
        return new int[]{left, right};
    }
    int findRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /*
     * @Description  922. 按奇偶排序数组 II
     * @author   Edison
     * @date    2023/3/31 15:44
     * @Param   [nums]
     * @return  int[]
     */
    public int[] sortArrayByParityII(int[] nums) {
        int oddIndex = 1;
        for (int i = 0; i < nums.length; i += 2) {
            if (nums[i] % 2 == 1) {
                while (nums[oddIndex] % 2 == 1) {
                    oddIndex += 2;
                }
                int temp = nums[i];
                nums[i] = nums[oddIndex];
                nums[oddIndex] = temp;
            }
        }
        return nums;
    }

    /*
     * @Description  35. 搜索插入位置
     * @author   Edison
     * @date    2023/3/31 16:09
     * @Param   [nums, target]
     * @return  int
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    /*
     * @Description  704. 二分查找
     * @author   Edison
     * @date    2023/3/31 16:25
     * @Param   [nums, target]
     * @return  int
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /*
     * @Description  2367. 算术三元组的数目
     * @author   Edison
     * @date    2023/3/31 16:33
     * @Param   [nums, diff]
     * @return  int
     */
    public int arithmeticTriplets(int[] nums, int diff) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] == diff) {
                    int index = j;
                    while (j < nums.length - 1 && nums[j + 1] - nums[index] != diff) {
                        j++;
                    }
                    if (j < nums.length - 1) {
                        count++;
                    }
                    break;
                }
            }
        }
        return count;
    }

    /*
     * @Description  69. x 的平方根
     * @author   Edison
     * @date    2023/3/31 16:45
     * @Param   [x]
     * @return  int
     */
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if ((long)mid * mid > x) {
                right = mid - 1;
            } else {
                ans = mid;
                left = mid + 1;
            }
        }
        return ans;
    }

    /*
     * @Description  367. 有效的完全平方数
     * @author   Edison
     * @date    2023/3/31 17:08
     * @Param   [num]
     * @return  boolean
     */
    public boolean isPerfectSquare(int num) {
        int left = 0;
        int right = num;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if ((long) mid * mid > num) {
                right = mid - 1;
            } else if ((long) mid * mid < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /*
     * @Description  27. 移除元素
     * @author   Edison
     * @date    2023/3/31 17:13
     * @Param   [nums, val]
     * @return  int
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow++] = temp;
            }
        }
        return slow;
    }

    /*
     * @Description  26. 删除有序数组中的重复项
     * @author   Edison
     * @date    2023/3/31 17:17
     * @Param   [nums]
     * @return  int
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    /*
     * @Description  283. 移动零
     * @author   Edison
     * @date    2023/4/3 10:27
     * @Param   [nums]
     * @return  void
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow++] = temp;
            }
        }
    }

    /*
     * @Description  844. 比较含退格的字符串
     * @author   Edison
     * @date    2023/4/3 10:33
     * @Param   [s, t]
     * @return  boolean
     */
    public boolean backspaceCompare(String s, String t) {
        int lenS = s.length() - 1;
        int lenT = t.length() - 1;
        int skipS = 0;
        int skipT = 0;
        while (lenS >= 0 || lenT >= 0) {
            while (lenS >= 0) {
                if (s.charAt(lenS) == '#') {
                    skipS++;
                    lenS--;
                } else if (skipS > 0) {
                    skipS--;
                    lenS--;
                } else {
                    break;
                }
            }
            while (lenT >= 0) {
                if (t.charAt(lenT) == '#') {
                    skipT++;
                    lenT--;
                } else if (skipT > 0) {
                    skipT--;
                    lenT--;
                } else {
                    break;
                }
            }
            if (lenT >= 0 && lenS >= 0) {
                if (s.charAt(lenS) != t.charAt(lenT)) {
                    return false;
                }
            } else {
                if (lenS >= 0 || lenT >= 0) {
                    return false;
                }
            }
            lenS--;
            lenT--;
        }
        return true;
    }

    /*
     * @Description  977. 有序数组的平方
     * @author   Edison
     * @date    2023/4/3 11:00
     * @Param   [nums]
     * @return  int[]
     */
    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int k = nums.length - 1;
        while (left <= right){
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                result[k--] = nums[left] * nums[left++];
            } else {
                result[k--] = nums[right] * nums[right--];
            }
        }
        return result;
    }

    /*
     * @Description  209. 长度最小的子数组
     * @author   Edison
     * @date    2023/4/3 11:16
     * @Param   [target, nums]
     * @return  int
     */
    public int minSubArrayLen(int target, int[] nums) {
        int slow = 0;
        int sum = 0;
        int len = nums.length;
        for (int fast = 0; fast < nums.length; fast++) {
            sum += nums[fast];
            while (sum >= target) {
                if (len > (fast - slow + 1)) {
                    len = fast - slow + 1;
                }
                sum -= nums[slow++];
            }
        }
        if (slow == 0) {
            len = 0;
        }
        return len;
    }

    /*
     * @Description  904. 水果成篮
     * @author   Edison
     * @date    2023/4/3 11:35
     * @Param   [fruits]
     * @return  int
     */
    public int totalFruit(int[] fruits) {
        int len = 1;
        int temp = 0;
        int left = 0;
        boolean flag = true;
        for (int right = 1; right < fruits.length; right++) {
            if (fruits[right] != fruits[left] && flag) {
                temp = fruits[right];
                flag = false;
            }
            if (fruits[right] != fruits[left] && fruits[right] != temp && !flag) {
                flag = true;
                if (len < right - left) {
                    len = right - left;
                }
                while (fruits[++left] != temp);
                right = left;
            }
            if (right == fruits.length - 1) {
                if (len < fruits.length - left) {
                    len = fruits.length - left;
                }
            }
        }
        return len;
    }

    /*
     * @Description  59. 螺旋矩阵 II
     * @author   Edison
     * @date    2023/4/3 15:49
     * @Param   [n]
     * @return  int[][]
     */
    public int[][] generateMatrix(int n) {
        int t = 0;
        int b = n - 1;
        int l = 0;
        int r = n - 1;
        int num = 1;
        int[][] nums = new int[n][n];
        while (num <= n * n) {
            for (int i = l; i <= r; i++) {
                nums[t][i] = num++;
            }
            t++;

            for (int i = t; i <= b; i++) {
                nums[i][r] = num++;
            }
            r--;

            for (int i = r; i >= l; i--) {
                nums[b][i] = num++;
            }
            b--;

            for (int i = b; i >= t; i--) {
                nums[i][l] = num++;
            }
            l++;
        }
        return nums;
    }

    /*
     * @Description  剑指 Offer 29. 顺时针打印矩阵
     * @author   Edison
     * @date    2023/4/3 16:01
     * @Param   [matrix]
     * @return  int[]
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[]{};
        }
        int[] res = new int[matrix.length * matrix[0].length];
        int len = res.length;
        int t = 0;
        int b = matrix.length - 1;
        int l = 0;
        int r = matrix[0].length - 1;
        int i = 0;
        while (i < len) {
            for (int z = l; z <= r; z++) {
                res[i++] = matrix[t][z];
            }
            t++;
            if (i >= len) {
                break;
            }

            for (int z = t; z <= b; z++) {
                res[i++] = matrix[z][r];
            }
            r--;
            if (i >= len) {
                break;
            }

            for (int z = r; z >= l; z--) {
                res[i++] = matrix[b][z];
            }
            b--;
            if (i >= len) {
                break;
            }

            for (int z = b; z >= t; z--) {
                res[i++] = matrix[z][l];
            }
            l++;
        }
        return res;
    }
}
