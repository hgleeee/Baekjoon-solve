import java.io.*;
import java.util.*;

public class Main {

    static class Player {
        int[] stat = new int[3];

        public Player(int blackStat, int whiteStat) {
            stat[1] = blackStat;
            stat[2] = whiteStat;
        }
    }

    static int[][] dir = new int[][]{{0,0},{1,0},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        List<Player> players = new ArrayList<>();
        String input = null;
        while ((input = br.readLine()) != null) {
            st = new StringTokenizer(input);
            players.add(new Player(stoi(st.nextToken()), stoi(st.nextToken())));
        }

        int[][][] dp = new int[players.size()+1][16][16];
        for (int a = 0; a < players.size(); ++a) {
            for (int j = 0; j < 16; ++j) {
                Arrays.fill(dp[a][j], -1);
            }
        }
        dp[0][0][0] = 0;

        for (int a = 0; a < players.size(); ++a) {
            for (int j = 0; j <= 15; ++j) {
                for (int i = 0; i <= 15; ++i) {
                    if (dp[a][j][i] == -1) continue;
                    for (int k = 0; k < 3; ++k) {
                        int ni = i + dir[k][0], nj = j + dir[k][1];
                        if (!inRange(ni, nj)) continue;
                        dp[a+1][nj][ni] = Math.max(dp[a+1][nj][ni], dp[a][j][i] + players.get(a).stat[k]);
                    }
                }
            }
        }
        System.out.println(dp[players.size()][15][15]);
        br.close();
    }

    static boolean inRange(int blackNum, int whiteNum) {
        return (blackNum <= 15 && whiteNum <= 15);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}