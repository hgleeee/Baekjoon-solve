import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken()), k = stoi(st.nextToken());
        char[] arr = br.readLine().toCharArray();
        boolean[] taken = new boolean[n];
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (arr[i] == 'H') continue;
            int lo = i-k < 0 ? 0 : i-k, hi = i+k >= n ? n-1 : i+k;
            for (int j = lo; j <= hi; ++j) {
                if (arr[j] == 'H' && !taken[j]) {
                    taken[j] = true;
                    cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}