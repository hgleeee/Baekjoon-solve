import java.io.*;
import java.util.*;

public class Main {
    static int fromTo[];
    static boolean finished[], visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; ++i) {
            int n = Integer.parseInt(br.readLine());
            fromTo = new int[n+1];
            String subInput[] = br.readLine().split(" ");
            for (int j = 1; j < n + 1; ++j) {
                fromTo[j] = Integer.parseInt(subInput[j-1]);
            }
            visited = new boolean[n+1];
            finished = new boolean[n+1];
            count = 0;
            for (int j = 1; j < n + 1; ++j) {
                dfs(j);
            }

            bw.write(n-count + "\n");
        }
        bw.flush();
        bw.close();
    }
    static int count = 0;
    static void dfs(int index) {
        if (visited[index]) {
            return;
        }
        visited[index] = true;
        int next = fromTo[index];
        if (!visited[next]) {
            dfs(next);
        }
        else {
            if (!finished[next]) {
                count++;
                for (int i = next; i != index; i = fromTo[i])
                    count++;
            }
        }


        finished[index] = true;
    }
}

