import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int idx, dist;
        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }

    static int n, m;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken()), b = stoi(st.nextToken()), c =stoi(st.nextToken());
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }
        System.out.println(dijkstra());

        br.close();
    }

    static int dijkstra() {
        int ret = -1;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->(o1.dist - o2.dist));
        boolean[] visited = new boolean[n+1];
        pq.offer(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.idx == n) {
                ret = now.dist;
                break;
            }
            if (visited[now.idx]) {
                continue;
            }
            visited[now.idx] = true;
            for (int[] next : graph[now.idx]) {
                pq.offer(new Node(next[0], now.dist + next[1]));
            }
        }
        return ret;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}