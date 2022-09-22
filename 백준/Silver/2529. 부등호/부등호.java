import java.io.*;
import java.util.*;

public class Main {

    static long max = 0;
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int k = stoi(br.readLine());
        char[] arr = new char[k];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; ++i) {
            arr[i] = st.nextToken().charAt(0);
        }
        for (int i = 0; i < 10; ++i) {
            boolean[] chosen = new boolean[10];
            chosen[i] = true;
            bruteforce(0, arr, k, i, chosen);
        }
        String maxStr = String.valueOf(max);
        String minStr = String.valueOf(min);
        if (maxStr.length() != k+1) {
            maxStr = "0" + maxStr;
        }
        if (minStr.length() != k+1) {
            minStr = "0" + minStr;
        }
        sb.append(maxStr + "\n" + minStr);
        System.out.println(sb);
        br.close();
    }

    static void bruteforce(int idx, char[] arr, int k, long value, boolean[] chosen) {
        if (idx == k) {
            max = Math.max(value, max);
            min = Math.min(value, min);
            return;
        }
        int toCompare = (int)value % 10;
        for (int i = 0; i < 10; ++i) {
            if (chosen[i]) continue;
            if (arr[idx] == '>') {
                if (toCompare < i) continue;
            } else {
                if (toCompare > i) continue;
            }
            chosen[i] = true;
            bruteforce(idx + 1, arr, k, value * 10 + i, chosen);
            chosen[i] = false;
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
