import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] map, dir = new int[][]{{1,0},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = stoi(br.readLine());
        map = new int[n][n];
        for (int j = 0; j < n; ++j) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; ++i) {
                map[j][i] = stoi(st.nextToken());
            }
        }
        System.out.println(solve());
        br.close();
    }

    static int solve() {
        int[][][] dp = new int[3][n][n]; // 3은 우유 종류
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        if (map[0][0] == 0) {
            dp[0][0][0] = 1;
        } else {
            dp[2][0][0] = 0; // 0을 찾아야 하는 경우
        }

        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < n; ++i) {
                for (int milk = 0; milk < 3; ++milk) {
                    if (dp[milk][j][i] == -1) continue;
                    for (int a = 0; a < 2; ++a) {
                        int ni = i + dir[a][0], nj = j + dir[a][1];
                        int nextMilk = (milk == 2) ? 0 : milk+1;
                        if (!inRange(ni, nj)) continue;
                        if (nextMilk == map[nj][ni]) {
                            dp[nextMilk][nj][ni] = Math.max(dp[nextMilk][nj][ni], dp[milk][j][i]+1);
                        } else {
                            dp[milk][nj][ni] = Math.max(dp[milk][nj][ni], dp[milk][j][i]);
                        }
                    }
                }
            }
        }
        return Math.max(dp[0][n-1][n-1], Math.max(dp[1][n-1][n-1], dp[2][n-1][n-1]));
    }

    static boolean inRange(int x, int y) {
        return (x>=0 && y>=0 && x<n && y<n);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}