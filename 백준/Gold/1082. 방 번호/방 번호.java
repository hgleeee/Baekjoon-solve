import java.io.*;
import java.util.*;

public class Main {

    static class State implements Comparable<State> {

        int total;
        int[] num;

        public State(int total, int[] num) {
            this.total = total;
            this.num = new int[n];
            for (int i = 0; i < num.length; ++i) {
                this.num[i] = num[i];
            }
        }

        public void add(int x) {
            total++;
            num[x]++;
        }

        @Override
        public int compareTo(State o) {
            if (total - o.total != 0) {
                return total - o.total;
            }
            for (int i = num.length-1; i >= 0; --i) {
                if (num[i] != o.num[i]) {
                    return num[i] - o.num[i];
                }
            }
            return 0;
        }
    }

    static int n, m;
    static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = stoi(br.readLine());
        st = new StringTokenizer(br.readLine());
        cost = new int[n];
        for (int i = 0; i < n; ++i) {
            cost[i] = stoi(st.nextToken());
        }
        m = stoi(br.readLine());

        State[] states = new State[m+1];
        states[m] = new State(0, new int[n]);
        for (int i = m; i >= 0; --i) {
            if (states[i] == null) continue;
            for (int j = 0; j < n; ++j) {
                int remain = i - cost[j];
                if (remain < 0) continue;
                State nextState = new State(states[i].total, states[i].num);
                nextState.add(j);
                if (nextState.num[0] == nextState.total && nextState.total > 1) continue;
                if (states[remain] == null) {
                    states[remain] = nextState;
                } else {
                    states[remain] = (states[remain].compareTo(nextState) < 0) ? nextState : states[remain];
                }
            }
        }

        State state = new State(0, new int[n]);
        for (int i = 0; i < m; ++i) {
            if (states[i] == null) continue;
            //System.out.println(transform(states[i]) + " " + i + " " + transform(state));
            state = (state.compareTo(states[i]) < 0) ? states[i] : state;
        }
        System.out.println(transform(state));
        br.close();
    }

    static String transform(State state) {
        StringBuilder sb = new StringBuilder();
        for (int i = n-1; i >= 0; --i) {
            for (int j = 0; j < state.num[i]; ++j) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}