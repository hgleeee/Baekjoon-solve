import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int idx, num;
        public Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    static int n, k, m;
    static boolean[] visited, visitedHyperTube;
    static boolean[][] inform;
    static ArrayList<Integer>[] hyperTubes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        k = stoi(st.nextToken());
        m = stoi(st.nextToken());
        visited = new boolean[n+1];
        visitedHyperTube = new boolean[m];
        hyperTubes = new ArrayList[m];
        inform = new boolean[n+1][m];
        for (int i = 0; i < m; ++i) {
            hyperTubes[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; ++j) {
                int a = stoi(st.nextToken());
                hyperTubes[i].add(a);
                inform[a][i] = true;
            }
        }
        System.out.println(bfs());
        br.close();
    }

    static int bfs() {
        int ret = -1;
        Queue<Node> myQ = new LinkedList<>();
        myQ.offer(new Node(1, 1));
        visited[1] = true;
        while (!myQ.isEmpty()) {
            Node now = myQ.poll();
            if (now.idx == n) {
                ret = now.num;
                break;
            }
            for (int i = 0; i < m; ++i) {
                if (!inform[now.idx][i] || visitedHyperTube[i]) continue;
                visitedHyperTube[i] = true;
                for (int nextIdx : hyperTubes[i]) {
                    if (nextIdx == now.idx || visited[nextIdx]) continue;
                    visited[nextIdx] = true;
                    myQ.offer(new Node(nextIdx, now.num+1));
                }
            }
        }
        return ret;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}