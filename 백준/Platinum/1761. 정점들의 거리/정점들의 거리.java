import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] parent;
    static int[] depth, dist;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        n = stoi(br.readLine());
        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n-1; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken()), b = stoi(st.nextToken()), d = stoi(st.nextToken());
            graph[a].add(new int[]{b, d});
            graph[b].add(new int[]{a, d});
        }
        depth = new int[n+1];
        dist = new int[n+1];
        Arrays.fill(depth, -1);
        depth[1] = 0;
        parent = new int[n+1][18];
        dfs(1, -1);
        dp();

        m = stoi(br.readLine());
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken()), b = stoi(st.nextToken());
            int ancient = lca(a, b);
            sb.append((dist[a] + dist[b] - 2*dist[ancient]) + "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static void dp() {
        for (int i = 1; i < 18; ++i) {
            for (int j = 1; j <= n; ++j) {
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }

    static int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[u] - depth[v];
        if (diff != 0) {
            int idx = 0;
            while (diff > 0) {
                if (diff % 2 == 1) {
                    u = parent[u][idx];
                }
                diff /= 2;
                idx++;
            }
        }
        if (u != v) {
            for (int i = 17; i >= 0; --i) {
                if (parent[u][i] != 0 && parent[u][i] != parent[v][i]) {
                    u = parent[u][i];
                    v = parent[v][i];
                }
            }
            u = parent[u][0];
        }
        return u;
    }

    static void dfs(int idx, int lastIdx) {
        if (lastIdx != -1) {
            parent[idx][0] = lastIdx;
            depth[idx] = depth[lastIdx] + 1;
        }
        for (int[] child : graph[idx]) {
            if (depth[child[0]] != -1) continue;
            dist[child[0]] = dist[idx] + child[1];
            dfs(child[0], idx);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}