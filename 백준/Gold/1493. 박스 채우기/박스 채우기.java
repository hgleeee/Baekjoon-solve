import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = stoi(st.nextToken()), w = stoi(st.nextToken()), h = stoi(st.nextToken());
        int n = stoi(br.readLine());

        int[] cube = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken()), b = stoi(st.nextToken());
            cube[a] = b;
        }

        long before = 0;
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            before <<= 3;
            long possibleCube = (long) (l >> i) * (w >> i) * (h >> i) - before;
            long newCube = Math.min(cube[i], possibleCube);
            before += newCube;
            ans += newCube;
        }

        if (before == (long) l*w*h) {
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }
        br.close();
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}