import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 1000000007;
    static int n;
    static long[] dp, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        dp = new long[n+1];
        dp[0] = 1;
        dp[1] = 2;
        if (n >= 2) dp[2] = 7;
        long sum = 20;
        for (int i = 3; i <= n; ++i) {
            dp[i] = (dp[i] + dp[i-2] + sum) % MOD;
            sum = (sum + 2 * dp[i]) % MOD;
        }
        System.out.println(dp[n]);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}