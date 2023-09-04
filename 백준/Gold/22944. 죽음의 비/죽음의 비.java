import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 987654321;
    static int n, h, d;
    static int ans = MAX;
    static int[] startPos = new int[2], endPos = new int[2];
    static List<int[]> uInfo = new ArrayList<>(); // 우산 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        h = stoi(st.nextToken());
        d = stoi(st.nextToken());
        for (int j = 0; j < n; ++j) {
            String input = br.readLine();
            for (int i = 0; i < n; ++i) {
                char x = input.charAt(i);
                if (x == 'S') {
                    startPos[0] = i;
                    startPos[1] = j;
                } else if (x == 'E') {
                    endPos[0] = i;
                    endPos[1] = j;
                } else if (x == 'U') {
                    uInfo.add(new int[]{i, j});
                }
            }
        }
        solve(startPos[0], startPos[1], h, new boolean[uInfo.size()], 0, false);
        if (ans == MAX) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
        br.close();
    }

    static void solve(int x, int y, int hp, boolean[] used, int dist, boolean havingUmb) {
        if (x == endPos[0] && y == endPos[1]) {
            if (hp > 0) {
                ans = Math.min(ans, dist);
            }
            return;
        }
        if (hp <= 0) {
            return;
        }
        for (int i = 0; i < used.length; ++i) {
            if (used[i]) continue;
            used[i] = true;
            int unitDist = Math.abs(x - uInfo.get(i)[0]) + Math.abs(y - uInfo.get(i)[1]);
            int reduced = unitDist - 1;
            if (havingUmb) {
                if (d - 1 >= reduced) {
                    reduced = 0;
                } else {
                    reduced -= (d-1);
                }
            }
            solve(uInfo.get(i)[0], uInfo.get(i)[1], hp - reduced, used, dist + unitDist, true);
            used[i] = false;
        }
        int shortcutDist = Math.abs(x - endPos[0]) + Math.abs(y - endPos[1]);
        int reduced = shortcutDist - 1;
        if (havingUmb) {
            if (d - 1 >= reduced) {
                reduced = 0;
            } else {
                reduced -= (d-1);
            }
        }
        solve(endPos[0], endPos[1], hp - reduced, used, dist + shortcutDist, false);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}