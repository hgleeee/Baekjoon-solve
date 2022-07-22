import java.util.*;
import java.io.*;

public class Main {

    static class Node implements Comparable<Node> {
        int idx, dist, previousNode;
        public Node(int idx, int dist, int previousNode) {
            this.idx = idx;
            this.dist = dist;
            this.previousNode = previousNode;
        }
        @Override
        public int compareTo(Node o) {
            return dist - o.dist;
        }
    }

    static final int INF = 987654321;
    static int n, m, sp, ep;
    static boolean[][] blocked;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        while (true) {
            st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken());
            m = stoi(st.nextToken());
            if (n == 0 && m == 0) {
                break;
            }
            st = new StringTokenizer(br.readLine());
            sp = stoi(st.nextToken());
            ep = stoi(st.nextToken());
            graph = new ArrayList[n];
            blocked = new boolean[n][n];
            for (int i = 0; i < n; ++i) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; ++i) {
                st = new StringTokenizer(br.readLine());
                int u = stoi(st.nextToken()), v = stoi(st.nextToken()), p = stoi(st.nextToken());
                graph[u].add(new int[]{v, p});
            }
            dijkstra(0);
            sb.append(dijkstra(1) + "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int dijkstra(int mode) {
        int ret = INF;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sp, 0, -1));
        boolean[] visited = new boolean[n];
        if (mode == 0) {
            ArrayList<Integer> reachIdx = new ArrayList<>();
            int[] min = new int[n];
            ArrayList<Integer>[] beforeNode = new ArrayList[n];
            for (int i = 0; i < n; ++i) {
                beforeNode[i] = new ArrayList<>();
            }
            int minDist = Integer.MAX_VALUE;
            while (!pq.isEmpty()) {
                Node now = pq.poll();
                if (now.idx == ep) {
                    if (minDist > now.dist) {
                        reachIdx.clear();
                        reachIdx.add(now.previousNode);
                        minDist = now.dist;
                    } else if (minDist == now.dist) {
                        reachIdx.add(now.previousNode);
                    }
                    continue;
                }
                if (visited[now.idx]) {
                    if (now.dist == min[now.idx]) {
                        beforeNode[now.idx].add(now.previousNode);
                    }
                    continue;
                }
                beforeNode[now.idx].add(now.previousNode);
                min[now.idx] = now.dist;
                visited[now.idx] = true;
                for (int[] next : graph[now.idx]) {
                    pq.offer(new Node(next[0], now.dist + next[1], now.idx));
                }
            }
            setBlocked(reachIdx, beforeNode);
        } else {
            while (!pq.isEmpty()) {
                Node now = pq.poll();
                if (now.idx == ep) {
                    ret = Math.min(ret, now.dist);
                    continue;
                }
                if (visited[now.idx]) {
                    continue;
                }
                visited[now.idx] = true;
                for (int[] next : graph[now.idx]) {
                    if (blocked[now.idx][next[0]]) continue;
                    pq.offer(new Node(next[0], now.dist + next[1], now.idx));
                }
            }
        }

        if (ret == INF) {
            return -1;
        }
        return ret;
    }

    static void setBlocked(ArrayList<Integer> reachIdx, ArrayList<Integer>[] beforeNode) {
        for (int ele : reachIdx) {
            blocked[ele][ep] = true;
            dfs(beforeNode, ele);
        }
    }

    static void dfs(ArrayList<Integer>[] beforeNode, int nowIdx) {
        for (int parent : beforeNode[nowIdx]) {
            if (parent == -1 || blocked[parent][nowIdx]) continue;
            blocked[parent][nowIdx] = true;
            dfs(beforeNode, parent);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}