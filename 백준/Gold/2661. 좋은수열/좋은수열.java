import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static String ans = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        solve(0, new char[n]);
        System.out.println(ans);
        br.close();
    }

    static void solve(int idx, char[] arr) {
        if (idx == n) {
            if (ans == null) {
                ans = new String(arr);
            }
            return;
        }
        for (int i = 0; i < 3; ++i) {
            if (ans != null) return;
            char x = (char)('1' + i);
            arr[idx] = x;
            boolean isGood = true;
            for (int gap = maxGap(idx); gap >= 1; --gap) {
                int lo = idx - gap, hi = idx;
                boolean unitIsGood = false;
                for (int k = 0; k < gap; ++k) {
                    if (arr[lo] != arr[hi]) {
                        unitIsGood |= true;
                    }
                    lo--;
                    hi--;
                }
                if (!unitIsGood) {
                    isGood = false;
                    break;
                }
            }
            if (isGood) {
                arr[idx] = x;
                solve(idx+1, arr);
            }
        }
    }

    static int maxGap(int idx) {
        return idx - idx/2;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}