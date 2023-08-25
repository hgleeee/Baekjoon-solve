import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int tst = stoi(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (tst-- > 0) {
            int k = stoi(br.readLine());
            st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (int i = 0; i < k; ++i) {
                pq.offer((long)stoi(st.nextToken()));
            }

            long cost = 0;
            while (pq.size() >= 2) {
                long a = pq.poll(), b = pq.poll();
                cost += (a+b);
                pq.offer(a+b);
            }
            sb.append(cost).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}