import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken()), m = stoi(st.nextToken());
        HashSet<String> myMap = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            myMap.add(br.readLine());
        }
        int cnt = 0;
        for (int i = 0; i < m; ++i) {
            if (myMap.contains(br.readLine())) {
                cnt++;
            }
        }
        System.out.println(cnt);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}