package wcj.array;

import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2022/11/15 16:10
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] nums = new int[]{};
        int target = 1;
        int[] range;

        Solution solution = new Solution();
        range = solution.searchRange2(nums,target);
        for(int num : range){
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println("69. x 的平方根");
        System.out.println("算数平平方根:" + solution.mySqrt(1));
        System.out.println();

        System.out.println("367. 有效的完全平方数");
        System.out.println("输出：" + solution.isPerfectSquare(8));
        System.out.println();

        System.out.println("27. 移除元素");
        int[] nums1 = new int[]{0,1,2,2,3,0,4,2};
        System.out.println("个数为：" + solution.removeElement(nums1,2));
        System.out.println();

        System.out.println("26. 删除有序数组中的重复项");
        int[] nums2 = {1,1,2};
        System.out.println("长度为：" + solution.removeDuplicates(nums2));
        System.out.println();

        System.out.println("283. 移动零");
        int[] nums3 = {0,1,0,3,12};
        solution.moveZeroes(nums3);
        for (int num : nums3) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println("844.比较含退格的字符串");
        String s = "ab#c";
        String t = "ad#c";
        System.out.println("输出：" + solution.backspaceCompare(s,t));
        System.out.println();

        System.out.println("977. 有序数组的平方");
        int[] nums4 = {1,2,1};
        System.out.println(Arrays.toString(solution.sortedSquares(nums4)));
        System.out.println();

        System.out.println("209. 长度最小的子数组");
        int[] nums5 = {5,1,3,5,10,7,4,9,2,8};
        System.out.println(solution.minSubArrayLen2(15,nums5));
        System.out.println();

        System.out.println("904. 水果成篮");
        int[] nums6 = {3,3,3,1,2,1,1,2,3,3,4};
        System.out.println(solution.totalFruit(nums6));
        System.out.println();

        System.out.println("76. 最小覆盖子串");
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        System.out.println(solution.minWindow(s1,t1));
        System.out.println();

        System.out.println("59. 螺旋矩阵 II");
        int[][] mat = solution.generateMatrix2(4);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

    }
}

class Solution{

    public int search(int[] nums, int target) {

        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int middle = left + (right - left) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target){
                left = middle + 1;
            } else {
                return middle;
            }
        }

        return -1;
    }

    public int searchInsert(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return left;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] range = new int[]{-1,- 1};
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if(nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                int temp = mid;
                while (nums[temp] == target) {
                    mid = temp;
                    range[0] = mid;
                    if(temp == 0) {
                        break;
                    }
                    temp--;
                }

                while (nums[mid] == target) {
                    range[1] = mid;
                    if(mid == nums.length - 1) {
                        break;
                    }
                    mid++;
                }
                break;
            }
        }
        return range;
    }

    public int[] searchRange2(int[] nums, int target) {
        int[] range = new int[]{-1,- 1};
        int left;
        int right;
        left = searchInsert(nums,target);
        right = searchInsert(nums,target + 1);
        if(left != nums.length && nums[left] == target) {
            range[0] = left;
            range[1] = right - 1;
        }
        return range;
    }

    public int mySqrt(int x) {

        int left = 0;
        int right = x;
        int ans = 0;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if ((long)mid * mid > x) {
                right = mid - 1;
            } else {
                ans = mid;
                left = mid + 1;
            }
        }
        return ans;
    }

    public boolean isPerfectSquare(int num) {
        int left = 0;
        int right = num;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
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

    public int removeElement(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
           if (nums[fastIndex] != val) {
               nums[slowIndex++] = nums[fastIndex];
           }
        }
        return slowIndex;
    }

    public int removeDuplicates(int[] nums) {
        int slowIndex = 0;
        int fastIndex = 1;
        while (fastIndex < nums.length) {
            if (nums[slowIndex] != nums[fastIndex]) {
                nums[++slowIndex] = nums[fastIndex];
            }
            fastIndex++;
        }
        return slowIndex + 1;
    }

    public int removeDuplicates2(int[] nums) {
        int slowIndex = 0;
        for (int fastIndex = 1;fastIndex < nums.length; fastIndex++) {
            if(nums[slowIndex] != nums[fastIndex]) {
                nums[++slowIndex] = nums[fastIndex];
            }
        }
        return slowIndex + 1;
    }

    public void moveZeroes(int[] nums) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if(nums[fastIndex] != 0) {
                int temp = nums[slowIndex];
                nums[slowIndex++] = nums[fastIndex];
                nums[fastIndex] = temp;
            }
        }
    }

    public boolean backspaceCompare(String s, String t) {

        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    j--;
                    skipT++;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            }else {
                if (i >= 0 || j >= 0) {
                    return  false;
                }
            }
            i--;
            j--;

        }

        return true;
    }

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

    public int minSubArrayLen(int target, int[] nums) {

        int min = nums.length;
        int left = 0;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (right >= left && sum >= target) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                }
                sum -= nums[left++];
            }
        }
        if (left == 0) {
            min = 0;
        }
        return min;
    }

    public int minSubArrayLen2(int target, int[] nums) {

        int min = nums.length;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (right >= left && sum >= target) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                }
                sum -= nums[++left];
            }
        }

        if (left == 0) {
            min = 0;
        }

        return min;
    }

    public int totalFruit(int[] fruits) {

        int maxLength = 1;
        int left = 0;
        int temp = 0;
        boolean flag = true;
        for (int right = 1; right < fruits.length; right++) {
            if (fruits[right] != fruits[left] && flag) {
                temp = fruits[right];
                flag = false;
            }
            if (fruits[right] != fruits[left] && fruits[right] != temp) {
                flag = true;
                if (right - left > maxLength) {
                    maxLength = right - left;
                }
                while (fruits[++left] != temp);
                right = left;
            }
            if (right == fruits.length - 1) {
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                }
            }

        }
        return maxLength;
    }

    public String minWindow(String s, String t) {

        return null;
    }

    public int[][] generateMatrix(int n) {

        int[][] mat = new int[n][n];
        int l = 0;
        int r = n - 1;
        int t = 0;
        int b = n - 1;
        int num = 1;
        int tar = n * n;
         while (num <= tar) {
            for (int i = l; i <= r; i++) {
                mat[t][i] = num++;
            }
            t++;

            for (int i = t; i <= b; i++) {
                mat[i][r] = num++;
            }
            r--;

            for (int i = r; i >= l; i--) {
                mat[b][i] = num++;
            }
            b--;

            for (int i = b; i >= t; i--) {
                mat[i][l] = num++;
            }
            l++;

         }
         return mat;
    }

    public int[][] generateMatrix2(int n) {

        int[][] nums = new int[n][n];
        int l = 0;
        int r = n - 1;
        int t = 0;
        int b = n - 1;
        int tar = n * n;
        int num = 1;

        while (num <= tar) {
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
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> list = new ArrayList<>();
        int l = 0, r = matrix[matrix.length].length - 1;
        int t = 0, b = matrix.length - 1;
        int loop = matrix.length < matrix[matrix.length].length ? matrix.length : matrix[matrix.length].length;

        while (loop-- > 0) {

        }

        return list;
    }

}


