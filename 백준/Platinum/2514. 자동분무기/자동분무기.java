import java.io.*;
import java.util.*;

public class Main {

    static int[][] gap;
    static char[][] ansMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int m = stoi(br.readLine()), k = stoi(br.readLine());
        int[][] map = new int[8][8];
        for (int j = 0; j < 8; ++j) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; ++i) {
                map[j][i] = stoi(st.nextToken());
            }
        }
        init(map, m);
        findAutoMachine();
        judgeAutoMachine(k);
        print(ansMap);
        br.close();
    }

    static void judgeAutoMachine(int k) {
        for (int j = 0; j < 8; ++j) {
            for (int i = 0; i < 8; ++i) {
                if (ansMap[j][i] != '?') continue;
                int cnt = addForPoint(i, j), toExclude = arrangeCnt(i, j);
                if ((k-toExclude-1) % 2 == 0) {
                    if (cnt % 4 == 3) ansMap[j][i] = '+';
                    else ansMap[j][i] = '-';
                } else {
                    if (cnt % 4 == 1) ansMap[j][i] = '+';
                    else ansMap[j][i] = '-';
                }
            }
        }
    }

    static int arrangeCnt(int x, int y) {
        int ret = 0;
        for (int j = 0; j < 8; ++j) {
            if (ansMap[j][x] != '.') {
                ret++;
            }
            if (ansMap[y][j] != '.') {
                ret++;
            }
        }
        return ret-2;
    }

    static void findAutoMachine() {
        for (int j = 0; j < 8; ++j) {
            for (int i = 0; i < 8; ++i) {
                int cnt = addForPoint(i, j);
                if (cnt % 2 != 0) {
                    ansMap[j][i] = '?';
                }
            }
        }
    }

    static int addForPoint(int x, int y) {
        int ret = 0;
        for (int i = 0; i < 8; ++i) {
            ret += gap[y][i];
            ret += gap[i][x];
        }
        ret -= gap[y][x];
        if (ret < 0) {
            ret += 400;
        }
        return ret;
    }

    static void init(int[][] map, int m) {
        gap = new int[8][8];
        for (int j = 0; j < 8; ++j) {
            for (int i = 0; i < 8; ++i) {
                gap[j][i] = map[j][i] - m;
            }
        }
        ansMap = new char[8][8];
        for (int j = 0; j < 8; ++j) {
            for (int i = 0; i < 8; ++i) {
                ansMap[j][i] = '.';
            }
        }
    }

    static void print(char[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 8; ++j) {
            for (int i = 0; i < 8; ++i) {
                sb.append(map[j][i]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}