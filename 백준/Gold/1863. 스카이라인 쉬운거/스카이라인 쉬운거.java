import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = stoi(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken()), y = stoi(st.nextToken());
            if (stack.isEmpty() || stack.peek() < y) {
                stack.push(y);
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > y) {
                stack.pop();
                ans++;
            }
            if (stack.isEmpty() || stack.peek() != y) {
                stack.push(y);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != 0) {
                ans++;
            }
        }
        System.out.println(ans);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}