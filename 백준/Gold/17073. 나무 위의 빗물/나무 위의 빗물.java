import java.io.*;
import java.util.*;

public class Main {

    static int n, w;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        w = stoi(st.nextToken());
        graph = new List[n+1];
        for (int i = 1; i <= n; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n-1; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = stoi(st.nextToken()), v = stoi(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        double ans = (double)w / getLeafNum();
        System.out.println(ans);               
        br.close();
    }

    static int getLeafNum() {
        int ret = 0;
        visited = new boolean[n+1];
        visited[1] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int nowIdx = queue.poll();
            boolean exist = false;
            for (int nextIdx : graph[nowIdx]) {
                if (visited[nextIdx]) continue;
                visited[nextIdx] = true;
                exist = true;
                queue.offer(nextIdx);
            }
            if (!exist) ret++;
        }
        return ret;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}