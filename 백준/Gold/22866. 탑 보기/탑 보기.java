import java.util.*;
import java.io.*;

public class Main {

    static class Info {
        int idx, dist;
        public Info(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }

    static int n;
    static int[] arr, leftNum, rightNum;
    static Info[] minVal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        n = stoi(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = stoi(st.nextToken());
        }

        leftNum = new int[n];
        rightNum = new int[n];
        minVal = new Info[n];
        Arrays.fill(minVal, null);
        solveLeft(0);
        solveRight(n-1);
        for (int i = 0; i < n; ++i) {
            int val = leftNum[i] + rightNum[i];
            sb.append(val + " " + ((val != 0) ? minVal[i].idx+1 : "") + "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static void solveLeft(int nowIdx) {
        if (nowIdx == n) {
            return;
        }
        for (int i = nowIdx-1; i >= 0; --i) {
            if (arr[i] > arr[nowIdx]) {
                leftNum[nowIdx] = leftNum[i] + 1;
                minVal[nowIdx] = new Info(i, nowIdx-i);
                break;
            }
        }
        solveLeft(nowIdx+1);
    }

    static void solveRight(int nowIdx) {
        if (nowIdx == -1) {
            return;
        }
        for (int i = nowIdx+1; i < n; ++i) {
            if (arr[i] > arr[nowIdx]) {
                rightNum[nowIdx] = rightNum[i] + 1;
                if (minVal[nowIdx] == null) {
                    minVal[nowIdx] = new Info(i, i-nowIdx);
                } else {
                    if (minVal[nowIdx].dist > i-nowIdx) {
                        minVal[nowIdx] = new Info(i, i-nowIdx);
                    }
                }
                break;
            }
        }
        solveRight(nowIdx-1);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}