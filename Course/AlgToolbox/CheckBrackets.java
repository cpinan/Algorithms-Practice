import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class CheckBrackets {

    static class Bracket {

        Bracket(char type, int position) {
            this.type = type;
            this.position = position;
        }

        boolean Match(char c) {
            if (this.type == '[' && c == ']')
                return true;
            if (this.type == '{' && c == '}')
                return true;
            if (this.type == '(' && c == ')')
                return true;
            return false;
        }

        char type;
        int position;
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        int position = 0;
        int errorPosition = -1;
        Stack<Bracket> stack = new Stack<>();
        for (position = 0; position < text.length(); position++) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                stack.push(new Bracket(next, position + 1));
            }

            if (next == ')' || next == ']' || next == '}') {
                if (stack.isEmpty() || !stack.pop().Match(next)) {
                    errorPosition = position + 1;
                    break;
                }
            }
        }
        if (errorPosition == -1 && !stack.isEmpty()) {
            while (!stack.isEmpty()) {
                errorPosition = stack.pop().position;
            }
        }

        if (errorPosition != -1) {
            System.out.println(errorPosition + "");
        } else {
            System.out.println("Success");
        }
    }
}
