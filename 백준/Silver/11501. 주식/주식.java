import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int t = stoi(br.readLine());
        while (--t >= 0) {
            int n = stoi(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; ++i) {
                arr[i] = stoi(st.nextToken());
            }
            long ans = 0;
            Stack<int[]> stack = new Stack<>();
            for (int i = 0; i < n; ++i) {
                if (stack.isEmpty() || stack.peek()[0] > arr[i]) {
                    stack.push(new int[]{arr[i], 1});
                } else {
                    int cnt = 1;
                    while (!stack.isEmpty() && stack.peek()[0] <= arr[i]) {
                        int[] popped = stack.pop();
                        ans += (long)(arr[i] - popped[0]) * popped[1];
                        cnt += popped[1];
                    }
                    stack.push(new int[]{arr[i], cnt});
                }
            }
            sb.append(ans + "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}