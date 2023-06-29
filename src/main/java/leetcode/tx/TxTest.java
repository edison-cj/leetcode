package leetcode.tx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/5/4 9:37
 */
public class TxTest {

    Solution solution = new Solution();



}

class Solution {

    /*
     * @Description  455.分发饼干
     * @author   Edison
     * @date    2023/5/4 9:54
     * @Param   [g, s]
     * @return  int
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        for (int i = g.length - 1, j = s.length - 1; i >= 0 && j >= 0; i--) {
            if (s[j] >= g[i]) {
                count++;
                j--;
            }
        }
        return count;
    }

    /*
     * @Description  376. 摆动序列
     * @author   Edison
     * @date    2023/5/4 9:55
     * @Param   [nums]
     * @return  int
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int prediff = 0;
        int curdiff = 0;
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            curdiff = nums[i + 1] - nums[i];
            if ((prediff <= 0 && curdiff > 0) || (prediff >= 0 && curdiff < 0)) {
                count++;
                prediff = curdiff;
            }
        }
        return count;
    }

    /*
     * @Description  53. 最大子数组和
     * @author   Edison
     * @date    2023/5/4 10:04
     * @Param   [nums]
     * @return  int
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int sum = nums[0];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += nums[i];
            sum = Math.max(sum, res);
            if (res <= 0) {
                res = 0;
            }
        }
        return sum;
    }

    /*
     * @Description  122. 买卖股票的最佳时机 II
     * @author   Edison
     * @date    2023/5/5 9:59
     * @Param   [prices]
     * @return  int
     */
    public int maxProfit(int[] prices) {
        int sum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                sum += prices[i + 1] - prices[i];
            }
        }
        return sum;
    }

    /*
     * @Description  55. 跳跃游戏
     * @author   Edison
     * @date    2023/5/5 10:07
     * @Param   [nums]
     * @return  boolean
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, i + nums[i]);
            if (cover >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    /*
     * @Description  45. 跳跃游戏 II
     * @author   Edison
     * @date    2023/5/5 10:12
     * @Param   [nums]
     * @return  int
     */
    public int jump(int[] nums) {
        int count = 0;
        int cover = 0;
        int end = 0;
        for (int i = 0; i <= end && end < nums.length - 1; i++) {
            cover = Math.max(cover, i + nums[i]);
            if (i == end) {
                end = cover;
                count++;
            }
        }
        return count;
    }

    /*
     * @Description  1005. K 次取反后最大化的数组和
     * @author   Edison
     * @date    2023/5/5 10:27
     * @Param   [nums, k]
     * @return  int
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        if (k % 2 == 1) {
            nums[nums.length - 1] = -nums[nums.length - 1];
        }
        return Arrays.stream(nums).sum();
    }

    /*
     * @Description  134. 加油站
     * @author   Edison
     * @date    2023/5/6 9:53
     * @Param   [gas, cost]
     * @return  int
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int sum = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            sum += gas[i] - cost[i];
            if (curSum < 0) {
                curSum = 0;
                start = i + 1;
            }
        }
        if (sum < 0) {
            return -1;
        }
        return start;
    }

    /*
     * @Description  135. 分发糖果
     * @author   Edison
     * @date    2023/5/6 10:00
     * @Param   [ratings]
     * @return  int
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
                candyVec[i] = Math.max(candyVec[i], candyVec[i + 1] + 1);
            }
        }
        return Arrays.stream(candyVec).sum();
    }

    /*
     * @Description  860. 柠檬水找零
     * @author   Edison
     * @date    2023/5/6 10:19
     * @Param   [bills]
     * @return  boolean
     */
    public boolean lemonadeChange(int[] bills) {
        int fiveNum = 0;
        int tenNum = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                fiveNum++;
            } else if (bills[i] == 10) {
                tenNum++;
                fiveNum--;
            } else if (bills[i] == 20) {
                if (tenNum > 0) {
                    tenNum--;
                    fiveNum--;
                } else {
                    fiveNum -= 3;
                }
            }
            if (fiveNum < 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  406. 根据身高重建队列
     * @author   Edison
     * @date    2023/5/6 10:28
     * @Param   [people]
     * @return  int[][]
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });
        LinkedList<int[]> que = new LinkedList<>();
        for (int[] person : people) {
            que.add(person[1], person);
        }
        return que.toArray(new int[people.length][]);
    }

    /*
     * @Description  452. 用最少数量的箭引爆气球
     * @author   Edison
     * @date    2023/5/7 10:42
     * @Param   [points]
     * @return  int
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) {
                count++;
            } else {
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return count;
    }

    /*
     * @Description  435. 无重叠区间
     * @author   Edison
     * @date    2023/5/7 10:52
     * @Param   [intervals]
     * @return  int
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int count = 1;

//        for (int i = 1; i < intervals.length; i++) {
//            if (intervals[i][0] < intervals[i - 1][1]) {
//                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
//            } else {
//                count++;
//            }
//        }
        int pre = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < pre) {
                pre = Math.min(pre, intervals[i][1]);
            } else {
                count++;
                pre = intervals[i][1];
            }
        }
        return intervals.length - count;
    }

    /*
     * @Description  763. 划分字母区间
     * @author   Edison
     * @date    2023/5/7 11:34
     * @Param   [s]
     * @return  List<Integer>
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        int[] edge = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            edge[chars[i] - 'a'] = i;
        }
        int index = 0;
        int last = -1;
        for (int i = 0; i < chars.length; i++) {
            index = Math.max(index, edge[chars[i] - 'a']);
            if (i == index) {
                list.add(i - last);
                last = i;
            }
        }
        return list;
    }

    /*
     * @Description  56. 合并区间
     * @author   Edison
     * @date    2023/5/7 11:41
     * @Param   [intervals]
     * @return  int[][]
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > end) {
                list.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                end = Math.max(end, intervals[i][1]);
            }
        }
        list.add(new int[]{start, end});
        return list.toArray(new int[list.size()][]);
    }

    /*
     * @Description  738. 单调递增的数字
     * @author   Edison
     * @date    2023/5/8 9:59
     * @Param   [n]
     * @return  int
     */
    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int flag = chars.length;
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i - 1] > chars[i]) {
                flag = i;
                chars[i - 1]--;
            }
        }
        for (int i = flag; i < chars.length; i++) {
            chars[i] = '9';
        }
        return Integer.valueOf(new String(chars));
    }
}
