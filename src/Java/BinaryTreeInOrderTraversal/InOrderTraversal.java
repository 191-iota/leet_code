package Java.BinaryTreeInOrderTraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOrderTraversal {
  public static void main(String[] args) {
    inOrderTraversal(generateSymmetricTree());
  }

  public static List<Integer> inOrderTraversalIterative(TreeNode root) {

    List<Integer> values = new ArrayList<>();
    Stack<TreeNode> valueStack = new Stack<>();
    TreeNode current = root;

    while (current != null || !valueStack.isEmpty()) {
      while (current != null) {
        valueStack.push(current);
        current = current.left;
      }

      current = valueStack.pop();
      values.add(current.val);
      current = current.right;
    }
  }

  // Attempt 1 - works (using recursion)
  public static List<Integer> inOrderTraversal(TreeNode root) {
    List<Integer> values = new ArrayList<>();
    traverse(values, root);
    System.out.println(values.toString());
    return values;
  }

  public static void traverse(List<Integer> values, TreeNode currentNode) {
    if (currentNode == null) return;

    traverse(values, currentNode.left);
    values.add(currentNode.val);
    traverse(values, currentNode.right);
  }

  public static TreeNode generateSymmetricTree() {
    //        1
    //       / \
    //      2   2
    //     / \ / \
    //    3  4 4  3
    return new TreeNode(
        1,
        new TreeNode(2, new TreeNode(3), new TreeNode(4)),
        new TreeNode(2, new TreeNode(4), new TreeNode(3)));
  }
}
