import java.util.*;
import java.io.*;

public class Main {

    static int n, k, p, x;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        k = stoi(st.nextToken());
        p = stoi(st.nextToken());
        x = stoi(st.nextToken());
        int[][] numInfo = {
                {1, 1, 1, 0, 1, 1 ,1},
                {0, 0, 1, 0, 0, 1, 0},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1},
                {0, 1, 1, 1, 0, 1, 0},
                {1, 1, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 1, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 1}};
        int[][] changeCost = new int[10][10];
        fillCost(changeCost, numInfo);
        System.out.println(solve(x, 0, changeCost, 0, 0));
        br.close();
    }

    static int solve(int input, int idx, int[][] changeCost, int result, int num) {
        if (idx == k) {
            if (num == x || num == 0) return 0;
            return 1;
        }
        int nowIdx = (int)Math.pow(10, k - idx - 1);
        int val = input / nowIdx;
        int ret = 0;
        for (int i = 0; i < 10; ++i) {
            if (num + i*nowIdx > n || result + changeCost[val][i] > p) continue;
            ret += solve(input - val*nowIdx, idx+1, changeCost, result+changeCost[val][i], num+i*nowIdx);
        }
        return ret;
    }

    static void fillCost(int[][] changeCost, int[][] numInfo) {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (i == j) continue;
                int cnt = 0;
                for (int k = 0; k < 7; ++k) {
                    if (numInfo[i][k] != numInfo[j][k]) {
                        cnt++;
                    }
                }
                changeCost[i][j] = cnt;
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}