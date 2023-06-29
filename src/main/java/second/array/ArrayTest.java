package second.array;

import org.testng.internal.annotations.IAnnotationFinder;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/5/29 10:01
 */
public class ArrayTest {
}

class Solution {

    /*
     * @Description  704. 二分查找
     * @author   Edison
     * @date    2023/5/29 10:02
     * @Param   [nums, target]
     * @return  int
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
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
     * @Description  35. 搜索插入位置
     * @author   Edison
     * @date    2023/5/29 10:07
     * @Param   [nums, target]
     * @return  int
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
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
     * @Description  34. 在排序数组中查找元素的第一个和最后一个位置
     * @author   Edison
     * @date    2023/5/29 10:10
     * @Param   [nums, target]
     * @return  int[]
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                while (mid > 0 && nums[mid - 1] == target) mid--;
                res[0] = mid;
                while (mid < nums.length - 1 && nums[mid + 1] == target) mid++;
                res[1] = mid;
                return res;
            }
        }
        return res;
    }

    /*
     * @Description  69. x 的平方根
     * @author   Edison
     * @date    2023/5/29 10:18
     * @Param   [x]
     * @return  int
     */
    int s;
    public int mySqrt(int x) {
        //牛顿迭代
        s = x;
        if (x == 0) return x;
        return ((int) (sqrts(x)));

        //二分法
//        int left = 0;
//        int right = x;
//        while (left < right) {
//            int mid = (right - left) >> 1 + left;
//            if ((long) mid * mid > x) {
//                right = mid;
//            } else {
//                left = mid;
//            }
//        }
//        return left;
    }
    double sqrts(double x) {
        double res = (x + s / x) / 2;
        if (res == x) {
            return x;
        } else {
            return sqrts(res);
        }
    }


    /*
     * @Description  27. 移除元素
     * @author   Edison
     * @date    2023/5/29 10:49
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
     * @date    2023/5/29 10:53
     * @Param   [nums]
     * @return  int
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    /*
     * @Description  283. 移动零
     * @author   Edison
     * @date    2023/5/29 11:02
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
     * @date    2023/5/29 11:12
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
                }else if (skipS > 0) {
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
                }else if (skipT > 0) {
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
     * @date    2023/5/29 11:12
     * @Param   [nums]
     * @return  int[]
     */
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int k = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                res[k--] = nums[left] * nums[left];
                left++;
            } else {
                res[k--] = nums[right] * nums[right];
                right--;
            }
        }
        return res;
    }

    /*
     * @Description  209. 长度最小的子数组
     * @author   Edison
     * @date    2023/5/29 11:18
     * @Param   [target, nums]
     * @return  int
     */
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length + 1;
        int slow = 0;
        int sum = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            sum += nums[fast];
            while (sum >= target) {
                if (fast - slow + 1 < len) {
                    len = fast - slow + 1;
                }
                sum -= nums[slow++];
            }
        }
        return len == nums.length + 1 ? 0 : len;
    }

    /*
     * @Description  TDOO
     * @author   Edison
     * @date    2023/5/29 11:28
     * @Param   [n]
     * @return  int[][]
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int num = 1;
        while (num <= n * n) {
            for (int i = left; i <= right; i++) {
                res[top][i] = num++;
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                res[i][right] = num++;
            }
            right--;

            for (int i = right; i >= left; i--) {
                res[bottom][i] = num++;
            }
            bottom--;

            for (int i = bottom; i >= top; i--) {
                res[i][left] = num++;
            }
            left++;
        }
        return res;
    }
}
