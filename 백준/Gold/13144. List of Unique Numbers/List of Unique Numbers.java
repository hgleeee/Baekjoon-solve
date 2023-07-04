import java.io.*;
import java.util.*;

public class Main {

    static long[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = stoi(br.readLine());
        sum = new long[n+1];
        init(n);
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            list.add(stoi(st.nextToken()));
        }
        System.out.println(solve(n, list));
        br.close();
    }

    static long solve(int n, List<Integer> list) {
        long ret = 0;
        int startIdx = 0;
        int[] idxArr = new int[n+1];
        Arrays.fill(idxArr, -1);
        for (int i = 0; i < list.size(); ++i) {
            int num = list.get(i);
            if (idxArr[num] != -1 && idxArr[num] >= startIdx) {
                ret += sum[i - startIdx - 1];
                if (i >= idxArr[num] + 2) {
                    ret -= sum[i - idxArr[num] - 2];
                }
                startIdx = idxArr[num]+1;
            }
            idxArr[num] = i;
        }
        ret += sum[n - startIdx - 1];
        ret += n;
        return ret;
    }

    static void init(int n) {
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i-1] + i;
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}