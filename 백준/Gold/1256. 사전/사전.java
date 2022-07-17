import java.util.*;
import java.io.*;

public class Main {

    static int n, m, k;
    static double[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        k = stoi(st.nextToken());
        dp = new double[101][101];
        for (int i = 0; i <= 100; ++i) {
            Arrays.fill(dp[i], -1);
        }
        double total = solve(n, m);
        if (total < k) {
            System.out.println(-1);
        } else {
            String ans = "";
            int aNum = n, zNum = m, idx = k;
            while (aNum >= 0 && zNum >= 0) {
                if (aNum == 0 && zNum == 0) {
                    break;
                }
                double aSize = 0;
                if (aNum > 0) {
                    aSize = solve(aNum - 1, zNum);
                }
                if (zNum > 0 && (idx > aSize || aSize == 0)) {
                    ans += 'z';
                    zNum--;
                    idx-=(int)aSize;
                } else {
                    ans += 'a';
                    aNum--;
                }
            }
            System.out.println(ans);
        }
        br.close();
    }

    static double solve(int aNum, int zNum) {
        if (aNum == 0 && zNum == 0) {
            return 1;
        }
        if (dp[aNum][zNum] != -1) {
            return dp[aNum][zNum];
        }
        dp[aNum][zNum] = 0;
        if (aNum > 0) {
            dp[aNum][zNum] += solve(aNum-1, zNum);
        }
        if (zNum > 0) {
            dp[aNum][zNum] += solve(aNum, zNum-1);
        }
        return dp[aNum][zNum];
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}