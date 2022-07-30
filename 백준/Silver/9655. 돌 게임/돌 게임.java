import java.util.*;
import java.io.*;

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());
        dp = new int[n+1][2];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        if (solve(n, true) == 1) {
            System.out.println("CY");
        } else {
            System.out.println("SK");
        }
        br.close();
    }

    static int solve(int n, boolean isSK) {
        if (n == 0) {
            if (isSK) {
                return 1;
            } else {
                return 0;
            }
        }
        if (dp[n][isSK ? 0:1] != -1) {
            return dp[n][isSK ? 0:1];
        }
        int ret = -1;
        if (n >= 3) {
            int one = solve(n - 1, !isSK), three = solve(n - 3, !isSK);
            if (isSK) {
                if (one == 0 || three == 0) {
                    ret = 0;
                } else {
                    ret = 1;
                }
            } else {
                if (one == 1 || three == 1) {
                    ret = 1;
                } else {
                    ret = 0;
                }
            }
        } else {
            ret = solve(n-1, !isSK);
        }
        dp[n][isSK ? 0:1] = ret;
        return dp[n][isSK ? 0:1];
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}