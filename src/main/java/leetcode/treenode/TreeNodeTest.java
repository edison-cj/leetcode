package leetcode.treenode;


import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/4/11 9:59
 */
public class TreeNodeTest {

    Solution solution = new Solution();

    @Test
    public void isRobotBoundedTest() {
        String str = "RLLGLRRRRGGRRRGLLRRR";
        System.out.println(solution.isRobotBounded(str));
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

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

class NodeNext {
    public int val;
    public NodeNext left;
    public NodeNext right;
    public NodeNext next;

    public NodeNext() {}

    public NodeNext(int _val) {
        val = _val;
    }

    public NodeNext(int _val, NodeNext _left, NodeNext _right, NodeNext _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

class Solution {

    /*
     * @Description  144. 二叉树的前序遍历
     * @author   Edison
     * @date    2023/4/11 10:01
     * @Param   [root]
     * @return  List<Integer>
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        //递归
//        TraversalPreorderTraversal(root, list);

        //迭代
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (node.right != null) {
                    stack.add(node.right);
                }
                if (node.left != null) {
                    stack.add(node.left);
                }
                stack.add(node);
                stack.add(null);
            } else {
                node = stack.pop();
                list.add(node.val);
            }
        }

        return list;
    }
    void TraversalPreorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        TraversalPreorderTraversal(root.left, list);
        TraversalPreorderTraversal(root.right, list);
    }

    /*
     * @Description  145. 二叉树的后序遍历
     * @author   Edison
     * @date    2023/4/11 10:06
     * @Param   [root]
     * @return  java.util.List<java.lang.Integer>
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        //递归
//        TraversalPostorder(root, list);

        //迭代
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                stack.add(node);
                stack.add(null);
                if (node.right != null) {
                    stack.add(node.right);
                }
                if (node.left != null) {
                    stack.add(node.left);
                }
            } else {
                node = stack.pop();
                list.add(node.val);
            }
        }

        return list;
    }
    void TraversalPostorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        TraversalPostorder(root.left, list);
        TraversalPostorder(root.right, list);
        list.add(root.val);
    }

    /*
     * @Description  94. 二叉树的中序遍历
     * @author   Edison
     * @date    2023/4/11 10:10
     * @Param   [root]
     * @return  java.util.List<java.lang.Integer>
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        //递归
//        TraversalInorder(root, list);

        //迭代
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (node.right != null) {
                    stack.add(node.right);
                }
                stack.add(node);
                stack.add(null);
                if (node.left != null) {
                    stack.add(node.left);
                }
            } else {
                node = stack.pop();
                list.add(node.val);
            }
        }

        return list;
    }
    void TraversalInorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        TraversalInorder(root.left, list);
        list.add(root.val);
        TraversalInorder(root.right, list);
    }

    /*
     * @Description  102. 二叉树的层序遍历
     * @author   Edison
     * @date    2023/4/11 10:48
     * @Param   [root]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        //迭代
//        if (root == null) {
//            return result;
//        }
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            List<Integer> list = new ArrayList<>();
//            while (size > 0) {
//                TreeNode node = queue.poll();
//                size--;
//                list.add(node.val);
//                if (node.left != null) {
//                    queue.add(node.left);
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                }
//            }
//            result.add(list);
//        }

        //递归
        levelOrderTraversal(root, result, 0);

        return result;
    }
    void levelOrderTraversal(TreeNode root, List<List<Integer>> result, int deep) {
        if (root == null) {
            return;
        }
        deep++;

        if (result.size() < deep) {
            List<Integer> list = new ArrayList<>();
            result.add(list);
        }
        result.get(deep - 1).add(root.val);
        levelOrderTraversal(root.left, result, deep);
        levelOrderTraversal(root.right, result, deep);
    }

    /*
     * @Description  1041. 困于环中的机器人
     * @author   Edison
     * @date    2023/4/11 11:12
     * @Param   [instructions]
     * @return  boolean
     */
    public boolean isRobotBounded(String instructions) {
        int d = 1;
        int x = 0;
        int y = 0;
        for (int i = 0; i < 4; i++) {
            for (char ch : instructions.toCharArray()) {
                if (ch == 'L') {
                    d += 1;
                } else if (ch == 'R') {
                    d -= 1;
                } else {
                    if (d % 4 == 1 || d % 4 == -3) {
                        y++;
                    } else if (d % 4 == 3 || d % 4 == -1) {
                        y--;
                    } else if (d % 4 == 0) {
                        x++;
                    } else {
                        x--;
                    }

                }
            }
        }
        return x == 0 && y == 0;
    }

    /*
     * @Description  199. 二叉树的右视图
     * @author   Edison
     * @date    2023/4/12 10:44
     * @Param   [root]
     * @return  java.util.List<java.lang.Integer>
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (size == 0) {
                    result.add(node.val);
                }
            }
        }
        return result;
    }

    /*
     * @Description  637. 二叉树的层平均值
     * @author   Edison
     * @date    2023/4/12 10:55
     * @Param   [root]
     * @return  java.util.List<java.lang.Double>
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = size;
            double sum = 0;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                sum += node.val;
            }
            list.add(sum / count);
        }
        return list;
    }

    /*
     * @Description  429. N 叉树的层序遍历
     * @author   Edison
     * @date    2023/4/12 11:14
     * @Param   [root]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                Node node = queue.poll();
                for (Node child : node.children) {
                    queue.add(child);
                }
                list.add(node.val);
            }
            res.add(list);
        }
        return res;
    }

    /*
     * @Description  515. 在每个树行中找最大值
     * @author   Edison
     * @date    2023/4/12 11:21
     * @Param   [root]
     * @return  java.util.List<java.lang.Integer>
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxNum = queue.peek().val;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.val > maxNum) {
                    maxNum = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(maxNum);
        }
        return list;
    }

    /*
     * @Description  116. 填充每个节点的下一个右侧节点指针
     * @author   Edison
     * @date    2023/4/12 11:43
     * @Param   [root]
     * @return  leetcode.treenode.NodeNext
     */
    public NodeNext connect(NodeNext root) {
        if (root == null) {
            return root;
        }
        Queue<NodeNext> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                NodeNext node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (size != 0) {
                    node.next = queue.peek();
                }
            }
        }
        return root;
    }

    /*
     * @Description  117. 填充每个节点的下一个右侧节点指针 II
     * @author   Edison
     * @date    2023/4/12 11:43
     * @Param   [root]
     * @return  leetcode.treenode.NodeNext
     */
    public NodeNext connect2(NodeNext root) {
        if (root == null) {
            return null;
        }
        Queue<NodeNext> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                NodeNext node = queue.poll();
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
     * @Description  111. 二叉树的最小深度
     * @author   Edison
     * @date    2023/4/12 11:54
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
     * @date    2023/4/14 12:20
     * @Param   [root]
     * @return  leetcode.treenode.TreeNode
     */
    public TreeNode invertTree(TreeNode root) {
        //递归
//        invertTreeTraversal(root);

        //迭代
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return root;
    }
    void invertTreeTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        invertTreeTraversal(root.left);
        invertTreeTraversal(root.right);
    }

    /*
     * @Description  101. 对称二叉树
     * @author   Edison
     * @date    2023/4/14 12:35
     * @Param   [root]
     * @return  boolean
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        //递归
//        return isSymmetricTraversal(root.left, root.right);

        //迭代
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            queue.add(node1.left);
            queue.add(node2.right);
            queue.add(node1.right);
            queue.add(node2.left);
        }
        return true;
    }
    boolean isSymmetricTraversal(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        }
        boolean outSide = isSymmetricTraversal(left.left, right.right);
        boolean inSide = isSymmetricTraversal(left.right, right.left);
        return inSide && outSide;
    }

    /*
     * @Description  100. 相同的树
     * @author   Edison
     * @date    2023/4/14 12:57
     * @Param   [p, q]
     * @return  boolean
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return isSameTreeTraversal(p, q);
    }
    boolean isSameTreeTraversal(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null && q != null) {
            return false;
        } else if (p != null && q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        boolean left = isSameTreeTraversal(p.left, q.left);
        boolean right = isSameTreeTraversal(p.right, q.right);
        return left && right;
    }

    /*
     * @Description  572. 另一棵树的子树
     * @author   Edison
     * @date    2023/4/14 13:12
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
        return isSubtreeTraversal(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
    boolean isSubtreeTraversal(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        } else if (root == null || subRoot == null || root.val != subRoot.val) {
            return false;
        } else {
            return isSubtreeTraversal(root.left, subRoot.left) && isSubtreeTraversal(root.right, subRoot.right);
        }
    }

    /*
     * @Description  104. 二叉树的最大深度
     * @author   Edison
     * @date    2023/4/14 13:12
     * @Param   [root]
     * @return  int
     */
    public int maxDepth(TreeNode root) {
        return getMaxDepth(root);
    }
    int getMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getMaxDepth(root.left);
        int right = getMaxDepth(root.right);
        return 1 + Math.max(left, right);
    }

    /*
     * @Description  559. N 叉树的最大深度
     * @author   Edison
     * @date    2023/4/14 13:17
     * @Param   [root]
     * @return  int
     */
    public int maxDepth(Node root) {
        return getNdepth(root);
    }
    int getNdepth(Node root) {
        int depth = 0;
        if (root == null) {
            return 0;
        }
        for (Node child : root.children) {
            depth = Math.max(depth, getNdepth(child));
        }
        return depth + 1;
    }

    /*
     * @Description  222. 完全二叉树的节点个数
     * @author   Edison
     * @date    2023/4/14 13:43
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
     * @date    2023/4/14 13:49
     * @Param   [root]
     * @return  boolean
     */
    public boolean isBalanced(TreeNode root) {
        return getHigh(root) == -1 ? false : true;
    }
    int getHigh(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHigh(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getHigh(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;
    }

    /*
     * @Description  257. 二叉树的所有路径
     * @author   Edison
     * @date    2023/4/14 14:04
     * @Param   [root]
     * @return  java.util.List<java.lang.String>
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> path = new ArrayList<>();
        findPath(list, root, path);
        return list;
    }
    void findPath(List<String> list, TreeNode root, List<Integer> path) {
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size() - 1));
            list.add(new String(sb));
            return;
        }
        path.add(root.val);
        if (root.left != null) {
            findPath(list, root.left, path);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            findPath(list, root.right, path);
            path.remove(path.size() - 1);
        }
    }

    /*
     * @Description  404. 左叶子之和
     * @author   Edison
     * @date    2023/4/17 10:51
     * @Param   [root]
     * @return  int
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int value = 0;

        //递归
//        if (root.left != null && root.left.left == null && root.left.right == null) {
//           value +=  root.left.val;
//        }
//        return value + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);

        //迭代
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null && node.left.left == null && node.left.right == null) {
                value += node.left.val;
            }
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return value;
    }

    /*
     * @Description  513. 找树左下角的值
     * @author   Edison
     * @date    2023/4/17 11:41
     * @Param   [root]
     * @return  int
     */
    int val = 0;
    int deep = -1;
    public int findBottomLeftValue(TreeNode root) {
//        findLeftValue(root, 0);

        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    val = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return val;
    }
    void findLeftValue(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (depth > deep) {
                val = root.val;
                deep = depth;
            }
        }
        if (root.left != null) findLeftValue(root.left, depth + 1);
        if (root.right != null) findLeftValue(root.right, depth + 1);
    }

    /*
     * @Description  112. 路径总和
     * @author   Edison
     * @date    2023/4/17 15:11
     * @Param   [root, targetSum]
     * @return  boolean
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return findPathSum(root, targetSum);
    }
    boolean findPathSum(TreeNode root, int target) {
        if (root.left == null && root.right == null && target == 0) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return false;
        }
        target -= root.val;
        if (root.left != null) {
            if (findPathSum(root.left, target - root.left.val)) {
                return true;
            }
        }
        if (root.right != null) {
            if (findPathSum(root.right, target - root.right.val)) {
                return true;
            }
        }
        return false;
    }

    /*
     * @Description  113. 路径总和 II
     * @author   Edison
     * @date    2023/4/17 15:26
     * @Param   [root, targetSum]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (root == null) {
            return list;
        }
        path.add(root.val);
        pathSumTraversal(root, targetSum - root.val, path, list);
        return list;
    }
    void pathSumTraversal(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> list) {
        if (root.left == null && root.right == null) {
            if (targetSum == 0) {
                list.add(new ArrayList<>(path));
            }
            return;
        }
        if (root.left != null) {
            path.add(root.left.val);
            pathSumTraversal(root.left, targetSum - root.left.val, path, list);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.right.val);
            pathSumTraversal(root.right, targetSum - root.right.val, path, list);
            path.remove(path.size() - 1);
        }
    }

    /*
     * @Description  106. 从中序与后序遍历序列构造二叉树
     * @author   Edison
     * @date    2023/4/17 15:46
     * @Param   [inorder, postorder]
     * @return  leetcode.treenode.TreeNode
     */
    Map<Integer,Integer> map;
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
     * @Description  654. 最大二叉树
     * @author   Edison
     * @date    2023/4/17 16:41
     * @Param   [nums]
     * @return  leetcode.treenode.TreeNode
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return findTree(nums, 0, nums.length);
    }
    TreeNode findTree(int[] nums, int begin, int end) {
        if (begin >= end) {
            return null;
        }
        int index = begin;
        for (int i = begin + 1; i < end; i++) {
            if (nums[i] > nums[index]) {
                index = i;
            }
        }
        TreeNode root = new TreeNode(nums[index]);
        root.left = findTree(nums, begin, index);
        root.right = findTree(nums, index + 1, end);
        return root;
    }

    /*
     * @Description  617. 合并二叉树
     * @author   Edison
     * @date    2023/4/18 10:32
     * @Param   [root1, root2]
     * @return  leetcode.treenode.TreeNode
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return mergeTreesTraversal(root1, root2);
    }
    TreeNode mergeTreesTraversal(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val += root2.val;

        root1.left = mergeTreesTraversal(root1.left, root2.left);
        root1.right = mergeTreesTraversal(root1.right, root2.right);
        return root1;
    }

    /*
     * @Description  700. 二叉搜索树中的搜索
     * @author   Edison
     * @date    2023/4/18 10:45
     * @Param   [root, val]
     * @return  leetcode.treenode.TreeNode
     */
    public TreeNode searchBST(TreeNode root, int val) {
        return BSTTraversal(root, val);
    }
    TreeNode BSTTraversal(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        TreeNode res = null;
        if (root.val > val) {
            res = BSTTraversal(root.left, val);
        }
        if (root.val < val) {
            res = BSTTraversal(root.right, val);
        }
        return res;
    }

    /*
     * @Description  98. 验证二叉搜索树
     * @author   Edison
     * @date    2023/4/18 11:09
     * @Param   [root]
     * @return  boolean
     */
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
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
     * @date    2023/4/18 14:32
     * @Param   [root]
     * @return  int
     */
    int res = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
//        Traversal(root);
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.add(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre != null) {
                    res = Math.min(res, root.val - pre.val);
                }
                pre = root;
                root = root.right;
            }
        }
        return res;
    }
    void Traversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Traversal(root.left);
        if (pre != null) {
            res = Math.min(res, root.val - pre.val);
        }
        pre = root;
        Traversal(root.right);
    }

    /*
     * @Description  501. 二叉搜索树中的众数
     * @author   Edison
     * @date    2023/4/18 14:43
     * @Param   [root]
     * @return  int[]
     */
    int maxCount = 0;
    int count = 0;
    List<Integer> cur = new ArrayList<>();
    public int[] findMode(TreeNode root) {
//        findModeTraversal(root);
        if (root == null) {
            return new int[0];
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.add(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre == null || root.val != pre.val) {
                    count = 1;
                } else {
                    count++;
                }
                if (count == maxCount) {
                    cur.add(root.val);
                }
                if (count > maxCount) {
                    maxCount = count;
                    cur.clear();
                    cur.add(root.val);
                }
                pre = root;
                root = root.right;
            }
        }
        int[] res = new int[cur.size()];
        int k = 0;
        for (int num : cur) {
            res[k++] = num;
        }
        return res;
    }
    void findModeTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        findModeTraversal(root.left);
        if (pre == null) {
            count = 1;
        } else if (pre.val == root.val){
            count++;
        } else {
            count = 1;
        }
        pre = root;
        if (count == maxCount) {
            cur.add(root.val);
        }
        if (count > maxCount) {
            maxCount = count;
            cur.clear();
            cur.add(root.val);
        }
        findModeTraversal(root.right);
    }

    /*
     * @Description  236. 二叉树的最近公共祖先
     * @author   Edison
     * @date    2023/4/18 15:07
     * @Param   [root, p, q]
     * @return  leetcode.treenode.TreeNode
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root == null) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left == null) {
            return right;
        }
        return left;
    }

    /*
     * @Description  235. 二叉搜索树的最近公共祖先
     * @author   Edison
     * @date    2023/4/19 10:21
     * @Param   [root, p, q]
     * @return  leetcode.treenode.TreeNode
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor1(root.left, p, q);
        if (root.val < q.val && root.val < q.val) return lowestCommonAncestor1(root.right, p, q);
        while (true) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                break;
            }
        }
        return root;
    }

    /*
     * @Description  701. 二叉搜索树中的插入操作
     * @author   Edison
     * @date    2023/4/19 10:41
     * @Param   [root, val]
     * @return  leetcode.treenode.TreeNode
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }else if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    /*
     * @Description  450. 删除二叉搜索树中的节点
     * @author   Edison
     * @date    2023/4/19 10:52
     * @Param   [root, key]
     * @return  leetcode.treenode.TreeNode
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode temp = root.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            temp.left = root.left;
            root = root.right;
            return root;
        }
        return root;
    }

    /*
     * @Description  669. 修剪二叉搜索树
     * @author   Edison
     * @date    2023/4/19 11:24
     * @Param   [root, low, high]
     * @return  leetcode.treenode.TreeNode
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

//        if (root.val < low) {
//            return trimBST(root.right, low, high);
//        }
//        if (root.val > high) {
//            return trimBST(root.left, low, high);
//        }
//        root.left = trimBST(root.left, low, high);
//        root.right = trimBST(root.right, low, high);

        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {
                root = root.right;
            }
            if (root.val > high) {
                root = root.left;
            }
        }
        TreeNode cur = root;
        while (cur != null) {
            while (cur.left != null && cur.left.val < low) {
                cur.left = cur.left.right;
            }
            cur = cur.left;
        }
        cur = root;

        while (cur != null) {
            while (cur.right != null && cur.right.val > high) {
                cur.right = cur.right.left;
            }
            cur = cur.right;
        }

        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedToBST(nums, 0, nums.length - 1);
    }
    TreeNode sortedToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedToBST(nums, left, mid - 1);
        root.right = sortedToBST(nums, mid + 1, right);
        return root;
    }

    /*
     * @Description  538. 把二叉搜索树转换为累加树
     * @author   Edison
     * @date    2023/4/19 14:40
     * @Param   [root]
     * @return  leetcode.treenode.TreeNode
     */
    int preVal;
    public TreeNode convertBST(TreeNode root) {
        preVal = 0;
//        convertBSTTraversal(root);
        if (root == null) {
            return root;
        }
        TreeNode res = root;
        Stack<TreeNode> stack = new Stack<>();
        while (res != null || !stack.isEmpty()) {
            if (res != null) {
                stack.add(res);
                res = res.right;
            } else {
                res = stack.pop();
                res.val += preVal;
                preVal = res.val;
                res = res.left;
            }
        }
        return root;
    }
    void convertBSTTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        convertBSTTraversal(root.right);
        root.val += preVal;
        preVal = root.val;
        convertBSTTraversal(root.left);
    }

    /*
     * @Description  1038. 从二叉搜索树到更大和树
     * @author   Edison
     * @date    2023/4/19 14:59
     * @Param   [root]
     * @return  leetcode.treenode.TreeNode
     */
    public TreeNode bstToGst(TreeNode root) {
        preVal = 0;
        bstToGstTraversal(root);
        return root;
    }
    void bstToGstTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        bstToGstTraversal(root.right);
        root.val += preVal;
        preVal = root.val;
        bstToGstTraversal(root.left);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int val = l1Val + l2Val + carry;
            carry = val / 10;
            ListNode temp = new ListNode(val % 10);
            cur.next = temp;
            cur = temp;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
