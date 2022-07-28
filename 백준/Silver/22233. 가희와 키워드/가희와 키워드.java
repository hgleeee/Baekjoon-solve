import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = stoi(st.nextToken()), m = stoi(st.nextToken());
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            set.add(br.readLine());
        }
        for (int i = 0; i < m; ++i) {
            String input = br.readLine();
            String[] strings = input.split(",");
            for (int j = 0; j < strings.length; ++j) {
                if (set.contains(strings[j])) {
                    set.remove(strings[j]);
                }
            }
            sb.append(set.size()+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}