import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static boolean[][] visited;
    static int[][] map, dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static int[][] mapInform;
    static int[] size;
    static ArrayList<Integer> used = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        map = new int[n][m];
        mapInform = new int[n][m];
        visited = new boolean[n][m];
        for (int j = 0; j < n; ++j) {
            String input = br.readLine();
            for (int i = 0; i < m; ++i) {
                int a = input.charAt(i) - '0';
                map[j][i] = a;
                if (a == 1) {
                    mapInform[j][i] = -1;
                } else {
                    mapInform[j][i] = a;
                }
            }
        }

        int num = 1;
        size = new int[1000001];
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                if (mapInform[j][i] == 0 && !visited[j][i]) {
                    int sub = dfs(i, j, num);
                    size[num] = sub;
                    num++;
                }
            }
        }

        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                if (map[j][i] != 0) {
                    int ele = 1;
                    for (int k = 0; k < 4; ++k) {
                        int nx = i + dir[k][0], ny = j + dir[k][1];
                        if (!inRange(nx, ny) || mapInform[ny][nx] == -1 || used.contains(mapInform[ny][nx])) continue;
                        used.add(mapInform[ny][nx]);
                        ele += size[mapInform[ny][nx]];
                    }
                    map[j][i] = ele % 10;
                    used.clear();
                }
            }
        }

        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                sb.append(map[j][i]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int dfs(int x, int y, int num) {
        int ret = 1;
        mapInform[y][x] = num;
        visited[y][x] = true;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dir[i][0], ny = y + dir[i][1];
            if (!inRange(nx, ny) || mapInform[ny][nx] != 0 || visited[ny][nx]) continue;
            ret += dfs(nx, ny, num);
        }
        return ret;
    }

    static boolean inRange(int x, int y) {
        return (x>=0 && y>=0 && x<m && y<n);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}