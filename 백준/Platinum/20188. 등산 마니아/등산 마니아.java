import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int idx, depth;

        public Node(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }

        @Override
        public int compareTo(Node o) {
            return o.depth-depth;
        }
    }

    static boolean[] visited;
    static int[] depthArr;
    static List<Integer>[] graph;
    static List<Integer> leafNodeIdx = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = stoi(br.readLine());
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
        visited = new boolean[n+1];
        depthArr = new int[n+1];
        dfs(1, 0);
        System.out.println(bfs(n));
        br.close();
    }

    static int bfs(int n) {
        int ret = 0;
        int[] cost = new int[n+1], childNum = new int[n+1];
        boolean[] bfsVisited = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int leaf : leafNodeIdx) {
            pq.offer(new Node(leaf, depthArr[leaf]));
        }
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.idx == 1) continue;
            if (bfsVisited[now.idx]) {
                continue;
            }
            bfsVisited[now.idx] = true;
            ret += n*(n-1)/2 - (n-childNum[now.idx]-1) * (n-childNum[now.idx]-2)/2;
            for (int parent : graph[now.idx]) {
                if (bfsVisited[parent]) {
                    continue;
                }
                childNum[parent] += childNum[now.idx] + 1;
                pq.offer(new Node(parent, depthArr[parent]));
            }
        }
        return ret;
    }

    static void dfs(int nodeIdx, int depth) {
        visited[nodeIdx] = true;
        depthArr[nodeIdx] = depth;
        boolean isLeaf = true;
        for (int child : graph[nodeIdx]) {
            if (visited[child]) continue;
            isLeaf = false;
            dfs(child, depth+1);
        }
        if (isLeaf) leafNodeIdx.add(nodeIdx);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}