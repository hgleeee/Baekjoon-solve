import java.util.*;
import java.io.*;

public class Main {

    static int[][] map, dir = new int[][]{{-1, 1},{0,1},{1,1}};
    static int[][][] dp;
    static int n, m;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        map = new int[n][m];
        for (int j = 0; j < n; ++j) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; ++i) {
                map[j][i] = stoi(st.nextToken());
            }
        }
        dp = new int[n][m][4];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int ans = INF;
        for (int i = 0; i < m; ++i) {
            ans = Math.min(ans, solve(i, 0, 3));
        }
        System.out.println(ans);
        br.close();
    }

    static int solve(int x, int y, int beforeDir) {
        if (y == n) {
            return 0;
        }
        if (dp[y][x][beforeDir] != -1) {
            return dp[y][x][beforeDir];
        }
        dp[y][x][beforeDir] = map[y][x];
        int ref = INF;
        for (int i = 0; i < 3; ++i) {
            int nx = x + dir[i][0], ny = y + dir[i][1];
            if (!inRange(nx) || beforeDir == i) continue;
            ref = Math.min(ref, solve(nx, ny, i));
        }
        dp[y][x][beforeDir] += ref;
        return dp[y][x][beforeDir];
    }

    static boolean inRange(int x) {
        return (x>=0 && x<m);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}