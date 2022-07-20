import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static ArrayList<Integer>[] graph;
    static int[] depth;
    static int[][] parent;

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
            int a = stoi(st.nextToken()), b = stoi(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        depth = new int[n+1];
        Arrays.fill(depth, -1);
        depth[1] = 0;
        parent = new int[n+1][20];
        dfs(1, -1);
        bottomUpDp();

        m = stoi(br.readLine());
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = stoi(st.nextToken()), v = stoi(st.nextToken());
            sb.append(lca(u, v)+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int lca(int u, int v) {
        int deep = -1, other = -1;
        if (depth[u] < depth[v]) {
            deep = v;
            other = u;
        } else {
            deep = u;
            other = v;
        }
        int diff = Math.abs(depth[u] - depth[v]);
        int idx = 0;
        while (diff != 0) {
            if (diff % 2 == 1) deep = parent[deep][idx];
            idx++;
            diff /= 2;
        }

        if (deep != other) {
            for (int i = 19; i >= 0; --i) {
                if (parent[deep][i] != 0 && parent[deep][i] != parent[other][i]) {
                    deep = parent[deep][i];
                    other = parent[other][i];
                }
            }
            deep = parent[deep][0];
        }
        return deep;
    }

    static void bottomUpDp() {
        for (int i = 1; i < 20; ++i) {
            for (int j = 1; j <= n; ++j) {
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }

    static void dfs(int idx, int parentIdx) {
        if (parentIdx != -1) {
            depth[idx] = depth[parentIdx] + 1;
            parent[idx][0] = parentIdx;
        }
        for (int child : graph[idx]) {
            if (depth[child] != -1) continue;
            dfs(child, idx);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}