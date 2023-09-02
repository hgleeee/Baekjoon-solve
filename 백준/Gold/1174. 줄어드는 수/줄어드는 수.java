import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] candidate;
    static String ans = "-1";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        candidate = new int[11];
        Arrays.fill(candidate, -1);

        for (int i = 1; i <= 10; ++i) {
            solve(i, 10);
        }
        System.out.println(ans);
        br.close();
    }

    static void solve(int idx, int beforeNum) {
        if (idx == 0) {
            n--;
            if (n == 0) {
                ans = transform(candidate);
            }
            return;
        }
        for (int i = 0; i < beforeNum; ++i) {
            candidate[idx] = i;
            solve(idx-1, i);
            candidate[idx] = -1;
        }
    }

    static String transform(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 10; i >= 1; --i) {
            if (arr[i] == -1) continue;
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}