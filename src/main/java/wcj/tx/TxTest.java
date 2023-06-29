package wcj.tx;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/3/22 10:39
 */
public class TxTest {

    Solution solution = new Solution();

    @Test
    public void findContentChildrenTest() {
        int[] g = {1, 2};
        int[] s = {1, 2, 3};
        System.out.println(solution.findContentChildren(g, s));
    }

    @Test
    public void wiggleMaxLengthTest() {
        int[] nums = {2, 2};
        System.out.println(solution.wiggleMaxLength(nums));
    }

    @Test
    public void maxSubArrayTest() {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution.maxSubArray(nums));
    }

    @Test
    public void maxProfitTest() {
        int[] nums = {7,6,4,3,1};
        System.out.println(solution.maxProfit(nums));
    }

    @Test
    public void largestSumAfterKNegationsTest() {
        int[] nums = {2,-3,-1,5,-4};
        System.out.println(solution.largestSumAfterKNegations(nums, 2));
    }

    @Test
    public void jumpTest() {
        int[] nums = {2,3,1,1,4};
        System.out.println(solution.jump(nums));
    }

    @Test
    public void candyTest() {
        int[] nums = {1,2,2};
        System.out.println(solution.candy(nums));
    }

    @Test
    public void reconstructQueueTest() {
        int[][] nums = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] queue = solution.reconstructQueue(nums);
        for (int[] q : queue) {
            System.out.println(q);
        }
    }

    @Test
    public void mergeTest() {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};

    }

    @Test
    public void monotoneIncreasingDigitsTest() {
        int n = 10;
        System.out.println(solution.monotoneIncreasingDigits(n));
        char[] chars = String.valueOf(n).toCharArray();
    }
}

class Solution {

