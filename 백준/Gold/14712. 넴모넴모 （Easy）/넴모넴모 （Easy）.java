import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        board = new boolean[n+1][m+1];
        solve(1, 1);
        System.out.println(ans);
        br.close();
    }

    static int ans = 0;

    static void solve(int x, int y) {
        if (x == 1 && y == n+1) {
            ans++;
            return;
        }
        int nextX = (x == m) ? 1 : x+1, nextY = (x == m) ? y+1 : y;
        if (!(board[y-1][x] && board[y][x-1] && board[y-1][x-1])) {
            board[y][x] = true;
            solve(nextX, nextY);
            board[y][x] = false;
        }
        solve(nextX, nextY);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}