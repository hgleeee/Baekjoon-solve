import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int[][] board = new int[10][10];
        for (int j = 0; j < 10; ++j) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 10; ++i) {
                board[j][i] = stoi(st.nextToken());
            }
        }
        fill(0, 0, board, new int[]{0, 5, 5, 5, 5, 5}, 0);
        if (ans == INF) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
        br.close();
    }

    static int ans = INF;

    static void fill(int x, int y, int[][] board, int[] remain, int cnt) {
        if (x == 0 && y == 10) {
            ans = Math.min(ans, cnt);
            return;
        }
        int nx = (x == 9) ? 0 : x+1, ny = (x == 9) ? y+1 : y;
        if (board[y][x] == 0) {
            fill(nx, ny, board, remain, cnt);
            return;
        }
        for (int i = 1; i <= 5; ++i) {
            if (remain[i] == 0 || !canCover(x, y, board, i)) continue;
            coverOrNot(x, y, board, i, 0);
            remain[i]--;
            fill(nx, ny, board, remain, cnt + 1);
            coverOrNot(x, y, board, i, 1);
            remain[i]++;
        }
    }

    static void coverOrNot(int x, int y, int[][] board, int size, int state) {
        for (int j = 0; j < size; ++j) {
            for (int i = 0; i < size; ++i) {
                int nx = x + i, ny = y + j;
                board[ny][nx] = state;
            }
        }
    }

    static boolean canCover(int x, int y, int[][] board, int size) {
        for (int j = 0; j < size; ++j) {
            for (int i = 0; i < size; ++i) {
                int nx = x + i, ny = y + j;
                if (!inRange(nx, ny) || board[ny][nx] == 0) return false;
            }
        }
        return true;
    }

    static boolean inRange(int x, int y) {
        return (x >= 0 && y >= 0 && x < 10 && y < 10);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}