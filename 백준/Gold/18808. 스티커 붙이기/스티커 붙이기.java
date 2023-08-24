import java.io.*;
import java.util.*;

public class Main {

    static class Sticker {

        int r, c;
        int[][] map;

        public Sticker(int r, int c, int[][] map) {
            this.r = r;
            this.c = c;
            this.map = map;
        }

        public void rotate() {
            int[][] nextMap = new int[c][r];
            for (int j = 0; j < r; ++j) {
                for (int i = 0; i < c; ++i) {
                    nextMap[i][r-1-j] = map[j][i];
                }
            }
            int tmp = this.r;
            this.r = c;
            this.c = tmp;
            this.map = nextMap;
        }

        public boolean canFill(int x, int y, int[][] totalMap) {
            for (int j = 0; j < r; ++j) {
                for (int i = 0; i < c; ++i) {
                    if (totalMap[y + j][x + i] == this.map[j][i] && this.map[j][i] == 1) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    static int n, m, k;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        k = stoi(st.nextToken());

        List<Sticker> stickers = new ArrayList<>();
        for (int a = 0; a < k; ++a) {
            st = new StringTokenizer(br.readLine());
            int r = stoi(st.nextToken()), c = stoi(st.nextToken());

            int[][] map = new int[r][c];
            for (int j = 0; j < r; ++j) {
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < c; ++i) {
                    map[j][i] = stoi(st.nextToken());
                }
            }
            stickers.add(new Sticker(r, c, map));
        }

        int[][] totalBoard = new int[n][m];
        int ret = 0;
        for (Sticker sticker : stickers) {
            int[] co = stickerCanFill(sticker, totalBoard);
            if (co[0] != -1) {
                for (int j = 0; j < sticker.r; ++j) {
                    for (int i = 0; i < sticker.c; ++i) {
                        if (sticker.map[j][i] == 1) {
                            totalBoard[j + co[1]][i + co[0]] = sticker.map[j][i];
                            ret++;
                        }
                    }
                }
            }
        }
        System.out.println(ret);
        br.close();
    }

    static int[] stickerCanFill(Sticker sticker, int[][] totalBoard) {
        for (int k = 0; k < 4; ++k) {
            for (int j = 0; j <= n - sticker.r; ++j) {
                for (int i = 0; i <= m - sticker.c; ++i) {
                    if (sticker.canFill(i, j, totalBoard)) {
                        return new int[]{i, j};
                    }
                }
            }
            sticker.rotate();
        }
        return new int[]{-1, -1};
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}