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

    static int n, d;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        d = stoi(st.nextToken());
        graph = new ArrayList[10001];
        for (int i = 0; i <= 10000; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken()), b = stoi(st.nextToken()), c= stoi(st.nextToken());
            graph[a].add(new int[]{b, c});
        }
        System.out.println(bfs());
        br.close();
    }

    static int bfs() {
        int ret = -1;
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.dist-o2.dist;
            }
        });
        boolean[] visited = new boolean[10001];
        pq.offer(new Node(0, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.idx == d) {
                ret = now.dist;
                break;
            }
            if (visited[now.idx]) {
                continue;
            }
            visited[now.idx] = true;
            for (int[] next : graph[now.idx]) {
                if (next[0] > d) continue;
                pq.offer(new Node(next[0], now.dist + next[1]));
            }
            if (now.idx < d) {
                pq.offer(new Node(now.idx + 1, now.dist+1));
            }
        }
        return ret;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}