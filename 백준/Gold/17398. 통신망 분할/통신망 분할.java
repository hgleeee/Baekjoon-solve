import java.util.*;
import java.io.*;

public class Main {

    static int N, M, Q;
    static int[][] arr;
    static Stack<Integer> myQ = new Stack<>();
    static int[] uf, size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        Q = stoi(st.nextToken());
        arr = new int[M+1][2];
        for (int i = 1; i <= M; ++i) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = stoi(st.nextToken());
            arr[i][1] = stoi(st.nextToken());
        }

        boolean[] connected = new boolean[M+1];
        Arrays.fill(connected, true);
        for (int i = 0; i < Q; ++i) {
            int a = stoi(br.readLine());
            myQ.push(a);
            connected[a] = false;
        }

        uf = new int[N+1];
        size = new int[N+1];
        for (int i = 1; i <= N; ++i) {
            uf[i] = i;
            size[i] = 1;
        }

        for (int i = 1; i <= M; ++i) {
            if (connected[i]) {
                int a = find(arr[i][0]), b = find(arr[i][1]);
                if (union(arr[i][0], arr[i][1])) {
                    if (a < b) {
                        size[a] += size[b];
                    } else {
                        size[b] += size[a];
                    }
                }
            }
        }

        long ans = 0;
        while (!myQ.isEmpty()) {
            Integer now = myQ.pop();
            int a = find(arr[now][0]), b = find(arr[now][1]);
            if (union(arr[now][0], arr[now][1])) {
                ans += (long)size[a] * size[b];
                if (a < b) {
                    size[a] += size[b];
                } else {
                    size[b] += size[a];
                }
            }
        }
        System.out.println(ans);
        br.close();
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        if (x > y) {
            uf[x] = y;
        } else {
            uf[y] = x;
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