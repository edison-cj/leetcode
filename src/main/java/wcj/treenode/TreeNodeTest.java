package wcj.treenode;

import org.testng.annotations.Test;

import javax.swing.*;
import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/3/7 15:45
 */
public class TreeNodeTest {

    Solution solution = new Solution();

    @Test
    public void preorderTraversalTest() {

    }
}

/*
 * @Description  二叉树
 * @author   Edison
 * @date    2023/3/8 16:39
 * @Param   
 * @return  
 */
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

/*
 * @Description  N叉树
 * @author   Edison
 * @date    2023/3/8 16:39
 * @Param   
 * @return  
 */
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

/*
 * @Description  填充节点的下一个右侧节点指针 Definition for a Node.
 * @author   Edison
 * @date    2023/3/8 16:56
 * @Param
 * @return  
 */
class NodeOne {
    public int val;
    public NodeOne left;
    public NodeOne right;
    public NodeOne next;

    public NodeOne() {}

    public NodeOne(int _val) {
        val = _val;
    }

    public NodeOne(int _val, NodeOne _left, NodeOne _right, NodeOne _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

class Solution {

    /*
     * @Description  144. 二叉树的前序遍历
     * @author   Edison
     * @date    2023/3/7 15:46
     * @Param   [root]
     * @return  List<Integer>
     */
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        //递归
        //Preorder(root, list);

        //迭代
//        Stack<TreeNode> stack = new Stack<>();
//        if (root == null) return list;
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            list.add(node.val);
//            if (node.right != null) {
//                stack.push(node.right);
//            }
//            if (node.left != null) {
//                stack.push(node.left);
//            }
//        }

        //统一迭代
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                stack.pop();
                if (node.right != null) stack.push(node.right);  //右
                if (node.left != null) stack.push(node.left); //左
                stack.push(node); //中
                stack.push(null);
            } else {
                stack.pop();
                node = stack.pop();
                list.add(node.val);
            }
        }

