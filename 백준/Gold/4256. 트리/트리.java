import java.util.*;
import java.io.*;

public class Main {

    static int t, n;
    static int[] arr1, arr2;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        t = stoi(br.readLine());
        while (--t >= 0) {
            n = stoi(br.readLine());
            arr1 = new int[n+1];
            arr2 = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; ++i) {
                arr1[i] = stoi(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; ++i) {
                arr2[i] = stoi(st.nextToken());
            }
            dfs(0,0,n);
            sb.append("\n");
        }
        System.out.print(sb.toString());

    }
    static void dfs(int root, int s, int e) {
        int rootIdx = arr1[root];
        for (int i = s; i < e; i++) {
            if (arr2[i] == rootIdx) {
                dfs(root + 1, s, i);
                dfs(root + i + 1 - s, i + 1, e);
                sb.append(rootIdx + " ");
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}