package code;

import java.util.Map;

/**
 * @Author: edison
 * @CreateTime: 2023-08-01  11:26
 * @Description: TODO
 * @Version: 1.0
 */
public class Code {

    Solution solution = new Solution();


}

class Solution {

    /*
     * @description: 4. 寻找两个正序数组的中位数
     * @author: edison 
     * @date: 2023/8/1 11:27
     * @param: [nums1, nums2]
     * @return: double
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, l) + findKth(nums1, 0, nums2, 0, r)) / 2.0;
    }
    int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) return nums2[j + k - 1];
        if (j >= nums2.length) return nums1[i + k - 1];
        if (k == 1) return Math.min(nums1[i], nums2[j]);
        int mid1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int mid2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        return mid1 < mid2 ? findKth(nums1, i + k / 2, nums2, j , k - k / 2) : findKth(nums1, i, nums2, j + k / 2, k - k / 2);
    }
}
