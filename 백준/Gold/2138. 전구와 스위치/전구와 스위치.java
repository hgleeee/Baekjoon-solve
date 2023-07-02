import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());
        String initS = br.readLine(), finalS = br.readLine();
        int[] bulbStateOnStart = new int[n], bulbStateOffStart = new int[n];
        for (int i = 0; i < 2; ++i) {
            bulbStateOffStart[i] = initS.charAt(i) - '0';
            bulbStateOnStart[i] = initS.charAt(i) == '0' ? 1 : 0;
        }
        for (int i = 2; i < n; ++i) {
            bulbStateOffStart[i] = initS.charAt(i) - '0';
            bulbStateOnStart[i] = initS.charAt(i) - '0';
        }
        int answer = Math.min(solve(n, bulbStateOffStart, finalS), solve(n, bulbStateOnStart, finalS)+1);
        System.out.println(answer == INF ? -1 : answer);
        br.close();
    }

    static int solve(int n, int[] bulbState, String finalS) {
        int ret = 0;
        int[] finalArr = new int[n];
        for (int i = 0; i < n; ++i) {
            finalArr[i] = finalS.charAt(i) - '0';
        }
        for (int i = 1; i < n-1; ++i) {
            if (bulbState[i-1] != finalArr[i-1]) {
                ret++;
                bulbState[i+1] = pushSwitch(bulbState[i+1]);
                bulbState[i] = pushSwitch(bulbState[i]);
                bulbState[i-1] = pushSwitch(bulbState[i-1]);
            }
        }
        if (bulbState[n-2] == finalArr[n-2] && bulbState[n-1] != finalArr[n-1] ||
                    bulbState[n-2] != finalArr[n-2] && bulbState[n-1] == finalArr[n-1]) {
            ret = INF;
        } else if (bulbState[n-2] != finalArr[n-2]) {
            ret++;
        }
        return ret;
    }

    static int pushSwitch(int x) {
        return (x == 1) ? 0 : 1;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}