    /*
     * @Description  455. 分发饼干
     * @author   Edison
     * @date    2023/3/22 10:50
     * @Param   [g, s]
     * @return  int
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index = s.length - 1;
        int result = 0;
        for (int i = g.length - 1; i >= 0; i--) {
            if (index >= 0 && g[i] <= s[index]) {
                result++;
                index--;
            }
        }
        return result;
    }
    
    /*
     * @Description  376. 摆动序列
     * @author   Edison
     * @date    2023/3/22 10:50
     * @Param
     * @return  
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int curdiff = 0;
        int prediff = 0;
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            curdiff = nums[i] - nums[i - 1];
            if ((prediff <= 0 && curdiff > 0) || (prediff >= 0 && curdiff < 0)) {
                result++;
                prediff = curdiff;
            }
        }
        return result;
    }

    /*
     * @Description  53. 最大子数组和
     * @author   Edison
     * @date    2023/3/22 11:22
     * @Param
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int sum = 0;
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    /*
     * @Description  122. 买卖股票的最佳时机 II
     * @author   Edison
     * @date    2023/3/22 14:48
     * @Param
     * @return
     */
    public int maxProfit(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                sum += prices[i] - prices[i - 1];
            }
        }
        return sum;
    }

    /*
     * @Description  55. 跳跃游戏
     * @author   Edison
     * @date    2023/3/22 15:53
     * @Param
     * @return
     */
    public boolean canJump(int[] nums) {
        int count = 0;
        if (nums.length == 1) {
            return true;
        }
        for (int i = 0; i <= count; i++) {
            count = Math.max(i + nums[i], count);
            if (count >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
    
    /*
     * @Description  45. 跳跃游戏 II
     * @author   Edison
     * @date    2023/3/22 16:17
     * @Param
     * @return  
     */
    public int jump(int[] nums) {
        int count = 0;
        int curDistance = 0;
        int nextDistance = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextDistance = Math.max(nums[i] + i, nextDistance);
            if (i == curDistance) {
                count++;
                curDistance = nextDistance;
            }
        }
        return count;
    }
    
    /*
     * @Description  1005. K 次取反后最大化的数组和
     * @author   Edison
     * @date    2023/3/23 10:03
     * @Param
     * @return  
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] *= -1;
                k--;
            }
        }
        if (k % 2 == 1) {
            nums[nums.length - 1] = -nums[nums.length - 1];
        }
        int result = 0;
        for (int num : nums) {
            result += num;
        }
        return result;
    }
    
    /*
     * @Description  134. 加油站
     * @author   Edison
     * @date    2023/3/23 10:35
     * @Param
     * @return  
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int index = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {
                curSum = 0;
                index = i + 1;
            }
        }
        if (totalSum < 0) {
            return -1;
        }
        return index;
    }

    /*
     * @Description 135. 分发糖果
     * @author   Edison
     * @date    2023/3/23 11:17
     * @Param
     * @return
     */
    public int candy(int[] ratings) {
        int[] candyVec = new int[ratings.length];
        candyVec[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candyVec[i] = candyVec[i - 1] + 1;
            } else {
                candyVec[i] = 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candyVec[i] = Math.max(candyVec[i + 1] + 1, candyVec[i]);
            }
        }
        int candySum = 0;
        for (int candy : candyVec) {
            candySum += candy;
        }
        return candySum;
    }

    /*
     * @Description  860. 柠檬水找零

     * @author   Edison
     * @date    2023/3/23 14:36
     * @Param
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int fiveNum = 0;
        int tenNum = 0;
        for (int bill : bills) {
            if (bill == 5) {
                fiveNum++;
            } else if (bill == 10) {
                fiveNum--;
                tenNum++;
            } else if (bill == 20){
                if (tenNum > 0) {
                    tenNum--;
                    fiveNum--;
                } else {
                    fiveNum -= 3;
                }
            }
            if (fiveNum < 0 || tenNum < 0) {
                return false;
            }
        }
        return true;
    }
    
    /*
     * @Description  406. 根据身高重建队列
     * @author   Edison
     * @date    2023/3/23 14:51
     * @Param
     * @return  
     */
    public int[][] reconstructQueue(int[][] people) {
        // 身高从大到小排（身高相同k小的站前面）
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        LinkedList<int[]> que = new LinkedList<>();

        for (int[] p : people) {
            que.add(p[1],p);
        }

        return que.toArray(new int[people.length][]);
    }
    
    /*
     * @Description  452. 用最少数量的箭引爆气球
     * @author   Edison
     * @date    2023/3/23 15:15
     * @Param
     * @return  
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        int result = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) {
                result++;
            } else {
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return result;
    }

    /*
     * @Description  435. 无重叠区间
     * @author   Edison
     * @date    2023/3/23 15:38
     * @Param
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
            } else {
                count++;
            }
        }
        return intervals.length - count;
    }
    
    /*
     * @Description  763. 划分字母区间
     * @author   Edison
     * @date    2023/3/23 15:53
     * @Param
     * @return  
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        int[] edge = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            edge[chars[i] - 'a'] = i;
        }
        int idx = 0;
        int last = -1;
        for (int i = 0; i < chars.length; i++) {
            idx = Math.max(idx, edge[chars[i] - 'a']);
            if (i == idx) {
                list.add(i - last);
                last = i;
            }
        }
        return list;
    }

    /*
     * @Description  56. 合并区间
     * @author   Edison
     * @date    2023/3/23 16:17
     * @Param
     * @return
     */
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> list = new LinkedList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > end) {
                list.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                end = Math.max(intervals[i][1], end);
            }
        }
        list.add(new int[]{start, end});
        return list.toArray(new int[list.size()][]);
    }
    
    /*
     * @Description  738. 单调递增的数字
     * @author   Edison
     * @date    2023/3/23 16:41
     * @Param
     * @return  
     */
    public int monotoneIncreasingDigits(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        int flag = chars.length;
        for (int i = chars.length - 1; i >= 1; i--) {
            if (chars[i - 1] > chars[i]) {
                chars[i - 1]--;
                flag = i;
            }
        }
        for (int i = flag; i < chars.length; i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(new String(chars));
    }

    /*
     * @Description  968. 监控二叉树
     * @author   Edison
     * @date    2023/3/23 17:08
     * @Param
     * @return
     */
    int count = 0;
    public int minCameraCover(TreeNode root) {
        if (minCameraCoverTraversal(root) == 0) {
            count++;
        }
        return count;
    }

    int minCameraCoverTraversal(TreeNode root) {
        if (root == null) {
            return 2;
        }
        /**
         节点的状态值：
         0 表示无覆盖
         1 表示 有摄像头
         2 表示有覆盖
         后序遍历，根据左右节点的情况,来判读 自己的状态
         */
        int left = minCameraCoverTraversal(root.left);
        int right = minCameraCoverTraversal(root.right);
        // 情况1
        // 左右节点都有覆盖

        // 情况2
        // left == 0 && right == 0 左右节点无覆盖
        // left == 1 && right == 0 左节点有摄像头，右节点无覆盖
        // left == 0 && right == 1 左节点有无覆盖，右节点摄像头
        // left == 0 && right == 2 左节点无覆盖，右节点覆盖
        // left == 2 && right == 0 左节点覆盖，右节点无覆盖

        // 情况3
        // left == 1 && right == 2 左节点有摄像头，右节点有覆盖
        // left == 2 && right == 1 左节点有覆盖，右节点有摄像头
        // left == 1 && right == 1 左右节点都有摄像头

        if (left == 2 && right == 2) {
            return 0;
        } else if (left == 0 || right == 0) {
            count++;
            return 1;
        } else {
            return 2;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