        return list;
    }
    void Preorder(TreeNode cur, List<Integer> vec) {
        if (cur == null) {
            return;
        }
        vec.add(cur.val);
        Preorder(cur.left, vec);
        Preorder(cur.right, vec);
    }

    /*
     * @Description  145. 二叉树的后序遍历
     * @author   Edison
     * @date    2023/3/7 16:05
     * @Param   [root]
     * @return  java.util.List<java.lang.Integer>
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        //递归
//        postorder(root, list);
        //迭代
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            list.add(node.val);
//            if (node.left != null) {
//                stack.push(node.left);
//            }
//            if (node.right != null) {
//                stack.push(node.right);
//            }
//        }
//        Collections.reverse(list);

        //统一迭代
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                stack.pop();
                stack.push(node); //中
                stack.push(null);
                if (node.right != null) stack.push(node.right);  //右
                if (node.left != null) stack.push(node.left); //左
            } else {
                stack.pop();   //弹出null
                node = stack.pop();
                list.add(node.val);
            }
        }

        return list;
    }
    void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }

    /*
     * @Description  94. 二叉树的中序遍历
     * @author   Edison
     * @date    2023/3/7 16:08
     * @Param   [root]
     * @return  java.util.List<java.lang.Integer>
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        //递归
//        inorder(root, list);

        //迭代
//        Stack<TreeNode> stack = new Stack<>();
//        TreeNode cur = root;
//        while (cur != null || !stack.isEmpty()) {
//           if (cur != null) {
//               stack.push(cur);
//               cur = cur.left;
//           } else {
//               cur = stack.pop();
//               list.add(cur.val);
//               cur = cur.right;
//           }
//        }

        //统一迭代
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                stack.pop();
                if (node.right != null) stack.push(node.right);  //右
                stack.push(node); //中
                stack.push(null);
                if (node.left != null) stack.push(node.left); //左
            } else {
                stack.pop();
                node = stack.pop();
                list.add(node.val);
            }
        }

        return list;
    }
    void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }


    /*
     * @Description  102. 二叉树的层序遍历
     * @author   Edison
     * @date    2023/3/8 14:21
     * @Param   [root]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */

    List<List<Integer>> levelOrderList = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {

//        //队列的迭代
//        List<List<Integer>> list = new ArrayList<>();
//        Queue<TreeNode> queue = new LinkedList<>();
//        if (root != null) queue.add(root);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            List<Integer> vec = new ArrayList<>();
//            while (size-- > 0) {
//                TreeNode node = queue.poll();
//                vec.add(node.val);
//                if (node.left != null) queue.add(node.left);
//                if (node.right != null) queue.add(node.right);
//            }
//            list.add(vec);
//        }
//        return list;

        //递归
        checkFun01(root, 0);
        return levelOrderList;
    }

    public void checkFun01(TreeNode node, Integer deep) {
        if (node == null) return;
        deep++;

        if (levelOrderList.size() < deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> item = new ArrayList<Integer>();
            levelOrderList.add(item);
        }
        levelOrderList.get(deep - 1).add(node.val);

        checkFun01(node.left, deep);
        checkFun01(node.right, deep);
    }

    /*
     * @Description  107. 二叉树的层序遍历 II
     * @author   Edison
     * @date    2023/3/8 16:02
     * @Param
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return list;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> vec = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();

                vec.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            list.add(vec);
        }
        Collections.reverse(list);
        return list;
    }
    
    /*
     * @Description  199. 二叉树的右视图
     * @author   Edison
     * @date    2023/3/8 16:12
     * @Param
     * @return  
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return list;
        queue.offer(root);
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
                    list.add(node.val);
                }
            }
        }
        return list;
    }
    
    /*
     * @Description  637. 二叉树的层平均值
     * @author   Edison
     * @date    2023/3/8 16:29
     * @Param
     * @return  
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);
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
            list.add(sum/size);
        }
        return list;
    }
    
    /*
     * @Description  429. N 叉树的层序遍历
     * @author   Edison
     * @date    2023/3/8 16:40
     * @Param
     * @return  
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> vec = new ArrayList<>();
            while (size-- > 0) {
                Node node = queue.poll();
                vec.add(node.val);
                if (node.children != null) {
                    for (Node child : node.children) {
                        queue.add(child);
                    }
                }
            }
            list.add(vec);
        }

        return list;
    }
    
    /*
     * @Description  515. 在每个树行中找最大值
     * @author   Edison
     * @date    2023/3/8 16:49
     * @Param
     * @return  
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return  list;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxNum = queue.peek().val;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (maxNum < node.val) {
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
     * @date    2023/3/8 16:54
     * @Param
     * @return  
     */
    public NodeOne connect(NodeOne root) {
        Queue<NodeOne> queue = new LinkedList<>();
        if (root == null) {
            return  null;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            NodeOne cur = queue.poll();
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
            for (int i = 1; i < size; i++) {
                NodeOne next = queue.poll();
                if (next.left != null) queue.add(next.left);
                if (next.right != null) queue.add(next.right);
                cur.next = next;
                cur = next;
            }
        }
        return root;
    }
    
    /*
     * @Description  117.填充每个节点的下一个右侧节点指针II
     * @author   Edison
     * @date    2023/3/8 17:08
     * @Param
     * @return  
     */
    public NodeOne connect1(NodeOne root) {
        Queue<NodeOne> queue = new LinkedList<>();
        if (root == null) {
            return null;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            NodeOne cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
            for (int i = 1; i < size; i++) {
                NodeOne next = queue.poll();
                if (next.left != null) {
                    queue.add(next.left);
                }
                if (next.right != null) {
                    queue.add(next.right);
                }
                cur.next = next;
                cur = next;
            }
        }
        return root;
    }
    
    /*
     * @Description  104.二叉树的最大深度
     * @author   Edison
     * @date    2023/3/8 17:25
     * @Param
     * @return  
     */
    public int maxDepth(TreeNode root) {

        //迭代
//        Queue<TreeNode> queue = new LinkedList<>();
//        int deep = 0;
//        if (root == null) {
//            return deep;
//        }
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            while (size-- > 0) {
//                TreeNode node = queue.poll();
//                if (node.left != null) {
//                    queue.add(node.left);
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                }
//            }
//            deep++;
//        }
//        return deep;

        //递归
        return getDepth(root);
    }

    int getDepth(TreeNode root) {
        int depth;
        if (root == null) return 0;
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        depth = 1 + Math.max(leftDepth, rightDepth);
        return depth;
    }

    /*
     * @Description  111. 二叉树的最小深度
     * @author   Edison
     * @date    2023/3/8 19:46
     * @Param   [root]
     * @return  int
     */
    public int minDepth(TreeNode root) {

        //迭代
//        int min = 0;
//        Queue<TreeNode> queue = new LinkedList<>();
//        if (root == null) {
//            return min;
//        }
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            min++;
//            while (size-- > 0) {
//                TreeNode node = queue.poll();
//                if (node.left != null) {
//                    queue.add(node.left);
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                }
//                if (node.right == null && node.left == null) {
//                    return min;
//                }
//            }
//        }
//        return min;

        //递归
        //递归
        if (root == null) {
            return 0;
        }
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        if (root.left == null) {
            return 1 + rightDepth;
        }
        if (root.right == null) {
            return 1 + leftDepth;
        }

        return 1 + Math.min(leftDepth, rightDepth);
    }
    
    /*
     * @Description  226. 翻转二叉树
        输入：root = [4,2,7,1,3,6,9]
        输出：[4,7,2,9,6,3,1]
     * @author   Edison
     * @date    2023/3/8 19:57
     * @Param
     * @return  
     */
    public TreeNode invertTree(TreeNode root) {
        //迭代
//        Queue<TreeNode> queue = new LinkedList<>();
//        if (root == null) {
//            return root;
//        }
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            while (size-- > 0) {
//                TreeNode node = queue.poll();
//                TreeNode temp = node.left;
//                node.left = node.right;
//                node.right = temp;
//                if (node.left != null) {
//                    queue.add(node.left);
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                }
//            }
//        }
        //递归
        if (root == null) {
            return root;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    /*
     * @Description  589. N 叉树的前序遍历
     * @author   Edison
     * @date    2023/3/8 20:25
     * @Param
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();

        //迭代
//        Stack<Node> stack = new Stack<>();
//        if (root == null) {
//            return list;
//        }
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            Node node = stack.pop();
//            if (node != null) {
//                if (node.children != null) {
//                    for (int i = node.children.size() - 1; i >= 0; i--) {
//                        stack.push(node.children.get(i));
//                    }
//                }
//                stack.push(node);
//                stack.push(null);
//            } else {
//                node = stack.pop();
//                list.add(node.val);
//            }
//        }

        //递归
        nTreePreorder(root, list);

        return list;
    }

    void nTreePreorder(Node cur, List<Integer> list) {
        if (cur == null) {
            return;
        }
        list.add(cur.val);
        for (Node child : cur.children) {
            nTreePreorder(child, list);
        }
    }

    /*
     * @Description  590. N 叉树的后序遍历
     * @author   Edison
     * @date    2023/3/8 20:50
     * @Param
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();

        //递归
//        nTreePostorder(root, list);

        //迭代
        Stack<Node> stack = new Stack<>();
        if (root == null) {
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node != null) {
                stack.push(node);
                stack.push(null);
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    stack.push(node.children.get(i));
                }
            } else {
                node = stack.pop();
                list.add(node.val);
            }
        }

        return list;
    }

    void nTreePostorder(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        for (Node child : root.children) {
            nTreePostorder(child, list);
        }
        list.add(root.val);
    }
    
    /*
     * @Description  101. 对称二叉树
     * @author   Edison
     * @date    2023/3/10 13:12
     * @Param
     * @return  
     */
    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return true;
        }

        //递归
        //return compareSymmetric(root.left, root.right);

        //迭代
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode leftNode = queue.poll();
            TreeNode rightNode = queue.poll();
            if (leftNode == null && rightNode == null) {
                continue;
            }
            if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                return false;
            }

            queue.add(leftNode.left);
            queue.add(rightNode.right);
            queue.add(leftNode.right);
            queue.add(rightNode.left);
        }

        return true;
    }

    boolean compareSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && right == null) {
            return true;
        } else if (left.val != right.val) {
            return false;
        }

        boolean outside = compareSymmetric(left.left, right.right);
        boolean inside = compareSymmetric(left.right, right.left);
        return outside && inside;
    }

    /*
     * @Description  100. 相同的树
     * @author   Edison
     * @date    2023/3/10 14:12
     * @Param
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //递归
        //return compareSameTree(p, q);

        //迭代
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            TreeNode leftNode = queue.poll();
            TreeNode rightNode = queue.poll();
            if (leftNode == null && rightNode == null) {
                continue;
            }
            if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                return false;
            }
            queue.add(leftNode.left);
            queue.add(rightNode.left);
            queue.add(leftNode.right);
            queue.add(rightNode.right);
        }

        return true;
    }

    boolean compareSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null || p.val != q.val) {
            return false;
        } else {
            boolean leftSide = compareSameTree(p.left, q.left);
            boolean rightSide = compareSameTree(p.right, q.right);
            return leftSide && rightSide;
        }
    }

    /*
     * @Description  572. 另一棵树的子树
     * @author   Edison
     * @date    2023/3/10 14:25
     * @Param
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return compareSubtree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    boolean compareSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        } else if (root == null || subRoot == null || root.val != subRoot.val) {
            return false;
        } else {
            boolean leftNode = compareSubtree(root.left, subRoot.left);
            boolean rightNode = compareSubtree(root.right, subRoot.right);
            return leftNode && rightNode;
        }
    }

    /*
     * @Description  559. N 叉树的最大深度
     * @author   Edison
     * @date    2023/3/10 15:20
     * @Param
     * @return
     */
    public int maxDepth(Node root) {
        return getNtreeDepth(root);
    }

    int getNtreeDepth(Node root) {
        int depth = 0;
        if (root == null) {
            return 0;
        }
        int[] numDepth = new int[root.children.size()];
        for (int i = 0; i < root.children.size(); i++) {
            depth = Math.max(depth, getNtreeDepth(root.children.get(i)));
        }

        return depth + 1;
    }

    /*
     * @Description  222. 完全二叉树的节点个数
     * @author   Edison
     * @date    2023/3/10 17:26
     * @Param
     * @return
     */
    public int countNodes(TreeNode root) {

        //迭代
//        if (root == null) {
//            return 0;
//        }
//        int num = 0;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            num += size;
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
//        return num;

        //递归
//        return getNumNodes(root);

        if (root == null) {
            return 0;
        }

        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        int leftDepth = 0;
        int rightDepth = 0;
        while (leftNode != null) {
            leftNode = leftNode.left;
            leftDepth++;
        }
        while (rightNode != null) {
            rightDepth++;
            rightNode = rightNode.right;
        }
        if (rightDepth == leftDepth) {
            return 2 << leftDepth + 1;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /*
     * @Description  110. 平衡二叉树
     * @author   Edison
     * @date    2023/3/10 19:44
     * @Param
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (leftHeight == -1) return -1;
        if (rightHeight == -1) return -1;
        return Math.abs(leftHeight - rightHeight) > 1 ? -1 : 1 + Math.max(leftHeight,rightHeight);

    }
    
    /*
     * @Description  257. 二叉树的所有路径
     * @author   Edison
     * @date    2023/3/13 13:48
     * @Param
     * @return  
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        //递归
//        List<Integer> paths = new ArrayList<>();
//        traverTreePath(root, paths, list);

        //迭代
        Stack<Object> stack = new Stack<>();
        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            if (node.left == null && node.right == null) {
                list.add(path);
            }
            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }
            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
        }

        return list;
    }

    void traverTreePath(TreeNode root, List<Integer> paths, List<String> list) {
        paths.add(root.val);
        if (root.left == null && root.right == null) {
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < paths.size() - 1; i++) {
                str.append(paths.get(i)).append("->");
            }
            str.append(paths.get(paths.size() - 1));
            list.add(str.toString());
            return;
        }
        if (root.left != null) {
            traverTreePath(root.left, paths, list);
            paths.remove(paths.size() - 1);
        }
        if (root.right != null) {
            traverTreePath(root.right, paths, list);
            paths.remove(paths.size() - 1);
        }
    }

    /*
     * @Description  404. 左叶子之和
     * @author   Edison
     * @date    2023/3/13 16:36
     * @Param
     * @return
     */

    public int sumOfLeftLeaves(TreeNode root) {
        //递归
//        return LeftLeaves(root);

        //迭代
        int val = 0;
        if (root == null) {
            return val;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null && node.left.left == null && node.left.right == null) {
                val += node.left.val;
            }else if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return val;
    }

    int LeftLeaves(TreeNode root) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }

        res += LeftLeaves(root.left);
        if (root.left != null && root.left.left == null && root.left.right == null) {
            res = root.left.val;
        }
        res += LeftLeaves(root.right);

        return res;
    }

    /*
     * @Description  513. 找树左下角的值
     * @author   Edison
     * @date    2023/3/13 17:10
     * @Param
     * @return
     */
    int deep = 0;
    int value;
    public int findBottomLeftValue(TreeNode root) {

        //递归
//        BottomLeftValue(root, deep);

        //迭代
        if (root == null) {
            return value;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    value = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return value;
    }

    void BottomLeftValue(TreeNode root, int Deep) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (deep < Deep) {
                value = root.val;
                deep = Deep;
            }
        }
        if (root.left != null) {
            BottomLeftValue(root.left, Deep + 1);
        }
        if (root.right != null) {
            BottomLeftValue(root.right, Deep + 1);
        }
    }
    
    /*
     * @Description  112. 路径总和
     * 给你二叉树的根节点root 和一个表示目标和的整数targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum 。
     * 如果存在，返回 true ；否则，返回 false 。
     * 叶子节点 是指没有子节点的节点。
     * @author   Edison
     * @date    2023/3/13 19:07
     * @Param
     * @return  
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        //递归
//        if (root.left == null && root.right == null && root.val == targetSum) {
//            return true;
//        }
//
//        boolean leftNode = hasPathSum(root.left, targetSum - root.val);
//        boolean rightNode = hasPathSum(root.right, targetSum - root.val);
//        return leftNode || rightNode;

        //迭代
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack1.push(root);
        stack2.push(root.val);
        while (!stack1.isEmpty()) {
            int size = stack1.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = stack1.pop();
                int sum = stack2.pop();
                if (node.left == null && node.right == null && sum == targetSum) {
                    return true;
                }
                if (node.right != null) {
                    stack1.push(node.right);
                    stack2.push(sum + node.right.val);
                }
                if (node.left != null) {
                    stack1.push(node.left);
                    stack2.push(sum + node.left.val);
                }
            }
        }
        return false;
    }

    /*
     * @Description  113. 路径总和 II
     * @author   Edison
     * @date    2023/3/14 9:51
     * @Param
     * @return
     */

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return result;
        }

        return result;
    }

    void traversalPathSum(TreeNode root, int sum, List<List<Integer>> result, List<Integer> path) {
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (root.val - sum == 0) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        if (root.left != null) {
            traversalPathSum(root.left, sum - root.val, result, path);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            traversalPathSum(root.right, sum - root.val, result, path);
            path.remove(path.size() - 1);
        }

    }
    
    /*
     * @Description  106. 从中序与后序遍历序列构造二叉树
     * @author   Edison
     * @date    2023/3/14 10:32
     * @Param
     * @return  
     */
    Map<Integer, Integer> map;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return traversalBuildTree(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    TreeNode traversalBuildTree(int[] inorder, int inBegin, int inEnd,int[] postorder, int postBegin, int postEnd) {
        if (inBegin >= inEnd || postBegin >= postEnd) {
            return null;
        }
        int rootIndex = map.get(postorder[postEnd - 1]);
        TreeNode root = new TreeNode(inorder[rootIndex]);
        int lenOfLeft = rootIndex - inBegin;
        root.left = traversalBuildTree(inorder, inBegin, rootIndex, postorder, postBegin, postBegin + lenOfLeft);
        root.right = traversalBuildTree(inorder, rootIndex + 1, inEnd, postorder, lenOfLeft + postBegin, postEnd - 1);
        return  root;
    }

    /*
     * @Description  105. 从前序与中序遍历序列构造二叉树
     * @author   Edison
     * @date    2023/3/14 11:35
     * @Param
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return traversalBuildTree2(preorder, 0, preorder.length,inorder, 0, inorder.length);
    }

    TreeNode traversalBuildTree2(int[] preorder, int preBegin, int preEnd,int[] inorder, int inBegin, int inEnd) {
        if (preBegin >= preEnd || inBegin >= inEnd) {
            return null;
        }
        int rootIndex = map.get(preorder[preBegin]);
        TreeNode root = new TreeNode(inorder[rootIndex]);
        int lenOfLeft = rootIndex - preBegin;
        root.left = traversalBuildTree2(preorder, preBegin + 1, preBegin + lenOfLeft + 1, inorder, inBegin, rootIndex);
        root.right = traversalBuildTree2(preorder, preBegin + lenOfLeft + 1, preEnd,inorder, rootIndex + 1, inEnd);

        return root;
    }

    /*
     * @Description  654. 最大二叉树
     * @author   Edison
     * @date    2023/3/14 14:51
     * @Param
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return traversalConstructMaximumBinaryTree(nums, 0, nums.length);
    }

    TreeNode traversalConstructMaximumBinaryTree(int[] nums, int begin, int end) {
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
        root.left = traversalConstructMaximumBinaryTree(nums, begin, rootIndex);
        root.right = traversalConstructMaximumBinaryTree(nums, rootIndex + 1, end);

        return root;
    }

    /*
     * @Description  617. 合并二叉树
     * @author   Edison
     * @date    2023/3/14 15:17
     * @Param
     * @return
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
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
    }
    
    /*
     * @Description  700. 二叉搜索树中的搜索
     * @author   Edison
     * @date    2023/3/14 15:32
     * @Param
     * @return  
     */
    public TreeNode searchBST(TreeNode root, int val) {

//        if (root == null || root.val == val) {
//            return root;
//        }
        //递归
//        TreeNode result = null;
//        if (root.val > val) result = searchBST(root.left, val);
//        if (root.val < val) result = searchBST(root.right, val);
//        return result;

        //迭代
        while (root != null) {
            if (root.val > val) {
                root = root.left;
            } else if (root.val < val) {
                root = root.right;
            } else {
                return root;
            }
        }

        return root;
    }
    
    /*
     * @Description  98. 验证二叉搜索树
     * @author   Edison
     * @date    2023/3/14 15:48
     * @Param
     * @return  
     */
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        //递归
//        boolean leftNode = isValidBST(root.left);
//        if (!leftNode) {
//            return false;
//        }
//        if (pre != null && pre.val >= root.val) {
//            return false;
//        }
//        pre = root;
//        boolean rightNode = isValidBST(root.right);
//        return rightNode;

        //迭代
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            if (pre != null && pre.val >= node.val) {
                return false;
            }
            pre = node;
            root = node.right;
        }

        return true;
    }

    /*
     * @Description  530. 二叉搜索树的最小绝对差
     * @author   Edison
     * @date    2023/3/14 16:21
     * @Param
     * @return
     */

    int result = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //递归
