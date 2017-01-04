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

		Map<Integer, Integer> revIndex = new HashMap<Integer, Integer>(
				inorder.length);
		for (int idx = 0; idx < inorder.length; idx++) {
			revIndex.put(inorder[idx], idx);
		}

		TreeNode root = new TreeNode(preorder[0]);
		TreeNode parNode = null;
		TreeNode curNode = null;

		outer: for (int val : preorder) {
			boolean isLeft = false;
			parNode = root;
			do {
				int parVal = parNode.val;
				int parIdx = revIndex.get(parVal);
				int curIdx = revIndex.get(val);
				if (curIdx < parIdx) {
					isLeft = true;
					curNode = parNode.left;
				} else if (curIdx > parIdx) {
					isLeft = false;
					curNode = parNode.right;
				} else {
					continue outer;
				}
				if (curNode != null) {
					parNode = curNode;
				}
			} while (curNode != null);

			curNode = new TreeNode(val);
			if (isLeft) {
				parNode.left = curNode;
			} else {
				parNode.right = curNode;
			}

			curNode = null;
		}

		return root;
    }
}