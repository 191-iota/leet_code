package AddTwoNumbers;

import java.math.BigInteger;

public class AddTwoNumbers {

  public static void main(String[] args) {
    ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(9)));
    ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(9))));

    addTwoNumbers(l1, l2);
  }

  // Attempt 3 - Alternative (works)

  public static ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
    ListNode resultNode = new ListNode();
    ListNode linker = resultNode; // They now point to the same object.

    ListNode l1Linker = l1;
    ListNode l2Linker = l2;

    int carry = 0;
    int current = 0;

    while (l1Linker != null || l2Linker != null) {

      if (l1Linker != null) {
        current += l1Linker.val;
      }

      if (l2Linker != null) {
        current += l2Linker.val;
      }

      carry = current / 10;
      linker.next = new ListNode((current) % 10);

      current = carry;
      linker = linker.next;
      l1Linker = l1Linker != null ? l1Linker.next : null;
      l2Linker = l2Linker != null ? l2Linker.next : null;
    }

    if (carry != 0) {
      linker.next = new ListNode(carry);
    }

    return resultNode.next;
  }

  // Attempt 2 - Alternative (does not work)

  public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
    ListNode resultNode = new ListNode();
    ListNode linker = resultNode; // They now point to the same object.

    ListNode l1Linker = l1;
    ListNode l2Linker = l2;

    int carry = 0;
    int current;

    while (l1Linker != null || l2Linker != null) {

      current = 0;
      carry = 0;

      if (l1Linker != null) {
        current += l1Linker.val;
        if (current + carry > 9) {
          linker.next = new ListNode((current + carry) % 10);
          carry += (current + l1Linker.val + carry) / 10;
        } else {
          linker.next.val += current + carry;
        }
      }

      if (l2Linker != null) {
        current += l2Linker.val;
        if (current + carry > 9) {
          linker.next = new ListNode((current + carry) % 10);
          carry += (current + l2Linker.val + carry) / 10;
        } else {
          linker.next.val += current + carry;
        }
      }

      linker = linker.next;
      l1Linker = l1Linker != null ? l1Linker.next : null;
      l2Linker = l2Linker != null ? l2Linker.next : null;
    }

    if (carry != 0) {
      linker.next = new ListNode(carry);
    }

    return resultNode.next;
  }

  // Attempt 1 (works)
  // Don't use for inspiration, this is beefy

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    StringBuilder firstNodeBuilder = new StringBuilder();
    StringBuilder secondNodeBuilder = new StringBuilder();

    boolean nextNodeL1 = true;
    boolean nextNodeL2 = true;

    while (nextNodeL1) {
      firstNodeBuilder.append(l1.val);
      if (l1.next != null) {
        l1.val = l1.next.val;
        l1.next = l1.next.next;
      } else {
        nextNodeL1 = false;
      }
    }

    while (nextNodeL2) {
      secondNodeBuilder.append(l2.val);
      if (l2.next != null) {
        l2.val = l2.next.val;
        l2.next = l2.next.next;
      } else {
        nextNodeL2 = false;
      }
    }

    firstNodeBuilder.reverse();
    secondNodeBuilder.reverse();

    String result =
        new StringBuilder(
                new BigInteger(firstNodeBuilder.toString())
                    .add(new BigInteger(secondNodeBuilder.toString()))
                    .toString())
            .reverse()
            .toString();

    ListNode resultNode = new ListNode();
    ListNode linker = resultNode;
    System.out.println(result);
    for (int i = 0; i < result.length(); i++) {
      linker.next = new ListNode(Character.getNumericValue(result.charAt(i)));
      linker = linker.next;
    }

    resultNode = resultNode.next;
    return resultNode;
  }
}
