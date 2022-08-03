import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken()), x = stoi(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = stoi(st.nextToken());
        }
        int lo = 0, hi = x-1;
        int sum = 0;
        for (int i = 0; i < x; ++i) {
            sum += arr[i];
        }
        int max = sum, cnt = 1;
        while (hi < n-1) {
            sum -= arr[lo];
            sum += arr[hi+1];
            if (max == sum) {
                cnt++;
            } else if (max < sum) {
                max = sum;
                cnt = 1;
            }
            lo++;
            hi++;
        }
        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max + "\n" + cnt);
        }
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}