import java.io.*;
import java.util.*;

public class Main {

    static int len;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());
        while (n-- > 0) {
            int[] cnt = new int[26];
            String word = br.readLine();
            for (int i = 0; i < word.length(); ++i) {
                cnt[word.charAt(i) - 'a']++;
            }
            len = word.length();
            solve(0, cnt, new char[len]);
        }
        System.out.print(sb);
        br.close();
    }

    static void solve(int idx, int[] cnt, char[] anagram) {
        if (idx == len) {
            sb.append(new String(anagram)).append("\n");
            return;
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] == 0) continue;
            cnt[i]--;
            anagram[idx] = (char)('a' + i);
            solve(idx+1, cnt, anagram);
            cnt[i]++;
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}