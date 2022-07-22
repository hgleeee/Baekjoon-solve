import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int t = stoi(br.readLine());
        while (--t >= 0) {
            st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken());
            m = stoi(st.nextToken());
            dp = new int[n][m];
            for (int i = 0; i < n; ++i) {
                Arrays.fill(dp[i], -1);
            }
            sb.append(solve(0, 0) + "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int solve(int beforeLeft, int beforeRight) {
        if (beforeLeft == n) {
            return 1;
        }
        if (dp[beforeLeft][beforeRight] != -1) {
            return dp[beforeLeft][beforeRight];
        }
        dp[beforeLeft][beforeRight] = 0;
        int nowLeft = beforeLeft+1;
        for (int i = beforeRight+1; i <= m-n+nowLeft; ++i) {
            dp[beforeLeft][beforeRight] += solve(nowLeft, i);
        }
        return dp[beforeLeft][beforeRight];
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}