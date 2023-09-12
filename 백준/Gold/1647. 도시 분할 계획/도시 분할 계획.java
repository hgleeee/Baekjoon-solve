import java.io.*;
import java.util.*;

public class Main {

    static class Route implements Comparable<Route> {
        int a, b, cost;

        public Route(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Route o) {
            return cost - o.cost;
        }
    }

    static int n, m;
    static int[] uf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        uf = new int[n+1];
        for (int i = 1; i <= n; ++i) {
            uf[i] = i;
        }
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            routes.add(new Route(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken())));
        }
        Collections.sort(routes);

        if (n == 2) {
            System.out.println(0);
            return;
        }

        int cnt = 0, ans = 0;
        for (Route route : routes) {
            if (union(route.a, route.b)) {
                ans += route.cost;
                if (++cnt == n-2) {
                    break;
                }
            }
        }
        System.out.println(ans);
        br.close();
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;
        }
        if (a > b) {
            uf[a] = b;
        } else {
            uf[b] = a;
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