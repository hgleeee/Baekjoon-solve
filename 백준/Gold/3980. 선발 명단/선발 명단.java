import java.io.*;
import java.util.*;

public class Main {

    static int ans;
    static int[][] ability = new int[11][11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int tst = stoi(br.readLine());
        while (tst-- > 0) {
            ans = -1;
            for (int i = 0; i < 11; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; ++j) {
                    ability[i][j] = stoi(st.nextToken());
                }
            }
            backtracking(0, new boolean[11], 0);
            sb.append(ans + "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static void backtracking(int idx, boolean[] positionFilled, int sum) {
        if (idx == 11) {
            ans = Math.max(ans, sum);
            return;
        }
        for (int i = 0; i < 11; ++i) {
            if (positionFilled[i] || ability[idx][i] == 0) continue;
            positionFilled[i] = true;
            backtracking(idx+1, positionFilled, sum + ability[idx][i]);
            positionFilled[i] = false;
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}