import java.util.*;
import java.io.*;

public class Main {

    static int n, ans = Integer.MIN_VALUE;
    static int[] arr;
    static char[] tool;
    static boolean[] toolBlank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        String input = br.readLine();
        arr = new int[n/2+1];
        tool = new char[n/2];
        for (int i = 0; i < input.length(); ++i) {
            if (i % 2 == 0) {
                arr[i/2] = input.charAt(i) - '0';
            } else {
                tool[i/2] = input.charAt(i);
            }
        }
        toolBlank = new boolean[n/2];
        bruteforce(0);
        System.out.println(ans);
        br.close();
    }

    static void bruteforce(int idx) {
        if (idx == n/2) {
            ans = Math.max(ans, solve());
            return;
        }
        if (idx == 0 || !toolBlank[idx-1]) {
            toolBlank[idx] = true;
            bruteforce(idx+1);
            toolBlank[idx] = false;
        }
        bruteforce(idx+1);
    }

    static int solve() {
        int[] arrRep = arr.clone();
        boolean calculated[] = new boolean[n/2];
        bigCalculate(0, arrRep, calculated);
        bigCalculate(1, arrRep, calculated);
        bigCalculate(2, arrRep, calculated);
        return arrRep[0];
    }

    static void bigCalculate(int mode, int[] arrRep, boolean[] calculated) {
        for (int i = 0; i < n/2; ++i) {
            if (mode == 0) {
                if (!toolBlank[i] || calculated[i]) continue;
            } else if (mode == 1) {
                if (tool[i] != '*' || calculated[i]) continue;
            } else {
                if (calculated[i]) continue;
            }
            int leftIdx = i, rightIdx = i+1;
            while (arrRep[leftIdx] == Integer.MAX_VALUE) {
                leftIdx--;
            }
            while (arrRep[rightIdx] == Integer.MAX_VALUE) {
                rightIdx++;
            }
            int sub = calculate(arrRep[leftIdx], arrRep[rightIdx], tool[i]);
            arrRep[leftIdx] = sub;
            arrRep[rightIdx] = Integer.MAX_VALUE;
            calculated[i] = true;
        }
    }

    static int calculate(int a, int b, char to) {
        if (to == '+') {
            return a+b;
        } else if (to == '-') {
            return a-b;
        }
        return a*b;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}