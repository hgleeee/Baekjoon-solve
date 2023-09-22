import java.io.*;
import java.util.*;

public class Main {

    static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.x = x;
            this.y = y;
        }

        void unitMove(int d, int s) {
            int nj = y + s * dir[d][0], ni = x + s * dir[d][1];
            while (nj < 1) {
                nj += n;
            }
            while (nj > n) {
                nj -= n;
            }
            while (ni < 1) {
                ni += n;
            }
            while (ni > n) {
                ni -= n;
            }
            y = nj;
            x = ni;
        }
    }

    static int n, m;
    static int[][] map;
    static int[][] dir = new int[][]{{0,0},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        map = new int[n+1][n+1];
        for (int j = 1; j <= n; ++j) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; ++i) {
                map[j][i] = stoi(st.nextToken());
            }
        }

        List<Pos> cloud = new ArrayList<>();
        for (int j = 0; j < 2; ++j) {
            for (int i = 0; i < 2; ++i) {
                cloud.add(new Pos(n-1+j, 1+i));
            }
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = stoi(st.nextToken()), s = stoi(st.nextToken());
            move(cloud, d, s);
            rain(cloud);
            waterCopy(cloud);
            cloud = getNewCloud(cloud);
        }

        int answer = 0;
        for (int j = 1; j <= n; ++j) {
            for (int i = 1; i <= n; ++i) {
                answer += map[j][i];
            }
        }
        System.out.println(answer);
        br.close();
    }

    static int[][] copyDir = new int[][]{{1,-1},{1,1},{-1,1},{-1,-1}};

    static List<Pos> getNewCloud(List<Pos> cloud) {
        boolean[][] checked = new boolean[n+1][n+1];
        for (Pos pos : cloud) {
            checked[pos.y][pos.x] = true;
        }

        List<Pos> ret = new ArrayList<>();
        for (int j = 1; j <= n; ++j) {
            for (int i = 1; i <= n; ++i) {
                if (!checked[j][i] && map[j][i] >= 2) {
                    map[j][i] -= 2;
                    ret.add(new Pos(j, i));
                }
            }
        }
        return ret;
    }

    static void waterCopy(List<Pos> cloud) {
        for (Pos pos : cloud) {
            for (int i = 0; i < 4; ++i) {
                int ny = pos.y + copyDir[i][0], nx = pos.x + copyDir[i][1];
                if (inRange(ny, nx) && map[ny][nx] > 0) {
                    map[pos.y][pos.x]++;
                }
            }
        }
    }

    static void rain(List<Pos> cloud) {
        for (Pos pos : cloud) {
            map[pos.y][pos.x]++;
        }
    }

    static void move(List<Pos> cloud, int d, int s) {
        for (Pos pos : cloud) {
            pos.unitMove(d, s);
        }
    }

    static boolean inRange(int j, int i) {
        return (j >= 1 && i >= 1 && j <= n && i <= n);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}