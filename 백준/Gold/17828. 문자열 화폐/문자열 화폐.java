import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken()), x = stoi(st.nextToken());
        int max = n * 26, min = n;
        if (x < min || x > max) {
            System.out.println("!");
        } else {
            System.out.println(solve(max, x, n));
        }
        br.close();
    }

    static String solve(int max, int x, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            if (max - x >= 25) {
                sb.append('A');
                x--;
            } else if (max - x == 0) {
                sb.append('Z');
                x -= 26;
            } else {
                sb.append((char)('Z' - (max - x)));
                x -= (26 - (max - x));
            }
            max -= 26;
        }
        return sb.toString();
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}