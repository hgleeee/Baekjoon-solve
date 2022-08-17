import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int p = stoi(br.readLine());
        while (--p >= 0) {
            st = new StringTokenizer(br.readLine());
            int t = stoi(st.nextToken());
            ArrayList<Integer> list = new ArrayList<>();
            int sum = 0;
            for (int i = 0; i < 20; ++i) {
                int height = stoi(st.nextToken());
                int cnt = 0;
                boolean isBreak = false;
                for (int j = 0; j < list.size(); ++j) {
                    if (list.get(j) > height) {
                        list.add(j, height);
                        isBreak = true;
                        break;
                    }
                    cnt++;
                }
                if (!isBreak) {
                    list.add(height);
                }
                sum += list.size() - 1 - cnt;
            }
            sb.append(t + " " + sum + "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}