//        traversalGetMinimumDifference(root);

        //迭代
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            if (pre != null) {
                result = Math.min(result, node.val - pre.val);
            }
            pre = node;
            root = node.right;
        }

        return result;
    }

    void traversalGetMinimumDifference(TreeNode root) {
        if (root == null) {
            return;
        }
        traversalGetMinimumDifference(root.left);
        if (pre != null) {
            result = Math.min(result, root.val - pre.val);
        }
        pre = root;
        traversalGetMinimumDifference(root.right);
    }

    /*
     * @Description  501. 二叉搜索树中的众数
     * @author   Edison
     * @date    2023/3/14 16:36
     * @Param
     * @return
     */
    int count = 0;
    int maxCount = 0;
    ArrayList<Integer> arrayList;
    public int[] findMode(TreeNode root) {
        arrayList = new ArrayList<>();

        //递归
//        searchBST(root);

        //迭代
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                if (pre == null || node.val != pre.val) {
                    count = 1;
                } else {
                    count++;
                }
                if (count > maxCount) {
                    maxCount = count;
                    arrayList.clear();
                    arrayList.add(node.val);
                } else if (count == maxCount) {
                    arrayList.add(node.val);
                }
                pre = node;
                root = node.right;
            }
        }

        int[] res = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            res[i] = arrayList.get(i);
        }
        return res;
    }

    void searchBST(TreeNode root) {
        if (root == null) {
            return;
        }
        searchBST(root.left);
        int rootVal = root.val;
        if (pre == null && rootVal != pre.val) {
            count = 1;
        } else {
            count++;
        }
        if (count > maxCount) {
            maxCount = count;
            arrayList.clear();
            arrayList.add(rootVal);
        } else if (count == maxCount) {
            arrayList.add(rootVal);
        }
        pre = root;
        searchBST(root.right);
    }
    
    /*
     * @Description  236. 二叉树的最近公共祖先
     * @author   Edison
     * @date    2023/3/14 17:13
     * @Param
     * @return  
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left == null && right != null) {
            return right;
        } else if (left != null && right == null) {
            return left;
        } else {
            return null;
        }
    }

    /*
     * @Description  235. 二叉搜索树的最近公共祖先
     * @author   Edison
     * @date    2023/3/15 9:49
     * @Param
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //递归
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor2(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor2(root.right, p, q);
        }

        //迭代
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
     * @date    2023/3/15 10:24
     * @Param   
     * @return  
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        //递归
//        if (root.val > val) {
//            root.left = insertIntoBST(root.left, val);
//        } else if (root.val < val) {
//            root.right = insertIntoBST(root.right, val);
//        }
//        return root;

        //迭代
        TreeNode newRoot = root;
        TreeNode cur = root;
        while (root != null) {
            cur = root;
            if (root.val > val) {
                root = root.left;
            } else if (root.val < val) {
                root = root.right;
            }
        }

        if (cur.val > val) {
            cur.left = new TreeNode(val);
        } else if (cur.val < val) {
            cur.right = new TreeNode(val);
        }
        return newRoot;
    }

    /*
     * @Description  450. 删除二叉搜索树中的节点
     * @author   Edison
     * @date    2023/3/15 10:47
     * @Param
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val == key) {
            if (root.right == null) {
                return root.left;
            } else if (root.left == null) {
                return root.right;
            } else {
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                cur.left = root.left;
                root = root.right;
                return root;
            }
        }
        if (root.val > key) root.left = deleteNode(root.left, key);
        if (root.val < key) root.right = deleteNode(root.right, key);

        return root;
    }

    /*
     * @Description  669. 修剪二叉搜索树
     * @author   Edison
     * @date    2023/3/15 11:14
     * @Param
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

        //递归
//        if (root.val < low) {
//            return trimBST(root.right, low, high);
//        }
//        if (root.val > high) {
//            return trimBST(root.left, low, high);
//        }
//        root.left = trimBST(root.left, low, high);
//        root.right = trimBST(root.right, low, high);

        //迭代
        //头节点
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {
                root = root.right;
            } else {
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
    
    /*
     * @Description  108. 将有序数组转换为二叉搜索树
     * @author   Edison
     * @date    2023/3/15 14:52
     * @Param
     * @return  
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        //递归
//        return traversalSortedArrayToBST(nums, 0, nums.length);

        //迭代
        if (nums == null) {
            return null;
        }

        TreeNode root = new TreeNode(0);
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        Queue<Integer> queue3 = new LinkedList<>();
        queue1.offer(root);
        queue2.offer(0);
        queue3.offer(nums.length - 1);
        while (!queue1.isEmpty()) {
            TreeNode node = queue1.poll();
            int left = queue2.poll();
            int right = queue3.poll();
            int mid = left + ((right - left) >> 1);

            node.val = nums[mid];

            if (left < mid) {
                node.left = new TreeNode(-1);
                queue1.offer(node.left);
                queue2.offer(left);
                queue3.offer(mid - 1);
            }
            if (right > mid) {
                node.right = new TreeNode(-1);
                queue1.offer(node.right);
                queue2.offer(mid + 1);
                queue3.offer(right);
            }
        }
        return root;
    }

    TreeNode traversalSortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid - 1]);
        root.left = traversalSortedArrayToBST(nums, left, mid - 1);
        root.right = traversalSortedArrayToBST(nums, mid + 1, right);
        return root;
    }
    
    /*
     * @Description  538. 把二叉搜索树转换为累加树
     * @author   Edison
     * @date    2023/3/15 15:22
     * @Param
     * @return  
     */
    int preSum = 0;
    public TreeNode convertBST(TreeNode root) {
//        traversalConvertBST(root);

        //迭代
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.right;
            } else {
                cur = stack.pop();
                cur.val += preSum;
                preSum = cur.val;
                cur = cur.left;
            }
        }

        return root;
    }

    void traversalConvertBST(TreeNode root) {
        if (root == null) {
            return;
        }
        traversalConvertBST(root.right);
        root.val += preSum;
        preSum = root.val;
        traversalConvertBST(root.left);
    }


}
