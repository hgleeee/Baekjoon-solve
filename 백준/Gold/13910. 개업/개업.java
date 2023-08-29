import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken()), m = stoi(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] workArr = new int[m];
        for (int i = 0; i < m; ++i) {
            workArr[i] = stoi(st.nextToken());
        }

        Set<Integer> doubleWork = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            for (int j = i+1; j < m; ++j) {
                doubleWork.add(workArr[i] + workArr[j]);
            }
        }
        for (int i = 0; i < m; ++i) {
            doubleWork.add(workArr[i]);
        }

        List<Integer> doubleWorkList = doubleWork.stream().sorted().collect(Collectors.toList());

        int[] dp = new int[n+1];
        Arrays.fill(dp, INF);
        dp[n] = 0;
        for (int i = n; i >= 1; --i) {
            for (int j = 0; j < doubleWorkList.size(); ++j) {
                int nextIdx = i - doubleWorkList.get(j);
                if (nextIdx < 0) break;
                dp[nextIdx] = Math.min(dp[nextIdx], dp[i] + 1);
            }
        }
        if (dp[0] == INF) {
            System.out.println(-1);
        } else {
            System.out.println(dp[0]);
        }
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}