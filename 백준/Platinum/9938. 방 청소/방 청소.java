import java.util.*;
import java.io.*;

public class Main {

    static int n, l;
    static int[] uf;
    static boolean[] filled;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        l = stoi(st.nextToken());
        uf = new int[l+1];
        filled = new boolean[l+1];
        for (int i = 1; i <= l; ++i) {
            uf[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            sb.append(solve(stoi(st.nextToken()), stoi(st.nextToken())) + "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static String solve(int x, int y) {
        String ret = "LADICA";
        if (!filled[x]) {
            filled[x] = true;
            union(x, y);
        } else if (!filled[y]) {
            filled[y] = true;
            union(y, x);
        } else if (!filled[find(x)]) {
            filled[find(x)] = true;
            union(x, y);
        } else if (!filled[find(y)]) {
            filled[find(y)] = true;
            union(y, x);
        } else {
            ret = "SMECE";
        }
        return ret;
    }

    static int find(int x) {
        if (uf[x] == x) return x;
        return uf[x] = find(uf[x]);
    }

    static void union(int a, int b) {
        uf[find(a)] = find(b);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}