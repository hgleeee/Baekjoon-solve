import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 10001;
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = stoi(st.nextToken());
        }
        System.out.println(binarySearch(0, 10000));
        br.close();
    }

    static int binarySearch(int lo, int hi) {
        if (lo == hi) {
            return lo;
        }
        int mid = (lo + hi) / 2;
        if (canDivide(mid)) {
            return binarySearch(lo, mid);
        }
        return binarySearch(mid+1, hi);
    }

    static boolean canDivide(int scoreGap) {
        int idx = 0, cnt = 0;
        while (idx < n) {
            int min = INF, max = 0;
            for (int i = idx; i < n; ++i) {
                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i]);
                if (max - min > scoreGap) {
                    idx = i-1;
                    break;
                }
                if (i == n-1) {
                    idx = n-1;
                    break;
                }
            }
            cnt++;
            idx++;
        }
        return (cnt > m) ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}