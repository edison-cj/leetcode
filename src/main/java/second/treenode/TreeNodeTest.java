package second.treenode;

import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/6/5 10:08
 */
public class TreeNodeTest {
}

class Solution {

    /*
     * @Description  199. 二叉树的右视图
     * @author   Edison
     * @date    2023/6/5 14:42
     * @Param   [root]
     * @return  List<Integer>
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (size == 0) {
                    list.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return list;
    }

    /*
     * @Description  637. 二叉树的层平均值
     * @author   Edison
     * @date    2023/6/5 14:51
     * @Param   [root]
     * @return  java.util.List<java.lang.Double>
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(sum / size);
        }
        return list;
    }

    /*
     * @Description  429.N叉树的层序遍历
     * @author   Edison
     * @date    2023/6/5 14:56
     * @Param   [root]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> levelOrder(Node1 root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node1> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                Node1 node = queue.poll();
                list.add(node.val);
                if (node.children != null) {
                    for (Node1 child : node.children) {
                        queue.add(child);
                    }
                }
            }
            res.add(list);
        }
        return res;
    }

    /*
     * @Description  515. 在每个树行中找最大值
     * @author   Edison
     * @date    2023/6/5 15:07
     * @Param   [root]
     * @return  java.util.List<java.lang.Integer>
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = queue.peek().val;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.val > max) {
                    max = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(max);
        }
        return list;
    }

    /*
     * @Description  116. 填充每个节点的下一个右侧节点指针
     * @author   Edison
     * @date    2023/6/5 15:12
     * @Param   [root]
     * @return  Node
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (size > 0) {
                    node.next = queue.peek();
                }
            }
        }
        return root;
    }

    /*
     * @Description  给定一个二叉树，找出其最大深度。
     * @author   Edison
     * @date    2023/6/5 15:21
     * @Param   [root]
     * @return  int
     */
    public int maxDepth(TreeNode root) {
        return getDepth(root);
    }
    int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return 1 + Math.max(left, right);
    }

    /*
     * @Description  111. 二叉树的最小深度
     * @author   Edison
     * @date    2023/6/5 15:25
     * @Param   [root]
     * @return  int
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 || right == 0) {
            return 1 + left + right;
        }
        return 1 + Math.min(left, right);
    }

    /*
     * @Description  226. 翻转二叉树
     * @author   Edison
     * @date    2023/6/5 15:34
     * @Param   [root]
     * @return  second.treenode.TreeNode
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /*
     * @Description  101. 对称二叉树
     * @author   Edison
     * @date    2023/6/6 10:45
     * @Param   [root]
     * @return  boolean
     */
    public boolean isSymmetric(TreeNode root) {
        return compare(root.left, root.right);
    }
    boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        }
        return compare(left.left, right.right) && compare(left.right, right.left);
    }

    /*
     * @Description  100. 相同的树
     * @author   Edison
     * @date    2023/6/6 10:50
     * @Param   [p, q]
     * @return  boolean
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /*
     * @Description  572. 另一棵树的子树
     * @author   Edison
     * @date    2023/6/6 10:55
     * @Param   [root, subRoot]
     * @return  boolean
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    /*
     * @Description  222.完全二叉树的节点个数
     * @author   Edison
     * @date    2023/6/6 11:04
     * @Param   [root]
     * @return  int
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /*
     * @Description  110. 平衡二叉树
     * @author   Edison
     * @date    2023/6/6 11:05
     * @Param   [root]
     * @return  boolean
     */
    public boolean isBalanced(TreeNode root) {
        return getDepth1(root) != -1;
    }
    int getDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth1(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getDepth1(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(right - left) > 1 ? -1 : 1 + Math.max(left, right);
    }

    /*
     * @Description  257. 二叉树的所有路径
     * @author   Edison
     * @date    2023/6/6 11:21
     * @Param   [root]
     * @return  java.util.List<java.lang.String>
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> paths = new ArrayList<>();
        getPath(root, list, paths);
        return list;
    }
    void getPath(TreeNode root, List<String> list, List<Integer> paths) {
        paths.add(root.val);
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));
            list.add(sb.toString());
            return;
        }
        if (root.left != null) {
            getPath(root.left, list, paths);
            paths.remove(paths.size() - 1);
        }
        if (root.right != null) {
            getPath(root.right, list, paths);
            paths.remove(paths.size() - 1);
        }
    }

    /*
     * @Description  404. 左叶子之和
     * @author   Edison
     * @date    2023/6/7 10:29
     * @Param   [root]
     * @return  int
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftValue = sumOfLeftLeaves(root.left);
        int rightValue = sumOfLeftLeaves(root.right);
        int midValue = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            midValue += root.left.val;
        }
        return midValue + leftValue + rightValue;
    }

    /*
     * @Description  513. 找树左下角的值
     * @author   Edison
     * @date    2023/6/7 10:44
     * @Param   [root]
     * @return  int
     */
    int Deep = -1;
    int value = 0;
    public int findBottomLeftValue(TreeNode root) {
//        int res = 0;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            res = queue.peek().val;
//            while (size-- > 0) {
//                TreeNode node = queue.poll();
//                if (node.left != null) {
//                    queue.add(node.left);
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                }
//            }
//        }
//        return res;
        value = root.val;
        findLeftValue(root, 0);
        return value;
    }
    void  findLeftValue(TreeNode root, int deep) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (deep > Deep) {
                Deep = deep;
                value = root.val;
            }
        }
        if (root.left != null) {
            findLeftValue(root.left, deep + 1);
        }
        if (root.right != null) {
            findLeftValue(root.right, deep  +1);
        }
    }

    /*
     * @Description  112. 路径总和
     * @author   Edison
     * @date    2023/6/7 11:06
     * @Param   [root, targetSum]
     * @return  boolean
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return findPathSum(root, targetSum - root.val);
    }
    boolean findPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && targetSum == 0) {
            return true;
        }
        if (root.left != null) {
            if (findPathSum(root.left, targetSum - root.left.val)) {
                return true;
            }
        }
        if (root.right != null) {
            if (findPathSum(root.right, targetSum - root.right.val)) {
                return true;
            }
        }
        return false;
    }

    /*
     * @Description  106. 从中序与后序遍历序列构造二叉树
     * @author   Edison
     * @date    2023/6/7 11:17
     * @Param   [inorder, postorder]
     * @return  second.treenode.TreeNode
     */
    Map<Integer, Integer> map;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeTraversal(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }
    TreeNode buildTreeTraversal(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd) {
        if (inBegin >= inEnd || postBegin >= postEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd - 1]);
        int rootIndex = map.get(root.val);
        int leftNum = rootIndex - inBegin;
        root.left = buildTreeTraversal(inorder, inBegin, rootIndex, postorder, postBegin, postBegin + leftNum);
        root.right = buildTreeTraversal(inorder, rootIndex + 1, inEnd, postorder, postBegin + leftNum, postEnd - 1);
        return root;
    }

    /*
     * @Description  105. 从前序与中序遍历序列构造二叉树
     * @author   Edison
     * @date    2023/6/7 11:32
     * @Param   [preorder, inorder]
     * @return  second.treenode.TreeNode
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeTraversal2(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }
    TreeNode buildTreeTraversal2(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd) {
        if (preBegin >= preEnd || inBegin >= inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preBegin]);
        int rootIndex = map.get(root.val);
        int rightNum = rootIndex - inBegin;
        root.left = buildTreeTraversal2(preorder, preBegin + 1, preBegin + rightNum + 1, inorder, inBegin, rootIndex);
        root.right = buildTreeTraversal2(preorder, preBegin + rightNum + 1, preEnd, inorder, rootIndex + 1, inEnd);
        return root;
    }

    /*
     * @Description  654. 最大二叉树
     * @author   Edison
     * @date    2023/6/7 11:45
     * @Param   [nums]
     * @return  second.treenode.TreeNode
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree3(nums, 0, nums.length);
    }
    TreeNode buildTree3(int[] nums, int begin, int end) {
        if (begin >= end) {
            return null;
        }
        int rootIndex = begin;
        for (int i = begin + 1; i < end; i++) {
            if (nums[i] > nums[rootIndex]) {
                rootIndex = i;
            }
        }
        TreeNode root = new TreeNode(nums[rootIndex]);
        root.left = buildTree3(nums, begin, rootIndex);
        root.right = buildTree3(nums, rootIndex + 1, end);
        return root;
    }

    /*
     * @Description  617. 合并二叉树
     * @author   Edison
     * @date    2023/6/8 9:36
     * @Param   [root1, root2]
     * @return  second.treenode.TreeNode
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root2.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    /*
     * @Description  700. 二叉搜索树中的搜索
     * @author   Edison
     * @date    2023/6/8 9:46
     * @Param   [root, val]
     * @return  second.treenode.TreeNode
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        if (root.val > val) {
            return searchBST(root.left, val);
        } else if (root.val < val) {
            return searchBST(root.right, val);
        }
        return null;
    }

    /*
     * @Description  98. 验证二叉搜索树
     * @author   Edison
     * @date    2023/6/8 9:54
     * @Param   [root]
     * @return  boolean
     */
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        boolean left = isValidBST(root.left);
        if (pre != null && pre.val >= root.val) {
            return false;
        }
        pre = root;
        boolean right = isValidBST(root.right);
        return left && right;
    }

    /*
     * @Description  530. 二叉搜索树的最小绝对差
     * @author   Edison
     * @date    2023/6/8 10:01
     * @Param   [root]
     * @return  int
     */
    int num;
    public int getMinimumDifference(TreeNode root) {
        num = Integer.MAX_VALUE;
        getMin(root);
        return num;
    }
    void getMin(TreeNode root) {
        if (root == null) {
            return;
        }
        getMin(root.left);
        if (pre != null && root.val - pre.val < num) {
            num = root.val - pre.val;
        }
        pre = root;
        getMin(root.right);
    }

    /*
     * @Description  501. 二叉搜索树中的众数
     * @author   Edison
     * @date    2023/6/8 10:12
     * @Param   [root]
     * @return  int[]
     */
    int maxCount = 0;
    int count = 0;
    public int[] findMode(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        findMode(root, list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    void findMode(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        findMode(root.left, list);
        if (pre == null) {
            count = 1;
        } else if (pre.val == root.val) {
            count++;
        } else {
            count = 1;
        }
        pre = root;
        if (count == maxCount) {
            list.add(root.val);
        }
        if (count > maxCount) {
            list.clear();
            maxCount = count;
            list.add(root.val);
        }
        findMode(root.right, list);

    }

    /*
     * @Description  236. 二叉树的最近公共祖先
     * @author   Edison
     * @date    2023/6/8 10:29
     * @Param   [root, p, q]
     * @return  second.treenode.TreeNode
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        if (left == null) return right;
        return left;
    }

    /*
     * @Description  235. 二叉搜索树的最近公共祖先
     * @author   Edison
     * @date    2023/6/9 9:52
     * @Param   [root, p, q]
     * @return  second.treenode.TreeNode
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor1(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor1(root.right, p, q);
        return root;
    }

    /*
     * @Description  701. 二叉搜索树中的插入操作
     * @author   Edison
     * @date    2023/6/9 9:57
     * @Param   [root, val]
     * @return  second.treenode.TreeNode
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) root.left = insertIntoBST(root.left, val);
        if (root.val < val) root.right = insertIntoBST(root.right, val);
        return root;
    }

    /*
     * @Description  450. 删除二叉搜索树中的节点
     * @author   Edison
     * @date    2023/6/9 10:04
     * @Param   [root, key]
     * @return  second.treenode.TreeNode
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val == key) {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode cur = root.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            cur.left = root.left;
            root = root.right;
            return root;
        }
        if (root.val > key) root.left = deleteNode(root.left, key);
        if (root.val < key) root.right = deleteNode(root.right, key);
        return root;
    }

    /*
     * @Description  669. 修剪二叉搜索树
     * @author   Edison
     * @date    2023/6/9 10:15
     * @Param   [root, low, high]
     * @return  second.treenode.TreeNode
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) return trimBST(root.right, low, high);
        if (root.val > high) return trimBST(root.left, low, high);
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    /*
     * @Description  108. 将有序数组转换为二叉搜索树
     * @author   Edison
     * @date    2023/6/9 10:28
     * @Param   [nums]
     * @return  second.treenode.TreeNode
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortBST(nums, 0, nums.length - 1);
    }
    TreeNode sortBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortBST(nums, left, mid - 1);
        root.right = sortBST(nums, mid + 1, right);
        return root;
    }

    /*
     * @Description  538. 把二叉搜索树转换为累加树
     * @author   Edison
     * @date    2023/6/9 10:30
     * @Param   [root]
     * @return  second.treenode.TreeNode
     */
    int preVal = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        root.val += preVal;
        preVal = root.val;
        convertBST(root.left);
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Node1 {
    int val;
    List<Node1> children;

    public Node1(int val, List<Node1> children) {
        this.val = val;
        this.children = children;
    }

    public Node1(int val) {
        this.val = val;
    }

    public Node1() {
    }
}

class Node {
    int val;
    Node left;
    Node right;
    Node next;

    public Node(int val, Node left, Node right, Node next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }

    public Node(int val) {
        this.val = val;
    }

    public Node() {
    }
}
