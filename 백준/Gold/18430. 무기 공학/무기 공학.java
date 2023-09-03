import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int ans = 0;
    static int[][] cost;
    static int[][][] shape = new int[][][]{{{1,0},{0,1},{0,0}}, {{1,0},{0,-1},{0,0}}, {{-1,0},{0,1},{0,0}}, {{-1,0},{0,-1},{0,0}}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        cost = new int[n][m];
        for (int j = 0; j < n; ++j) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; ++i) {
                cost[j][i] = stoi(st.nextToken());
            }
        }
        solve(0, 0, new boolean[n][m], 0);
        System.out.println(ans);
        br.close();
    }

    static void solve(int x, int y, boolean[][] used, int total) {
        if (x == 0 && y == n) {
            ans = Math.max(total, ans);
            return;
        }
        int nextX = (x == m-1) ? 0 : x+1, nextY = (x == m-1) ? y+1 : y;
        for (int i = 0; i < 4; ++i) {
            boolean canMake = true;
            for (int j = 0; j < 3; ++j) {
                int nx = x + shape[i][j][0], ny = y + shape[i][j][1];
                if (!inRange(nx, ny) || used[ny][nx]) {
                    canMake = false;
                    break;
                }
            }
            if (canMake) {
                int ref = 0;
                for (int j = 0; j < 3; ++j) {
                    int nx = x + shape[i][j][0], ny = y + shape[i][j][1];
                    ref += cost[ny][nx];
                    used[ny][nx] = true;
                }
                ref += cost[y][x];
                solve(nextX, nextY, used, total + ref);
                for (int j = 0; j < 3; ++j) {
                    int nx = x + shape[i][j][0], ny = y + shape[i][j][1];
                    used[ny][nx] = false;
                }
            }
        }
        solve(nextX, nextY, used, total);
    }

    static boolean inRange(int x, int y) {
        return (x >= 0 && y >= 0 && x < m && y < n);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}