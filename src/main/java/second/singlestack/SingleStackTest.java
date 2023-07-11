package second.singlestack;

import java.util.*;

/**
 * @Author: edison
 * @CreateTime: 2023-07-11  09:45
 * @Description: 单调栈
 * @Version: 1.0
 */
public class SingleStackTest {
    Solution solution = new Solution();


}

class Solution {

    /*
     * @description: 739. 每日温度
     * @author: edison 
     * @date: 2023/7/11 9:46
     * @param: [temperatures]
     * @return: int[]
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                ans[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return ans;
    }

    /*
     * @description: 496. 下一个更大元素 I
     * @author: edison 
     * @date: 2023/7/11 9:53
     * @param: [nums1, nums2]
     * @return: int[]
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        Arrays.fill(ans, -1);
        Deque<Integer> stack = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                int pre = nums2[stack.pop()];
                if (map.containsKey(pre)) {
                    ans[map.get(pre)] = nums2[i];
                }
            }
            stack.push(i);
        }
        return ans;
    }

    /*
     * @description: 503. 下一个更大元素 II
     * @author: edison 
     * @date: 2023/7/11 10:09
     * @param: [nums]
     * @return: int[]
     */
    public int[] nextGreaterElements(int[] nums) {
        if (nums.length == 1) return new int[]{-1};
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < nums.length * 2; i++) {
            while (!stack.isEmpty() && nums[i % nums.length] > nums[stack.peek()]) {
                ans[stack.peek()] = nums[i % nums.length];
                stack.pop();
            }
            stack.push(i % nums.length);
        }
        return ans;
    }

    /*
     * @description: 42. 接雨水
     * @author: edison 
     * @date: 2023/7/11 10:19
     * @param: [height]
     * @return: int
     */
    public int trap(int[] height) {
        if (height.length <= 2) return 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        int ans = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] < height[stack.peek()]) {
                stack.push(i);
            } else if (height[i] == height[stack.peek()]) {
                stack.pop();
                stack.push(i);
            } else {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    int mid = stack.pop();
                    if (!stack.isEmpty()) {
                        int h = Math.min(height[i], height[stack.peek()]) - height[mid];
                        int w = i - stack.peek() - 1;
                        ans += h * w;
                    }
                }
                stack.push(i);
            }
        }
        return ans;
    }

    /*
     * @description: 84. 柱状图中最大的矩形
     * @author: edison 
     * @date: 2023/7/11 10:46
     * @param: [heights]
     * @return: int
     */
    public int largestRectangleArea(int[] heights) {
        int[] newHeights = new int[heights.length + 2];
        System.arraycopy(heights, 0, newHeights, 1, heights.length);
        newHeights[0] = newHeights[heights.length + 1] = 0;
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        for (int i = 1; i < newHeights.length; i++) {
            while (newHeights[i] < newHeights[stack.peek()]) {
                int h = newHeights[stack.pop()];
                int w = i - stack.peek() - 1;
                ans = Math.max(ans, h * w);
            }
            stack.push(i);
        }
        return ans;
    }
}
