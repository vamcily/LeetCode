/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder == null || preorder.length == 0 || inorder == null
				|| inorder.length == 0) {
			return null;
		}

		return buildTreeNode(preorder, inorder, 0, 0, inorder.length - 1);
    }

    public TreeNode buildTreeNode(int[] preorder, int[] inorder, int preIdx, int inStart, int inEnd) {
    	if (preIdx > preorder.length - 1 || inStart > inEnd) {
    		return null;
    	}

    	TreeNode curNode = new TreeNode(preorder[preIdx]);
    	int inIdx = 0;
    	for (int i = inStart; i <= inEnd; i++) {
    		if (inorder[i] == curNode.val) {
    			inIdx = i;
    		}
    	}

    	curNode.left = buildTreeNode(preorder, inorder, preIdx + 1, inStart, inIdx - 1);
    	curNode.right = buildTreeNode(preorder, inorder, preIdx + inIdx - inStart + 1, inIdx + 1, inEnd);

    	return curNode;
    }
}