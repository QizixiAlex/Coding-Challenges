/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int minVal = Integer.MAX_VALUE;
    long secMinVal = Long.MAX_VALUE;

    public int findSecondMinimumValue(TreeNode root) {
        minVal = root.val;
        dfs(root);
        return secMinVal == Long.MAX_VALUE ? -1 : (int)secMinVal;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.val >= secMinVal) {
            return;
        } else if (node.val == minVal) {
            dfs(node.left);
            dfs(node.right);
        } else {
            secMinVal = node.val;
        }
    }

}