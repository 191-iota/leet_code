package Java;
import java.util.Stack;

public class ValidParenthesis {
  public static void main(String[] args) {}

  // Attempt 3 - (works)
  // Here I tried solving it on my own way but ended up implementing
  // a raw stack functionality which is the most optimal I think
  public static boolean isValid3(String s) {
    short[] window = new short[s.length()];
    char[] cArray = s.toCharArray();

    if ((cArray.length) % 2 != 0) {
      return false;
    }

    for (short i = 0; i < cArray.length; i++) {
      switch (cArray[i]) {
        case ')' -> window[i] = 1;
        case '}' -> window[i] = 2;
        case ']' -> window[i] = 3;
        case '[' -> window[i] = -3;
        case '{' -> window[i] = -2;
        case '(' -> window[i] = -1;
      }
    }

    short top = -1;
    for (short i = 0; i < window.length; i++) {
      if (window[i] < 0) {
        // Open
        window[++top] = window[i];
      } else {
        // closing bracket, check if it matches the top of the stack
        if (top == -1 || window[top] + window[i] != 0) {
          return false;
        }
        top--;
      }
    }
    return top == -1;
  }

  // Attempt 2 - (works)
  // Since this is getting too complex i will try implementing a cache stack
  // Still too overengineered
  public static boolean isValid2(String s) {
    Stack<Character> stack = new Stack<>();
    Stack<Character> cache = new Stack<>();

    for (char c : s.toCharArray()) {
      stack.push(c);
    }

    while (!stack.isEmpty()) {
      char itemToSearch = 'X';

      if (stack.size() != cache.size()) {
        switch (stack.peek()) {
          case ')' -> itemToSearch = '(';
          case '}' -> itemToSearch = '{';
          case ']' -> itemToSearch = '[';
        }
      }

      if (!cache.isEmpty() && stack.peek() == cache.peek()) {
        stack.pop();
        cache.pop();
      } else if (stack.peek() == ')' || stack.peek() == ']' || stack.peek() == '}') {
        stack.pop();
      } else {
        return false;
      }

      if (itemToSearch != 'X') {
        cache.push(itemToSearch);
      }
    }
    return cache.isEmpty();
  }

  // Attempt 1 - (doesn't work)
  public static boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      stack.push(c);
    }

    boolean uneven = false;
    while (!stack.isEmpty()) {
      char itemToSearch = 'X';

      switch (stack.peek()) {
        case ')' -> itemToSearch = '(';
        case '}' -> itemToSearch = '{';
        case ']' -> itemToSearch = '[';
      }

      if (stack.peek() == ')' && stack.search(itemToSearch) == 2
          || stack.peek() == '}' && stack.search(itemToSearch) == 2
          || stack.peek() == ']' && stack.search(itemToSearch) == 2) {
        stack.pop();
        stack.pop();
      } else if (stack.peek() == ')' && stack.search(itemToSearch) % 2 == 0
          || stack.peek() == '}' && stack.search(itemToSearch) % 2 == 0
          || stack.peek() == ']' && stack.search(itemToSearch) % 2 == 0) {
        stack.pop();
        uneven = true;
      } else if (stack.size() == 1 && uneven) {
        stack.pop();
      } else {
        return false;
      }
    }
    return true;
  }
}
