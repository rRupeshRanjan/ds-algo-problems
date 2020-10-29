package com.learning;

import com.learning.DataStructures.TreeNode;

import java.util.*;

public class BinaryTree {
    static int index = 0;
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);

        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode2.setLeft(treeNode4);
        treeNode2.setRight(treeNode5);
        treeNode3.setLeft(treeNode6);
        treeNode3.setRight(treeNode7);

//        preorderTraversal(bstFromPreorder(new int[]{4, 5}));
//        levelOrderBottom(treeNode1);
//        antiClockwiseSpiralTraversal(treeNode1);
//        levelOrderTraversal(node1);
//        System.out.println(compareTree(node1, node3));
//        System.out.println(minDepth(node1));
//        System.out.println(maxDepthTree(node1));
//        System.out.println(sumRootToLeaf(node1));
//        distanceBetweenTwoNodes(node5);
//        inorderTraversal(node5);
//        preorderTraversal(root);
//        postorderTraversal(node5);
//        System.out.println(leafSimilar(treeNode1, treeNode2));
//        System.out.println(isBalanced(treeNode1));
        System.out.println(neighbours(treeNode1, treeNode7));
    }

    private static void distanceBetweenTwoNodes(TreeNode treeNode5) {
        TreeNode lca = lca(treeNode5, 1, 8);
        int node1Depth = nodeLevel(treeNode5, 1, 0);
        int node2Depth = nodeLevel(treeNode5, 8, 0);
        int nodeLcaDepth = nodeLevel(treeNode5, lca.value, 0);

        System.out.println("Distance :" + (node1Depth + node2Depth - 2 * nodeLcaDepth));
    }

    private static void postorderTraversal(TreeNode root) {
        Stack<TreeNode> nodes1 = new Stack<>();
        List<TreeNode> treeNodeList = new ArrayList<>();

        if(root!=null) {
            nodes1.push(root);

            while (!nodes1.empty()) {
                TreeNode curr = nodes1.pop();
                treeNodeList.add(curr);

                if (curr.left != null) {
                    nodes1.push(curr.left);
                }

                if (curr.right != null) {
                    nodes1.push(curr.right);
                }
            }
        }

        Collections.reverse(treeNodeList);
        for(TreeNode treeNode : treeNodeList){
            System.out.print(treeNode.value);
        }
    }

    private static void preorderTraversal(TreeNode root) {
        Stack<TreeNode> treeNodes = new Stack<>();

        if(root!=null) {
            treeNodes.push(root);

            while (!treeNodes.empty()) {
                TreeNode curr = treeNodes.pop();
                System.out.print(curr.value + " ");

                if (curr.right != null) {
                    treeNodes.push(curr.right);
                }

                if (curr.left != null) {
                    treeNodes.push(curr.left);
                }
            }
        }
        System.out.println();
    }

    private static void inorderTraversal(TreeNode root) {
        Stack<TreeNode> treeNodes = new Stack<>();
        List<TreeNode> treeNodeList = new ArrayList<>();

        if(root!=null) {
            TreeNode curr = root;

            while (true) {
                while (curr != null) {
                    treeNodes.push(curr);
                    curr = curr.left;
                }

                if (treeNodes.empty())
                    break;

                curr = treeNodes.pop();
//                System.out.print(curr.value);
                treeNodeList.add(curr);
                curr = curr.right;
            }
        }

        TreeNode rootNew = treeNodeList.get(0);
        TreeNode tempRoot = rootNew;

        for (int i = 0; i< treeNodeList.size()-1; i++){
            tempRoot.setRight(new TreeNode(treeNodeList.get(i+1).value));
            tempRoot = tempRoot.right;
        }

        System.out.println(treeNodeList);
    }

    private static void levelOrderTraversal(TreeNode treeNode){
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(treeNode);
        while(!queue.isEmpty()){
            TreeNode pop = queue.poll();
            System.out.println(pop.value);

            if(pop.left!=null) queue.add(pop.left);
            if(pop.right!=null) queue.add(pop.right);
        }
    }

    private static void antiClockwiseSpiralTraversal(TreeNode treeNode){
        int i=1, j= heightOfTree(treeNode);
        int flag = 0;

        while (i<=j){
            if(flag==0){
                rightToLeft(treeNode, i);
                flag=1;
                i++;
            } else {
                leftToRight(treeNode, j);
                flag=0;
                j--;
            }
        }
    }

    private static int heightOfTree(TreeNode treeNode) {
        if(treeNode == null)
            return 0;

        int left = heightOfTree(treeNode.left);
        int right = heightOfTree(treeNode.right);

        return Math.max(left, right) + 1;
    }

    private static void rightToLeft(TreeNode treeNode, int level) {
        if(treeNode == null)
            return;
        if(level==1)
            System.out.print(treeNode.value + " ");
        else{
            rightToLeft(treeNode.right, level-1);
            rightToLeft(treeNode.left, level-1);
        }
    }

    private static void leftToRight(TreeNode treeNode, int level) {
        if(treeNode == null)
            return;
        if(level==1)
            System.out.print(treeNode.value + " ");
        else{
            leftToRight(treeNode.left, level-1);
            leftToRight(treeNode.right, level-1);
        }
    }

    private static TreeNode lca(TreeNode root, int val1, int val2) {
        if (root == null) return null;
        if (root.value == val1 || root.value == val2) return root;

        TreeNode left = lca(root.left, val1, val2);
        TreeNode right = lca(root.right, val1, val2);

        if(left != null && right != null) return root;
        if(left == null && right == null) return null;

        return left != null ? left : right;
    }

    private static int nodeLevel(TreeNode root, int data, int level) {
        if(root == null)
            return 0;

        if(root.value == data)
            return level;

        int depth = nodeLevel(root.left, data, level + 1);

        if ( depth != 0 )
            return depth;

        depth = nodeLevel(root.right, data, level + 1);

        return depth;
    }

    private static int maxDepthTree(TreeNode root) {
        if (root == null) return 0;

        int lDepth = maxDepthTree(root.left);
        int rDepth = maxDepthTree(root.right);

        return Math.max(lDepth, rDepth) + 1;
    }

    private static int minDepth(TreeNode root) {
        if(root == null)
            return 0;

        if(root.left == null)
            return 1 + minDepth(root.right);

        if(root.right == null)
            return 1 + minDepth(root.left);

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    private static int sumRootToLeaf(TreeNode root) {
        List<List<Integer>> paths = new ArrayList<>();
        dfs(root, paths, new ArrayList<>());
        int sum = 0;
        for(List<Integer> path: paths) {
            int num = 0;
            for (int i: path) {
                num = (num << 1) + i;
            }
            sum += num;
        }

        return sum;
    }

    private static void dfs(TreeNode root, List<List<Integer>> paths, List<Integer> path) {
        path.add(root.value);

        if(root.left!= null){
            dfs(root.left, paths, path);
            path.remove(path.size()-1);
        }

        if(root.right!= null){
            dfs(root.right, paths, path);
            path.remove(path.size()-1);
        }

        if (root.left == null && root.right == null) {
            paths.add(new ArrayList<>(path));
        }
    }

    private static boolean compareTree(TreeNode a, TreeNode b) {
        if(a==null && b==null)
            return true;
        if(a==null || b==null)
            return false;

        return a.value == b.value && compareTree(a.left, b.left) && compareTree(a.right, b.right);
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        if(preorder==null || preorder.length==0)
            return null;

        return bstFromPreorderHelper(preorder, 0, preorder.length-1);
    }

    public static TreeNode bstFromPreorderHelper(int[] preorder, int start, int end){
        if(start==end) return new TreeNode(preorder[start]);
        if(start>end) return null;

        int key = start;
        for(int i=start+1; i<=end; i++){
            if(preorder[i]> preorder[start]) break;
            key++;
        }

        TreeNode curr = new TreeNode(preorder[start]);
        curr.left = bstFromPreorderHelper(preorder, start+1, key);
        curr.right = bstFromPreorderHelper(preorder, key+1, end);

        return curr;
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(root==null)
            return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> holder = new ArrayList<>();
            for(int i=0; i<size; i++) {
                TreeNode poll = q.poll();
                if(poll.left!=null) q.add(poll.left);
                if(poll.right!=null) q.add(poll.right);
                holder.add(poll.value);
            }
            result.add(holder);
        }
        Collections.reverse(result);

        return result;
    }

    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfsLeafSimilar(root1, list1);
        dfsLeafSimilar(root2, list2);
        return list1.equals(list2);
    }

    private static void dfsLeafSimilar(TreeNode root, List<Integer> list) {
        if(root==null)
            return;

        if(root.left==null && root.right==null)
            list.add(root.value);

        dfsLeafSimilar(root.left, list);
        dfsLeafSimilar(root.right, list);
    }

    public static boolean isBalanced(TreeNode root) {
        return depthIsbalanced(root) != -1;
    }

    private static int depthIsbalanced(TreeNode root) {
        if (root == null) return 0;

        int left = depthIsbalanced(root.left);
        if (left == -1) return -1;
        int right = depthIsbalanced(root.right);
        if (right == -1) return -1;

        if (Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }

    private static List<Integer> neighbours(TreeNode root, TreeNode node) {
        List<Integer> list = new ArrayList<>();

        if(root==null || node==null)
            return list;

        boolean found = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                TreeNode temp = q.poll();

                if(node == temp.left || node == temp.right) {
                    found = true;
                    continue;
                }

                if (temp.left != null) q.add(temp.left);
                if (temp.right != null) q.add(temp.right);
            }

            if(found) break;
        }

        while(!q.isEmpty())
            list.add(q.poll().value);

        return list;
    }
}