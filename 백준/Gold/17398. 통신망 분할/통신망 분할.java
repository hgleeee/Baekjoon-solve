import java.util.*;
import java.io.*;

public class Main {

    static class Connection {
        int x, y;
        public Connection(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, q;
    static long[] size;
    static int[] uf;
    static boolean[] removeCon;
    static Connection[] connections;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        q = stoi(st.nextToken());
        connections = new Connection[m+1];
        for (int i = 1; i <= m; ++i) {
            st = new StringTokenizer(br.readLine());
            connections[i] = new Connection(stoi(st.nextToken()), stoi(st.nextToken()));
        }

        size = new long[n+1];
        uf = new int[n+1];
        for (int i = 1; i <= n; ++i) {
            size[i] = 1;
            uf[i] = i;
        }

        removeCon = new boolean[m+1];
        Stack<Integer> removeIdx = new Stack<>();
        for (int i = 0; i < q; ++i) {
            int a = stoi(br.readLine());
            removeCon[a] = true;
            removeIdx.push(a);
        }

        for (int i = 1; i <= m; ++i) {
            if (removeCon[i]) continue;
            long xSize = size[find(connections[i].x)], ySize = size[find(connections[i].y)];
            if (merge(connections[i].x, connections[i].y)) {
                size[find(connections[i].x)] = xSize+ySize;
            }
        }

        long ans = 0;
        while (!removeIdx.isEmpty()) {
            Integer remove = removeIdx.pop();
            long xSize = size[find(connections[remove].x)], ySize = size[find(connections[remove].y)];
            if (merge(connections[remove].x, connections[remove].y)) {
                ans += xSize*ySize;
                size[find(connections[remove].x)] = xSize+ySize;
            }
        }
        System.out.println(ans);
        br.close();
    }

    static boolean merge(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;
        }
        if (a < b) {
            uf[b] = a;
        } else {
            uf[a] = b;
        }
        return true;
    }

    static int find(int x) {
        if (uf[x] == x) return x;
        return uf[x] = find(uf[x]);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
