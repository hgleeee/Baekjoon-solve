import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int idx, remainFreeNum;
        public Node(int idx, int remainFreeNum) {
            this.idx = idx;
            this.remainFreeNum = remainFreeNum;
        }
    }

    static int n, p, k;
    static ArrayList<int[]> graph[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        p = stoi(st.nextToken());
        k = stoi(st.nextToken());

        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < p; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken()), b = stoi(st.nextToken()), c = stoi(st.nextToken());
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        if (n == 1) {
            System.out.println(0);
        } else {
            int ans = binarySearch(0, 1000001);
            if (ans == 1000001) {
                System.out.println(-1);
            } else {
                System.out.println(ans);
            }
        }

        br.close();
    }

    static int binarySearch(int lo, int hi) {
        if (lo == hi) {
            return lo;
        }
        int mid = (lo+hi) /2;
        if (bfs(mid)) {
            return binarySearch(lo, mid);
        }
        return binarySearch(mid+1, hi);
    }

    static boolean bfs(int money) {
        boolean ret = false;
        Queue<Node> myQ = new LinkedList<>();
        boolean visited[][] = new boolean[n+1][k+1];
        myQ.offer(new Node(1, k));
        visited[1][k] = true;
        while (!myQ.isEmpty()) {
            Node now = myQ.poll();
            if (now.idx == n) {
                ret = true;
                break;
            }
            for (int[] next : graph[now.idx]) {
                if (next[1] > money) {
                    if (now.remainFreeNum == 0 || visited[next[0]][now.remainFreeNum-1]) continue;
                    visited[next[0]][now.remainFreeNum-1] = true;
                    myQ.offer(new Node(next[0], now.remainFreeNum-1));
                } else {
                    if (visited[next[0]][now.remainFreeNum]) continue;
                    visited[next[0]][now.remainFreeNum] = true;
                    myQ.offer(new Node(next[0], now.remainFreeNum));
                }
            }
        }
        return ret;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}