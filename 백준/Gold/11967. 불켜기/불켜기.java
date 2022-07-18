import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static boolean[][] visited, bright;
    static ArrayList<int[]>[][] graph;
    static int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        visited = new boolean[n+1][n+1];
        bright = new boolean[n+1][n+1];
        graph = new ArrayList[n+1][n+1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                graph[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken()), y = stoi(st.nextToken()), a = stoi(st.nextToken()), b = stoi(st.nextToken());
            graph[x][y].add(new int[]{a, b});
        }
        System.out.println(bfs());
        br.close();
    }

    static int bfs() {
        int ret = 1;
        Queue<int[]> myQ = new LinkedList<>();
        myQ.offer(new int[]{1, 1});
        visited[1][1] = true;
        bright[1][1] = true;
        while (!myQ.isEmpty()) {
            int[] now = myQ.poll();
            for (int[] node : graph[now[0]][now[1]]) {
                if (bright[node[1]][node[0]]) continue;
                bright[node[1]][node[0]] = true;
                ret++;
                for (int i = 0; i < 4; ++i) {
                    int checkX = node[0] + dir[i][0], checkY = node[1] + dir[i][1];
                    if (!inRange(checkX, checkY)) continue;
                    if (visited[checkY][checkX]) {
                        visited[node[1]][node[0]] = true;
                        myQ.offer(new int[]{node[0], node[1]});
                        break;
                    }
                }
            }
            for (int i = 0; i < 4; ++i) {
                int nx = now[0] + dir[i][0], ny = now[1] + dir[i][1];
                if (!inRange(nx, ny) || !bright[ny][nx] || visited[ny][nx]) continue;
                visited[ny][nx] = true;
                myQ.offer(new int[]{nx, ny});
            }
        }

        return ret;
    }

    static boolean inRange(int x, int y) {
        return (x>=1 && y>=1 && x<=n && y<=n);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}