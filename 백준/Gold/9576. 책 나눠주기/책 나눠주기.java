import java.util.*;
import java.io.*;

public class Main {

    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int t = stoi(br.readLine());
        for (int tst = 0; tst < t; ++tst) {
            ArrayList<int[]> inform = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken());
            m = stoi(st.nextToken());
            for (int i = 0; i < m; ++i) {
                st = new StringTokenizer(br.readLine());
                inform.add(new int[]{stoi(st.nextToken()), stoi(st.nextToken())});
            }
            sb.append(maxNum(inform) + "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int maxNum(ArrayList<int[]> inform) {
        int cnt = 0;
        boolean[] borrowed = new boolean[n+1];
        Collections.sort(inform, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] < o2[1]) {
                    return -1;
                } else if (o1[1] > o2[1]) {
                    return 1;
                }
                if (o1[0] < o2[0]) {
                    return -1;
                }
                return 1;
            }
        });

        for (int[] sub : inform) {
            for (int i = sub[0]; i <= sub[1]; ++i) {
                if (!borrowed[i]) {
                    borrowed[i] = true;
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}