package sort;

import org.testng.annotations.Test;


/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/4/3 16:39
 */
public class SortTest {

    Solution solution = new Solution();
    int[] nums = {45,45,32,15,41,56,23,65,12,36,21,2,3,2222,1};

    @Test
    public void BubbleSortTest() {
        solution.bubbleSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void selectionSortTest() {
        solution.selectionSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void insertionSortTest() {
        solution.insertionSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void shellSortTest() {
        solution.shellSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void mergeSortTest() {
        nums = solution.mergeSort(this.nums, 0, this.nums.length - 1);
        for (int num : this.nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void quickSortTest() {
        solution.quickSort(nums, 0, nums.length - 1);
        for (int num : this.nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void heapSortTest() {
        solution.heapSort(nums);
        for (int num : this.nums) {
            System.out.print(num + " ");
        }
    }
}

class Solution {

    /*
     * @description: 冒泡排序
     * @author: edison
     * @date: 2023/7/25 16:28
     * @param: [nums]
     * @return: void
     */
    public void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
            }
        }
    }

    /*
     * @description: 选择排序
     * @author: edison
     * @date: 2023/7/25 16:29
     * @param: [nums]
     * @return: void
     */
    public void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[index]) {
                    index = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[index];
            nums[index] = temp;
        }
    }

    /*
     * @description: 插入排序
     * @author: edison
     * @date: 2023/7/25 16:48
     * @param: [nums]
     * @return: void
     */
    public void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int preIndex = i - 1;
            int current = nums[i];
            while (preIndex >= 0 && current < nums[preIndex]) {
                nums[preIndex + 1] = nums[preIndex];
                preIndex--;
            }
            nums[preIndex + 1] = current;
        }
    }

    /*
     * @description: 希尔排序
     * @author: edison
     * @date: 2023/7/25 16:48
     * @param: [nums]
     * @return: void
     */
    public void shellSort(int[] nums) {
        int gap = nums.length / 2;
        while (gap > 0) {
            for (int i = gap; i < nums.length; i++) {
                int preIndex = i - gap;
                int current = nums[i];
                while (preIndex >= 0 && current < nums[preIndex]) {
                    nums[preIndex + gap] = nums[preIndex];
                    preIndex -= gap;
                }
                nums[preIndex + gap] = current;
            }
            gap /= 2;
        }
    }

    /*
     * @description: 归并排序
     * @author: edison
     * @date: 2023/7/25 17:04
     * @param: [nums]
     * @return: void
     */
    public int[] mergeSort(int[] nums, int l, int h) {
        if (l == h) return new int[]{nums[l]};

        int mid = l + (h - l) / 2;
        int[] leftNums = mergeSort(nums, l, mid);
        int[] rightNums = mergeSort(nums, mid + 1, h);
        int[] newNums = new int[leftNums.length + rightNums.length];

        int m = 0, i = 0, j = 0;
        while (i < leftNums.length && j < rightNums.length) {
            newNums[m++] = leftNums[i] < rightNums[j] ? leftNums[i++] : rightNums[j++];
        }
        while (i < leftNums.length) {
            newNums[m++] = leftNums[i++];
        }
        while (j < rightNums.length) {
            newNums[m++] = rightNums[j++];
        }

        return newNums;
    }

    /*
     * @description: 快速排序
     * @author: edison
     * @date: 2023/7/25 17:35
     * @param: [nums, low, high]
     * @return: void
     */
    public void quickSort(int[] nums, int begin, int end) {
        if (begin > end) return;
        int temp = nums[begin];
        int i = begin, j = end;
        while (i != j) {
            while (i < j && nums[j] >= temp) j--;
            while (i < j && nums[i] <= temp) i++;
            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        nums[begin] = nums[i];
        nums[i] = temp;
        quickSort(nums, begin, i - 1);
        quickSort(nums, i + 1, end);
    }

    /*
     * @description: 堆排序
     * @author: edison
     * @date: 2023/7/25 17:55
     * @param: []
     * @return: void
     */
    public void heapSort(int[] nums) {

    }
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}



