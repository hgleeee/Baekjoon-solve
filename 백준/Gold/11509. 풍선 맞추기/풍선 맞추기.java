import java.io.*;
import java.util.*;

public class Main {

    static int[] height = new int[1000001];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = stoi(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            int h = stoi(st.nextToken());
            if (h != 1000000 && height[h+1] > 0) {
                height[h+1]--;
            }
            height[h]++;
        }
        int cnt = 0;
        for (int i = 1; i <= 1000000; ++i) {
            cnt += height[i];
        }
        System.out.println(cnt);
        br.close();
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}