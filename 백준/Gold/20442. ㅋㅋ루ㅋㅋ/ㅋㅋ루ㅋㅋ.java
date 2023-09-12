import java.io.*;
import java.util.*;

public class Main {

    static final int MIN = -987654321;
    static String s;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        sum = new int[s.length()+1];
        for (int i = 1; i <= s.length(); ++i) {
            sum[i] = sum[i-1] + ((s.charAt(i-1) == 'R') ? 1 : 0);
        }
        System.out.println(solve(s.length()));
        br.close();
    }

    static int solve(int len) {
        int[] maxLen = new int[len/2+1]; // index는 k의 개수 / 2, 들어가는 value는 r의 개수
        int lo = 1, hi = len;

        int kNum = 0;
        while (lo <= hi) {
            if (sum[hi] - sum[lo-1] != 0) {
                maxLen[kNum] = Math.max(maxLen[kNum], sum[hi] - sum[lo - 1]);
            }
            if (s.charAt(lo-1) == 'K' && s.charAt(hi-1) == 'K') {
                kNum++;
                lo++;
                hi--;
            } else if (s.charAt(lo-1) == 'K') {
                hi--;
            } else if (s.charAt(hi-1) == 'K') {
                lo++;
            } else {
                lo++;
                hi--;
            }
        }
        int ret = 0;
        for (int i = 0; i <= len/2; ++i) {
            if (maxLen[i] == 0) continue;
            ret = Math.max(ret, maxLen[i] + i*2);
        }
        return ret;
    }
}