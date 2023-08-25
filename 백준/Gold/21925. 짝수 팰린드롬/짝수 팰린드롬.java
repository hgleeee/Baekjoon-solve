import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = stoi(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = stoi(st.nextToken());
        }
        int startIdx = 0, cnt = 0;
        while (startIdx < n) {
            boolean unsatisfied = true;
            for (int i = 0; i*2+1+startIdx < n; ++i) {
                int endIdx = startIdx + i*2+1;
                if (isPelin(startIdx, endIdx, arr)) {
                    startIdx = endIdx+1;
                    unsatisfied = false;
                    cnt++;
                    break;
                }
            }
            if (unsatisfied) {
                cnt = -1;
                break;
            }
        }
        System.out.println(cnt);
        br.close();
    }

    static boolean isPelin(int startIdx, int endIdx, int[] arr) {
        while (startIdx < endIdx) {
            if (arr[startIdx] != arr[endIdx]) {
                return false;
            }
            startIdx++;
            endIdx--;
        }
        return true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}