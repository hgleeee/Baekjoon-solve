import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken()), k = stoi(st.nextToken());
        System.out.println(solve(n, k));
        br.close();
    }

    static int solve(long n, long k) {
        int idx = 1;
        while (true) {
            long size = idx * (long) Math.pow(10, idx-1) * 9; // 1일 때, 1 * 1 * 9 / 2일 때, 2 * 10 * 9 .....
            if (size < k) {
                k -= size;
            } else {
                break;
            }
            idx++;
        }
        long value = (k + idx - 1) / idx;
        int remain = (int)(k + idx - 1) % idx;
        long b = (long)Math.pow(10, idx-1);
        if (b + value - 1 > n) {
            return -1;
        }
        String transformed = String.valueOf(b + value - 1);
        return transformed.charAt(remain) - '0';
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }


}