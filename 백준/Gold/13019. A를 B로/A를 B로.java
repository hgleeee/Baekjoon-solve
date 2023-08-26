import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine(), b = br.readLine();
        int[] info = searchUncommonPart(a, b);
        if (info[1] == a.length() || cannotChange(a, b)) {
            System.out.println(-1);
            return;
        }
        System.out.println(solve(a, b, info[1], info[0]));
        br.close();
    }

    static boolean cannotChange(String a, String b) {
        int[] aArr = new int[26], bArr = new int[26];
        for (int i = 0; i < a.length(); ++i) {
            aArr[a.charAt(i) - 'A']++;
            bArr[b.charAt(i) - 'A']++;
        }
        for (int i = 0; i < 26; ++i) {
            if (aArr[i] != bArr[i]) {
                return true;
            }
        }
        return false;
    }

    static int solve(String a, String b, int lastAIdx, int bIdx) {
        int cnt = 0, nowBIdx = bIdx;
        for (int i = lastAIdx-1; i >= 0; --i) {
            if (a.charAt(i) == b.charAt(nowBIdx)) {
                nowBIdx--;
                cnt++;
            }
        }
        return bIdx+1 - cnt;
    }

    // b 기준으로 0부터 해당 반환값까지는 a에서 조정해야 하는 부분이다.
    static int[] searchUncommonPart(String a, String b) {
        int bIdx = b.length()-1, lastAIdx = a.length();
        for (int i = a.length()-1; i >= 0; --i) {
            if (a.charAt(i) == b.charAt(bIdx)) {
                lastAIdx = i;
                bIdx--;
            }
        }
        return new int[]{bIdx, lastAIdx};
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}