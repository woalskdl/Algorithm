package kjm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// https://algospot.com/judge/problem/read/BRACKETS2
public class BRACKETS2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String input = br.readLine();

            char[] brackets = input.toCharArray();

            boolean matched = matched(brackets);

            if (matched)
                sb.append("YES").append("\n");
            else
                sb.append("NO").append("\n");
        }

        System.out.println(sb);
    }

    private static boolean matched(char[] brackets) {
        Stack<Character> stack = new Stack<>();
        boolean matched = true;

        for (int i = 0; i < brackets.length && matched; i++) {
            char bracket = brackets[i];
            if (bracket == '(' || bracket == '{' || bracket == '[') {
                stack.push(bracket);
            } else {
                if (stack.isEmpty())
                    return false;

                char lastOpen = stack.pop();

                switch (bracket) {
                    case ')' :
                        matched = (lastOpen == '(');
                        break;
                    case '}' :
                        matched = (lastOpen == '{');
                        break;
                    case ']' :
                        matched = (lastOpen == '[');
                        break;
                }

//                matched = switch (bracket) {
//                    case ')' -> (lastOpen == '(');
//                    case '}' -> (lastOpen == '{');
//                    case ']' -> (lastOpen == '[');
//                    default -> false;
//                };
            }
        }

        return matched && stack.isEmpty();
    }
}
