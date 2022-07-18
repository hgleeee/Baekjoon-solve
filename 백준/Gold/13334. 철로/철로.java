import java.util.*;
import java.io.*;

public class Main {

    static class Line {
        int min, max;
        public Line(int x, int y) {
            this.min = Math.min(x, y);
            this.max = Math.max(x, y);
        }
    }

    static int n, d;
    static PriorityQueue<Line> total, pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        total = new PriorityQueue<>(new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.max - o2.max;
            }
        });
        pq = new PriorityQueue<>(new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.min - o2.min;
            }
        });
        n = stoi(br.readLine());
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            total.offer(new Line(stoi(st.nextToken()), stoi(st.nextToken())));
        }
        d = stoi(br.readLine());

        int ans = 0;
        while (!total.isEmpty()) {
            Line now = total.poll();
            int rightIdx = now.max;
            while (!pq.isEmpty()) {
                Line ext = pq.peek();
                if (rightIdx - ext.min <= d) {
                    break;
                }
                pq.poll();
            }
            if (now.max - now.min <= d) {
                pq.offer(now);
            }
            ans = Math.max(ans, pq.size());
        }
        System.out.println(ans);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}