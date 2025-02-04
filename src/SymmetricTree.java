import java.util.ArrayList;
import java.util.List;

public class SymmetricTree {
      public static void main(String[] args) {
          isSymmetric(generateSymmetricTree());
      }
  
      // inorder traversal
      private static boolean isSymmetric(TreeNode root) {
          List<Integer> right = new ArrayList<>();
          List<Integer> left = new ArrayList<>();
          if(root == null) {
              inverseList(left);
              System.out.println(left);
          }
  
          isSymmetric(root.left);
          isSymmetric(root.right);
          if(root.right != null) {
              right.add(root.right.val);
          }
          if(root.left != null) {
              left.add(root.left.val);
          }
          //System.out.println(right);
          return false;
      }
  
      private static List<Integer> inverseList(List<Integer> left) {
          left.forEach(v -> {
              if(v > 0) {
                  int index = left.get(v);
                  int temp = left.indexOf(index + 1);
                  left.add(index + 1, left.get(v));
                  left.add(index, temp);
              }
          });
          return left;
      }
  
      public static TreeNode generateSymmetricTree() {
          //        1
          //       / \
          //      2   2
          //     / \ / \
          //    3  4 4  3
          return new TreeNode(1,
                  new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                  new TreeNode(2, new TreeNode(4), new TreeNode(3))
          );
      }
  }

