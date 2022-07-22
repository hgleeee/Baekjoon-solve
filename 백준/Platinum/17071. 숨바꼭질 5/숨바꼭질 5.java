import java.util.*;
import java.io.*;

public class Main {

    static class State {
        int idx, time;
        public State(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    static final int MAX = 500000, INF = 987654321;
    static int n, k;
    static int[] timeOfYoung;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        k = stoi(st.nextToken());
        timeOfYoung = new int[MAX+1];
        Arrays.fill(timeOfYoung, -1);
        int idx = 1, youngerIdx = k;
        while (youngerIdx <= MAX) {
            timeOfYoung[youngerIdx] = idx-1;
            youngerIdx += idx++;
        }
        System.out.println(bfs());
        br.close();
    }

    static int bfs() {
        int ret = INF;
        Queue<State> myQ = new LinkedList<>();
        boolean visited[][] = new boolean[MAX+1][2];
        visited[n][0] = true;
        myQ.offer(new State(n,0));
        while (!myQ.isEmpty()) {
            State now = myQ.poll();
            if (timeOfYoung[now.idx] != -1 && timeOfYoung[now.idx] >= now.time &&
                    timeOfYoung[now.idx] % 2 == now.time % 2) {
                ret = Math.min(ret, timeOfYoung[now.idx]);
            }
            if (now.idx + 1 <= MAX && !visited[now.idx + 1][(now.time+1)%2]) {
                visited[now.idx + 1][(now.time+1)%2] = true;
                myQ.offer(new State(now.idx+1, now.time+1));
            }
            if (now.idx - 1 >= 0 && !visited[now.idx - 1][(now.time+1)%2]) {
                visited[now.idx - 1][(now.time+1)%2] = true;
                myQ.offer(new State(now.idx-1, now.time+1));
            }
            if (now.idx * 2 <= MAX && !visited[now.idx * 2][(now.time+1)%2]) {
                visited[now.idx * 2][(now.time+1)%2] = true;
                myQ.offer(new State(now.idx*2, now.time+1));
            }
        }
        if (ret == INF) {
            return -1;
        }
        return ret;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}