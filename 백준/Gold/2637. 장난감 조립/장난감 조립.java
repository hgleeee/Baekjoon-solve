import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        // 입력에 따른
        int needNum[][] = new int[n+1][n+1];
        // 기본 부품만 (출력용)
        int src[][] = new int[n+1][n+1];
        ArrayList<Integer> graph[] = new ArrayList[n+1];
        for (int i = 1; i <= n; ++i) {
            graph[i] = new ArrayList<>();
        }
        int parentNum[] = new int[n+1];
        int first, second, num;
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            first = Integer.parseInt(st.nextToken());
            second = Integer.parseInt(st.nextToken());
            num = Integer.parseInt(st.nextToken());
            graph[second].add(first);
            needNum[first][second] = num;
            parentNum[first]++;
        }

        Queue<Integer> myQ = new LinkedList<>();
        ArrayList<Integer> basicSrc = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            if (parentNum[i] == 0) {
                basicSrc.add(i);
                myQ.add(i);
                src[i][i] = 1;
            }
        }

        while (!myQ.isEmpty()) {
            int now = myQ.poll();
            for (int child : graph[now]) {
                parentNum[child]--;
                for (int base : basicSrc) {
                    src[child][base] += src[now][base] * needNum[child][now];
                }
                if (parentNum[child] == 0) {
                    myQ.add(child);
                }
            }
        }

        for (int base : basicSrc) {
            bw.append(base + " " + src[n][base] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}





