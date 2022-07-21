import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] A, B;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; ++i) {
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            for (int j = 0; j < a; ++j) {
                graph[i].add(stoi(st.nextToken()));
            }
        }

        visited = new boolean[n+1];
        A = new int[n+1];
        B = new int[m+1];
        Arrays.fill(A, -1);
        Arrays.fill(B, -1);
        int cnt = 0;
        for (int idx = 1; idx <= n; ++idx) {
            if (A[idx] == -1) {
                Arrays.fill(visited, false);
                if (dfs(idx)) cnt++;
            }
        }
        System.out.println(cnt);
        br.close();
    }

    static boolean dfs(int a) {
        visited[a] = true;
        for (int b : graph[a]) {
            if (B[b] == -1 || !visited[B[b]] && dfs(B[b])) {
                A[a] = b;
                B[b] = a;
                return true;
            }
        }
        return false;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}