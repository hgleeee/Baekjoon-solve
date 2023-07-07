import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 1000000007;
    static int n, m;
    static int[] streetCap, uf;
    static int[][] streetInform;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        streetCap = new int[m];
        streetInform = new int[m][2];
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            streetInform[i][0] = stoi(st.nextToken());
            streetInform[i][1] = stoi(st.nextToken());
        }
        System.out.println(solve());
        br.close();
    }

    static int solve() {
        Set<Integer> minCutStreet = new HashSet<>();
        uf = new int[n];
        for (int i = 0; i < n; ++i) {
            uf[i] = i;
        }

        for (int i = m-1; i >= 0; --i) {
            if (!union(streetInform[i][0] ,streetInform[i][1])) {
                minCutStreet.add(i);
            }
        }
        return calculate(minCutStreet);
    }

    static int calculate(Set<Integer> minCutStreet) {
        if (minCutStreet.isEmpty()) {
            return 0;
        }
        long ret = 0, val = 1;
        for (int i = 0; i < m; ++i) {
            if (minCutStreet.contains(i)) {
                ret += val;
            }
            val *= 3;
            val %= MOD;
            ret %= MOD;
        }
        return (int)ret;
    }

    static int find(int x) {
        if (uf[x] == x) return x;
        return uf[x] = find(uf[x]);
    }

    static int notSaveFind(int x) {
        if (uf[x] == x) return x;
        return notSaveFind(uf[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        int originA = uf[a], originB = uf[b];
        if (a > b) {
            uf[a] = b;
        } else {
            uf[b] = a;
        }
        if (notSaveFind(n-1) == 0) {
            uf[a] = originA;
            uf[b] = originB;
            return false;
        }
        return true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}