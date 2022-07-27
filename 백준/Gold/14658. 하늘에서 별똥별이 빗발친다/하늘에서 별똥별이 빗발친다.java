import java.util.*;
import java.io.*;

public class Main {

    static int n, m, l, k;
    static int[][] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        l = stoi(st.nextToken());
        k = stoi(st.nextToken());
        stars = new int[k][2];
        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = stoi(st.nextToken());
            stars[i][1] = stoi(st.nextToken());
        }
        System.out.println(k - solve());
        br.close();
    }

    static int solve() {
        int ret = 0;
        for (int[] s1 : stars) {
            for (int[] s2 : stars) {
                ret = Math.max(ret, sub(s1[0], s2[1]));
            }
        }
        return ret;
    }

    static int sub(int a, int b) {
        int ret = 0;
        for (int[] s : stars) {
            if (a <= s[0] && s[0] <= a+l && b <= s[1] && s[1] <= b+l) {
                ret++;
            }
        }
        return ret;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}