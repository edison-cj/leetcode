package leetcode.singlestack;

import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/5/22 9:58
 */
public class SingleStackTest {



}

class Solution {

    /*
     * @Description  739. 每日温度
     * @author   Edison
     * @date    2023/5/22 10:05
     * @Param   [temperatures]
     * @return  int[]
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }

    /*
     * @Description  496. 下一个更大元素 I
     * @author   Edison
     * @date    2023/5/22 10:05
     * @Param   [nums1, nums2]
     * @return  int[]
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                int pre = nums2[stack.pop()];
                if (map.containsKey(pre)) {
                    res[map.get(pre)] = nums2[i];
                }
            }
            stack.push(i);
        }
        return res;
    }

    /*
     * @Description  503. 下一个更大元素 II
     * @author   Edison
     * @date    2023/5/22 10:25
     * @Param   [nums]
     * @return  int[]
     */
    public int[] nextGreaterElements(int[] nums) {
        if (nums.length == 1) {
            return new int[]{-1};
        }
        Stack<Integer> stack = new Stack<>();
        int len = nums.length;
        int[] res =new int[len];
        Arrays.fill(res, -1);
        for (int i = 0; i < 2 * len; i++) {
            while (!stack.isEmpty() && nums[i % len] > nums[stack.peek()]) {
                res[stack.peek()] = nums[i % len];
                stack.pop();
            }
            stack.push(i % len);
        }
        return res;
    }

    /*
     * @Description  42. 接雨水
     * @author   Edison
     * @date    2023/5/22 10:39
     * @Param   [height]
     * @return  int
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len <= 2) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int sum = 0;
        for (int index = 1; index < len; index++) {
            int stackTop = stack.peek();
            if (height[index] < height[stackTop]) {
                stack.push(index);
            } else if (height[index] == height[stackTop]) {
                stack.pop();
                stack.push(index);
            } else {
                int heightAdIdx = height[index];
                while (!stack.isEmpty() && (heightAdIdx > height[stackTop])) {
                    int mid = stack.pop();
                    if (!stack.isEmpty()) {
                        int left = stack.peek();
                        int h = Math.min(height[left], height[index]) - height[mid];
                        int w = index - left - 1;
                        int hold = h * w;
                        if (hold > 0) sum += hold;
                        stackTop = stack.peek();
                    }
                }
                stack.push(index);
            }
        }
        return sum;
    }

    /*
     * @Description  84. 柱状图中最大的矩形
     * @author   Edison
     * @date    2023/5/22 14:53
     * @Param   [heights]
     * @return  int
     */
    public int largestRectangleArea(int[] heights) {
        int[] newHeight = new int[heights.length + 2];
        System.arraycopy(heights, 0, newHeight, 1, heights.length);
        newHeight[heights.length + 1] = 0;
        newHeight[0] = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        int res = 0;
        for (int i = 1; i < newHeight.length; i++) {
            while (newHeight[i] < newHeight[stack.peek()]) {
                int mid = stack.pop();
                int w = i - stack.peek() - 1;
                int h = newHeight[mid];
                res = Math.max(res, w * h);
            }
            stack.push(i);
        }
        return res;
    }

}
