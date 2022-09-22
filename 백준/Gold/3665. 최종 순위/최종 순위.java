import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int t = stoi(br.readLine());
        for (int tst = 0; tst < t; ++tst) {
            int n = stoi(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] lastYearInfo = new int[n];
            for (int i = 0; i < n; ++i) {
                lastYearInfo[i] = stoi(st.nextToken());
            }
            List<Integer>[] graph = new ArrayList[n+1];
            for (int i = 1; i <= n; ++i) {
                graph[i] = new ArrayList<>();
            }
            int[] parentNum = new int[n+1];
            for (int i = 0; i < n; ++i) {
                for (int j = i+1; j < n; ++j) {
                    graph[lastYearInfo[i]].add(lastYearInfo[j]);
                    parentNum[lastYearInfo[j]]++;
                }
            }
            int m = stoi(br.readLine());
            for (int i = 0; i < m; ++i) {
                st = new StringTokenizer(br.readLine());
                int a = stoi(st.nextToken()), b = stoi(st.nextToken());
                if (graph[a].contains(b)) {
                    parentNum[b]--;
                    parentNum[a]++;
                    graph[a].remove(Integer.valueOf(b));
                    graph[b].add(a);
                } else {
                    parentNum[a]--;
                    parentNum[b]++;
                    graph[b].remove(Integer.valueOf(a));
                    graph[a].add(b);
                }
            }
            List<Integer> ans = topologicalSort(n, parentNum, graph);
            if (ans.size() == n) {
                for (int ele : ans) {
                    sb.append(ele + " ");
                }
                sb.append("\n");
            } else {
                sb.append("IMPOSSIBLE\n");
            }
        }
        System.out.print(sb.toString());
        br.close();
    }

    static List<Integer> topologicalSort(int n, int[] parentNum, List<Integer>[] graph) {
        List<Integer> ret = new ArrayList<>();
        Queue<Integer> myQ = new LinkedList<>();
        for (int i = 1; i <= n; ++i) {
            if (parentNum[i] == 0) myQ.offer(i);
        }
        while (!myQ.isEmpty()) {
            int now = myQ.poll();
            ret.add(now);
            for (int child : graph[now]) {
                if (--parentNum[child] == 0) {
                    myQ.offer(child);
                }
            }
        }
        return ret;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
