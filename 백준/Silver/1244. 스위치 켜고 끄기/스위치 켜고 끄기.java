import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int n = stoi(br.readLine());
        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            arr[i] = stoi(st.nextToken());
        }

        int k = stoi(br.readLine());
        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            int state = stoi(st.nextToken()), num = stoi(st.nextToken());
            change(state, num, arr);
        }
        for (int i = 1; i <= n; ++i) {
            sb.append(arr[i]);
            if (i % 20 == 0) {
                sb.append("\n");
            } else {
                sb.append(" ");
            }
        }
        System.out.println(sb);
        br.close();
    }

    static void change(int state, int num, int[] arr) {
        if (state == 1) {
            for (int now = num; now < arr.length; now += num) {
                arr[now] = arr[now] == 0 ? 1 : 0;
            }
        } else {
            arr[num] = arr[num] == 0 ? 1 : 0;
            int lo = num-1, hi = num+1;
            while (lo >= 1 && hi < arr.length) {
                if (arr[lo] != arr[hi]) {
                    break;
                }
                arr[lo] = arr[lo] == 0 ? 1 : 0;
                arr[hi] = arr[hi] == 0 ? 1 : 0;
                lo--;
                hi++;
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}