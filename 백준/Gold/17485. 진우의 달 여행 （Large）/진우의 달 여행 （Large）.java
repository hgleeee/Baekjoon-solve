import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 987654321;
    static int n, m;
    static int[][] needAmount, dir = new int[][]{{-1, 1}, {0, 1}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        needAmount = new int[n][m];
        for (int j = 0; j < n; ++j) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; ++i) {
                needAmount[j][i] = stoi(st.nextToken());
            }
        }
        System.out.println(solve());
        br.close();
    }

    static int solve() {
        int[][][] dp = new int[n][m][3]; // 3은 이전에 어떤 방향으로 왔는지 가리킴
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                Arrays.fill(dp[j][i], INF);
            }
        }
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp[0][i], needAmount[0][i]);
        }
        for (int j = 0; j < n-1; ++j) {
            for (int i = 0; i < m; ++i) {
                for (int k = 0; k < 3; ++k) {
                    for (int r = 0; r < 3; ++r) {
                        if (r == k) continue;
                        int nextI = i + dir[r][0], nextJ = j + dir[r][1];
                        if (!inRange(nextI, nextJ)) continue;
                        dp[nextJ][nextI][r] = Math.min(dp[nextJ][nextI][r], dp[j][i][k] + needAmount[nextJ][nextI]);
                    }
                }
            }
        }

        int ret = INF;
        for (int i = 0; i < m; ++i) {
            for (int k = 0; k < 3; ++k) {
                ret = Math.min(dp[n-1][i][k], ret);
            }
        }
        return ret;
    }

    static boolean inRange(int x, int y) {
        return (x >= 0 && y >= 0 && x < m && y < n);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}