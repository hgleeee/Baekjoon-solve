import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] graph;
    static int k, n, V;
    static int[][] capacity, flow;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int tst = 0;
        while (++tst > 0) {
            st = new StringTokenizer(br.readLine());
            k = stoi(st.nextToken());
            n = stoi(st.nextToken());
            if (k == 0 && n == 0) break;
            V = (n+1)*2;
            capacity = new int[V][V];
            flow = new int[V][V];
            graph = new ArrayList[n+1];
            for (int i = 1; i <= n; ++i) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 1; i <= n; ++i) {
                String[] sub = br.readLine().split(" ");
                for (int j = 0; j < sub.length; ++j) {
                    graph[i].add(stoi(sub[j]));
                }
            }
            setCapacity();
            int ret = maxFlow(3, 4);
            sb.append("Case ").append(tst).append(":\n");
            if (ret >= k) {
                List<Integer> route = new ArrayList<>();
                route.add(1);
                cnt = k;
                searchRoute(0, 3, 4, route);
            } else {
                sb.append("Impossible\n");
            }
            sb.append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static void setCapacity() {
        for (int i = 1; i <= n; ++i) {
            capacity[i*2][i*2+1] = 1;
            for (int next : graph[i]) {
                capacity[i*2+1][next*2] = 1;
            }
        }
    }

    static int cnt;
    static void searchRoute(int idx, int now, int sink, List<Integer> route) {
        if (now == sink) {
            if (cnt > 0) {
                for (int i = 0; i < route.size(); ++i) {
                    sb.append(route.get(i) + " ");
                }
                sb.append("\n");
                cnt--;
            }
            return;
        }
        if (now % 2 == 0) {
            if (flow[now][now+1] > 0) {
                flow[now][now+1]--;
                searchRoute(idx, now + 1, sink, route);
            }
            return;
        }

        for (int next : graph[now/2]) {
            if (flow[now][next*2] > 0) {
                route.add(next);
                flow[now][next*2]--;
                searchRoute(idx+1, next*2, sink, route);
                route.remove(route.size()-1);
            }
        }
    }

    static int maxFlow(int src, int sink) {
        int ret = 0;
        Queue<Integer> queue = new LinkedList<>();
        int[] parent = new int[2*(n+1)];
        while (true) {
            queue.clear();
            queue.offer(src);
            Arrays.fill(parent, 0);
            parent[src] = -1;
            while (!queue.isEmpty() && parent[sink] == 0) {
                int now = queue.poll();
                for (int next = 1; next <= 2*n+1; ++next) {
                    if (parent[next] == 0 && capacity[now][next] - flow[now][next] > 0) {
                        queue.offer(next);
                        parent[next] = now;
                    }
                }
            }
            if (parent[sink] == 0) break; // 증가 경로 X
            for (int i = sink; i != src; i = parent[i]) {
                flow[parent[i]][i]++;
                flow[i][parent[i]]--;
            }
            ret += 1;
        }
        return ret;
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}