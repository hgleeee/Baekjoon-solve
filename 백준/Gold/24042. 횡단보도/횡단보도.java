import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int idx;
        long nowTime;

        public Node(int idx, long nowTime) {
            this.idx = idx;
            this.nowTime = nowTime;
        }

        @Override
        public int compareTo(Node o) {
            if (nowTime - o.nowTime < 0)
                return -1;
            return 1;
        }
    }

    static class Region {
        int idx, period;

        public Region(int idx, int period) {
            this.idx = idx;
            this.period = period;
        }

        long afterCrossTime(long nowTime) {
            if (nowTime % m == period) {
                return nowTime+1;
            }
            if (nowTime % m > period) {
                return m * (nowTime / m + 1) + period + 1;
            }
            return nowTime + (period - nowTime % m) + 1;
        }
    }

    static int n, m;
    static List<Region>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; ++i) {
            graph[i] = new ArrayList();
        }
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken()), b = stoi(st.nextToken());
            graph[a].add(new Region(b, i));
            graph[b].add(new Region(a, i));
        }
        System.out.println(dijkstra());
        br.close();
    }

    static long dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n+1];
        pq.offer(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.idx == n) {
                return now.nowTime;
            }
            if (visited[now.idx]) {
                continue;
            }
            visited[now.idx] = true;
            for (Region region : graph[now.idx]) {
                pq.offer(new Node(region.idx, region.afterCrossTime(now.nowTime)));
            }
        }
        return -1;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}