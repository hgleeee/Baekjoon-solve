import java.io.*;
import java.util.*;

public class Main {

    static final int DIV = 10007;
    static int n, m, h;
    static List<Integer>[] blocks;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        h = stoi(st.nextToken());
        blocks = new List[n+1];
        for (int i = 1; i <= n; ++i) {
            blocks[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; ++i) {
            String input = br.readLine();
            String[] sub = input.split(" ");
            for (int j = 0; j < sub.length; ++j) {
                blocks[i].add(stoi(sub[j]));
            }
        }

        dp = new int[n+1][h+1];
        for (int i = 1; i <= n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(solve(1, h));
        br.close();
    }

    static int solve(int studentIdx, int height) {
        if (studentIdx == n+1) {
            if (height == 0) {
                return 1;
            }
            return 0;
        }
        if (dp[studentIdx][height] != -1) {
            return dp[studentIdx][height];
        }
        dp[studentIdx][height] = solve(studentIdx+1, height);
        for (int block : blocks[studentIdx]) {
            if (height - block < 0) continue;
            dp[studentIdx][height] += solve(studentIdx+1, height-block);
        }
        return dp[studentIdx][height] %= DIV;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}