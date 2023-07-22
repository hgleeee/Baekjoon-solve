import java.io.*;
import java.util.*;

public class Main {

    static class Chunk implements Comparable<Chunk> {
        int weight, cost;

        public Chunk(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public int compareTo(Chunk o) {
            if (cost != o.cost) {
                return cost - o.cost;
            }
            return o.weight - weight;
        }
    }

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken()), m = stoi(st.nextToken());

        List<Chunk> chunks = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int w = stoi(st.nextToken()), c = stoi(st.nextToken());
            chunks.add(new Chunk(w, c));
        }
        Collections.sort(chunks);

        boolean existAns = false;
        int localSum = 0, lastCost = -1;
        int nowCost = 0;
        int ret = INF;
        for (int i = 0; i < n; ++i) {
            Chunk chunk = chunks.get(i);
            localSum += chunk.weight;
            if (lastCost == chunk.cost) {
                nowCost += chunk.cost;
            } else {
                nowCost = chunk.cost;
            }
            lastCost = chunk.cost;
            if (localSum >= m) {
                existAns = true;
                ret = Math.min(ret, nowCost);
            }
        }
        if (existAns) System.out.println(ret);
        else System.out.println(-1);
        br.close();
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}