import java.io.*;
import java.util.*;

public class Main {

    static int[][] capacity, flow;
    static int n;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        int p = stoi(st.nextToken());
        capacity = new int[2*n+2][2*n+2];
        flow = new int[2*n+2][2*n+2];
        for (int i = 2; i <= n; ++i) {
            capacity[i*2][i*2+1] = 1;
        }
        capacity[2][3] = INF;
        for (int i = 0; i < p; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            capacity[2*a+1][2*b] = 1;
            capacity[2*b+1][2*a] = 1;
        }
        System.out.println(maxFlow(1, 2));
    }

    static int maxFlow(int src, int sink) {
        int ret = 0;
        Queue<Integer> myQ = new LinkedList<>();
        int[] parent = new int[n*2+2];
        while (true) {
            myQ.clear();
            myQ.offer(src*2);
            Arrays.fill(parent, -1);
            parent[src*2] = src*2;
            while (!myQ.isEmpty() && parent[sink*2] == -1) {
                int now = myQ.poll();
                for (int next = 1; next <= 2*n+1; ++next) {
                    if (parent[next] == -1 && capacity[now][next] > flow[now][next]) {
                        myQ.offer(next);
                        parent[next] = now;
                    }
                }
            }
            if (parent[sink*2] == -1) {
                break;
            }
            for (int i = sink*2; i != src*2; i = parent[i]) {
                flow[parent[i]][i]++;
                flow[i][parent[i]]--;
            }
            ret++;
        }
        return ret;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}