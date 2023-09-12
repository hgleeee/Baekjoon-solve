import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static List<int[]>[] graph, reverseGraph;
    static int[] parentNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = stoi(br.readLine());
        m = stoi(br.readLine());
        graph = new List[n+1];
        reverseGraph = new List[n+1];
        for (int i = 1; i <= n; ++i) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }
        parentNum = new int[n+1];
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken()), b = stoi(st.nextToken()), c = stoi(st.nextToken());
            graph[a].add(new int[]{b, c});
            reverseGraph[b].add(new int[]{a, c});
            parentNum[b]++;
        }
        st = new StringTokenizer(br.readLine());
        int start = stoi(st.nextToken()), end = stoi(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        int[] takeTime = new int[n+1];
        while (!queue.isEmpty()) {
            int nowIdx = queue.poll();
            for (int[] next : graph[nowIdx]) {
                int nextIdx = next[0], cost = next[1];
                takeTime[nextIdx] = Math.max(takeTime[nextIdx], takeTime[nowIdx] + cost);
                if (--parentNum[nextIdx] == 0) {
                    queue.offer(nextIdx);
                }
            }
        }
        int meetTime = takeTime[end];

        Queue<Integer> reverseQueue = new LinkedList<>();
        reverseQueue.offer(end);

        int cnt = 0;
        boolean[] visited = new boolean[n+1];
        visited[end] = true;
        while (!reverseQueue.isEmpty()) {
            int nowIdx = reverseQueue.poll();
            for (int[] prev : reverseGraph[nowIdx]) {
                int prevIdx = prev[0], cost = prev[1];
                if (cost == takeTime[nowIdx] - takeTime[prevIdx]) {
                    cnt++;
                    if (!visited[prevIdx]) {
                        reverseQueue.offer(prevIdx);
                        visited[prevIdx] = true;
                    }
                }
            }
        }

        System.out.println(meetTime + "\n" + cnt);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}