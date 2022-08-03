import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());
        String arr = br.readLine();
        System.out.println(solve(arr));
        br.close();
    }

    static int solve(String arr) {
        int ret = Integer.MAX_VALUE;
        int len = arr.length();
        int cntFront = 0;
        ret = Math.min(ret, checkFromFront(arr));
        ret = Math.min(ret, checkFromBack(arr));
        int cnt = 0;
        for (int i = 0; i < len; ++i) {
            if (arr.charAt(i) == 'R') {
                cnt++;
            }
        }
        ret = Math.min(cnt, ret);
        ret = Math.min(len-cnt, ret);
        return ret;
    }

    static int checkFromBack(String arr) {
        int cnt = 0, len = arr.length();
        int idx = 0;
        char x = arr.charAt(len-1);
        for (int i = len-1; i >= 0; --i) {
            if (arr.charAt(i) != x) {
                idx = i;
                break;
            }
        }
        for (int i = idx; i >= 0; --i) {
            if (arr.charAt(i) == x) {
                cnt++;
            }
        }
        return cnt;
    }

    static int checkFromFront(String arr) {
        int cnt = 0, len = arr.length();
        int idx = 0;
        char x = arr.charAt(0);
        for (int i = 1; i < len; ++i) {
            if (arr.charAt(i) != x) {
                idx = i;
                break;
            }
        }
        for (int i = idx; i < len; ++i) {
            if (arr.charAt(i) == x) {
                cnt++;
            }
        }
        return cnt;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}