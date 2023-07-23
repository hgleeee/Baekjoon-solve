import java.io.*;
import java.util.*;

public class Main {

    static class Info implements Comparable<Info> {
        int dist, num;

        public Info(int dist, int num) {
            this.dist = dist;
            this.num = num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(Info o) {
            return o.dist - dist;
        }
    }

    static int n, k, s;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        k = stoi(st.nextToken());
        s = stoi(st.nextToken());
        PriorityQueue<Info> pHouseInfo = new PriorityQueue<>(), nHouseInfo = new PriorityQueue<>();
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken()) - s, y = stoi(st.nextToken());
            if (x > 0) {
                pHouseInfo.offer(new Info(Math.abs(x), y));
            } else {
                nHouseInfo.offer(new Info(Math.abs(x), y));
            }
        }
        System.out.println(solve(pHouseInfo, solve(nHouseInfo, 0)));
        br.close();
    }

    static int solve(PriorityQueue<Info> pq, int ret) {
        while (!pq.isEmpty()) {
            int limit = k, route = 0;
            while (limit > 0 && !pq.isEmpty()) {
                Info info = pq.poll();
                route = Math.max(route, info.dist);
                limit -= info.num;
                if (limit < 0) {
                    info.setNum(-limit);
                    pq.offer(info);
                    limit = 0;
                }
            }
            ret += 2 * route;
        }
        return ret;
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}