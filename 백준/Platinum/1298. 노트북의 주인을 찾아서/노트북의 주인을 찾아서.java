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
        A = new int[n+1];
        B = new int[n+1];
        Arrays.fill(A, -1);
        Arrays.fill(B, -1);
        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken()), b= stoi(st.nextToken());
            graph[a].add(b);
        }
        visited = new boolean[n+1];
        int cnt = 0;
        for (int i = 1; i <= n; ++i) {
            if (A[i] == -1) {
                Arrays.fill(visited, false);
                if (dfs(i)) cnt++;
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