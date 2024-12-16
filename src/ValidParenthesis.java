import java.util.Stack;

public class ValidParenthesis {
    public static void main(String[] args) {

    }


    // Attempt 1 - (doesn't work)
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
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

            if (stack.peek() == ')' && stack.search(itemToSearch) == 2 ||
                    stack.peek() == '}' && stack.search(itemToSearch) == 2 ||
                    stack.peek()== ']' && stack.search(itemToSearch) == 2) {
                stack.pop();
                stack.pop();
            } else if (stack.peek() == ')' && stack.search(itemToSearch) % 2 == 0 ||
                    stack.peek() == '}' && stack.search(itemToSearch) % 2 == 0||
                    stack.peek()== ']' && stack.search(itemToSearch) % 2 == 0) {
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
