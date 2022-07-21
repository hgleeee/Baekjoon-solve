import java.util.*;
import java.io.*;

public class Main {

    static int n, l, r;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int t = stoi(br.readLine());
        while (--t >= 0) {
            st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken());
            l = stoi(st.nextToken());
            r = stoi(st.nextToken());
            dp = new long[n+1][21][21];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= 20; ++j) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
            sb.append(solve(n, 0, 0) + "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static long solve(int idx, int left, int right) {
        if (idx == 0) {
            if (left == l && right == r) {
                return 1;
            }
            return 0;
        }
        if (dp[idx][left][right] != -1) {
            return dp[idx][left][right];
        }
        dp[idx][left][right] = 0;
        if (idx == n) {
            return dp[idx][left][right] = solve(idx-1, left+1, right+1);
        }
        for (int i = 0; i <= n-idx; ++i) {
            if (i == 0) {
                dp[idx][left][right] += solve(idx-1, left+1, right);
            } else if (i == n-idx) {
                dp[idx][left][right] += solve(idx-1, left, right+1);
            } else {
                dp[idx][left][right] += solve(idx - 1, left, right);
            }
        }
        return dp[idx][left][right];
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}