import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int x, y, dist;
        public int compareTo(Edge e) {
            return dist - e.dist;
        }
        Edge(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static int[] uf;
    static int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    static List<Edge> list = new ArrayList<>();
    static Map<Integer, int[]> coByIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken()), m = stoi(st.nextToken());
        char[][] map = new char[n][n];

        coByIdx = new HashMap<>();
        int idx = 2;
        for (int j = 0; j < n; ++j) {
            String sub = br.readLine();
            for (int i = 0; i < n; ++i) {
                map[j][i] = sub.charAt(i);
                if (map[j][i] == 'S' || map[j][i] == 'K') {
                    map[j][i] = (char)('0'+idx);
                    coByIdx.put(idx-2, new int[]{i, j});
                    idx++;
                }
            }
        }

        for (int i = 0; i < m+1; ++i) {
            bfs(map, i);
        }
        Collections.sort(list);
        uf = new int[m+1];
        for (int i = 0; i < m+1; ++i) {
            uf[i] = i;
        }

        int result = 0, cnt = 0;
        boolean toPrint = false;
        for (Edge edge : list) {
            if (union(edge.x, edge.y)) {
                result += edge.dist;
                if (++cnt == m) {
                    toPrint = true;
                    break;
                }
            }
        }
        if (toPrint) {
            bw.write(String.valueOf(result));
        } else {
            bw.write("-1");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(char[][] map, int startIdx) {
        Queue<int[]> myQ = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map.length];
        int sx = coByIdx.get(startIdx)[0], sy = coByIdx.get(startIdx)[1];
        myQ.offer(new int[]{sx, sy, 0});
        visited[sy][sx] = true;
        while (!myQ.isEmpty()) {
            int[] now = myQ.poll();
            char nowChar = map[now[1]][now[0]];
            if (nowChar != '0') {
                int idx = nowChar - '2';
                if (idx != startIdx) {
                    list.add(new Edge(startIdx, idx, now[2]));
                }
            }
            for (int i = 0; i < 4; ++i) {
                int nx = now[0] + dir[i][0], ny = now[1] + dir[i][1];
                if (!inRange(nx, ny, map.length) || map[ny][nx] == '1' || visited[ny][nx]) continue;
                visited[ny][nx] = true;
                myQ.offer(new int[]{nx, ny, now[2] + 1});
            }
        }
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;
        }
        if (a < b) {
            uf[b] = a;
        } else {
            uf[a] = b;
        }
        return true;
    }

    static int find(int x) {
        if (uf[x] == x) return x;
        return uf[x] = find(uf[x]);
    }

    static boolean inRange(int x, int y, int n) {
        return (x>=0 && y>=0 && x<n && y<n);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
