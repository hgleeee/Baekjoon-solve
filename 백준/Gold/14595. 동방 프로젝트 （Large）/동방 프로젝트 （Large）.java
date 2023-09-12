import java.io.*;
import java.util.*;

public class Main {

    static class Info implements Comparable<Info> {
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Info o) {
            if (x != o.x) {
                return x - o.x;
            }
            return y - o.y;
        }
    }

    static int[] uf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = stoi(br.readLine()), m = stoi(br.readLine());

        uf = new int[n+1];
        for (int i = 1; i <= n; ++i) {
            uf[i] = i;
        }

        List<Info> infoList = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken()), y = stoi(st.nextToken());
            infoList.add(new Info(x, y));
        }
        Collections.sort(infoList);

        int root = -1, lastFinished = -1;
        for (Info info : infoList) {
            if (lastFinished >= info.x) {
                for (int i = lastFinished; i <= info.y; ++i) {
                    union(root, i);
                }
            } else {
                root = info.x;
                for (int i = info.x; i <= info.y; ++i) {
                    union(root, i);
                }
            }
            lastFinished = info.y;
        }

        Set<Integer> rootSet = new HashSet<>();
        for (int i = 1; i <= n; ++i) {
            rootSet.add(find(i));
        }
        System.out.println(rootSet.size());
        br.close();
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            uf[a] = b;
        } else if (a < b) {
            uf[b] = a;
        }
    }

    static int find(int x) {
        if (uf[x] == x) return x;
        return uf[x] = find(uf[x]);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}