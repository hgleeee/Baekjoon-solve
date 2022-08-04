import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken()), k = stoi(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = stoi(st.nextToken());
        }

        int lo = 0, hi = 0;
        int[] num = new int[100001];
        num[arr[0]] = 1;

        int ans = 1;
        while (hi < n-1) {
            int now = arr[hi + 1];
            num[now]++;
            if (num[now] > k) {
                while (arr[lo] != now) {
                    num[arr[lo]]--;
                    lo++;
                }
                num[arr[lo]]--;
                lo++;
            }
            hi++;
            ans = Math.max(hi-lo+1, ans);
        }
        System.out.println(ans);
        br.close();
    }



    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}