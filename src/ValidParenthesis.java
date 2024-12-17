import java.util.Stack;

public class ValidParenthesis {
    public static void main(String[] args) {

    }

    // Attempt 2 - (works)
// Since this is getting too complex i will try implementing a cache stack
// Still too overengineered
    public static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        Stack<Character> cache = new Stack<>();

        for(char c : s.toCharArray()){
            stack.push(c);
        }

        while (!stack.isEmpty()) {
            char itemToSearch = 'X';

            if(stack.size() != cache.size()) {
                switch (stack.peek()) {
                    case ')' -> itemToSearch = '(';
                    case '}' -> itemToSearch = '{';
                    case ']' -> itemToSearch = '[';
                }
            }

            if(!cache.isEmpty() && stack.peek() == cache.peek()){
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
