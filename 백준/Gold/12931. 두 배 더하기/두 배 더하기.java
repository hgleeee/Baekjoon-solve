import java.io.*;
import java.util.*;

public class Main {

    static class Info {
        int minusNum, divideNum;

        public Info(int minusNum, int divideNum) {
            this.minusNum = minusNum;
            this.divideNum = divideNum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = stoi(st.nextToken());
        }
        System.out.println(solve(n, arr));
        br.close();
    }

    static int solve(int n, int[] arr) {
        Info[] infoList = new Info[n];
        for (int i = 0; i < n; ++i) {
            infoList[i] = getInfo(arr[i]);
        }

        int divideMax = -1;
        for (int i = 0; i < n; ++i) {
            divideMax = Math.max(divideMax, infoList[i].divideNum);
        }

        int cnt = 0;
        for (int i = 0; i < divideMax; ++i) {
            for (int j = 0; j < n; ++j) {
                if (arr[j] % 2 == 1) {
                    cnt++;
                }
                arr[j] /= 2;
            }
            cnt++;
        }
        for (int i = 0; i < n; ++i) {
            cnt += arr[i];
        }
        return cnt;
    }

    static Info getInfo(int n) {
        int minusNum = 0, divideNum = 0;
        while (n > 0) {
            if (n % 2 == 1) {
                n--;
                minusNum++;
            } else {
                n /= 2;
                divideNum++;
            }
        }
        return new Info(minusNum, divideNum);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}