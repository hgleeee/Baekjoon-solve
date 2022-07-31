import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = stoi(br.readLine());
        while (--t >= 0) {
            String str = br.readLine();
            int k = stoi(br.readLine());
            if (k == 1) {
                sb.append("1 1\n");
                continue;
            }

            int[] arr = new int[26];
            for(int j = 0; j < str.length(); j++) {
                arr[str.charAt(j) - 'a']++;
            }

            int min = Integer.MAX_VALUE;
            int max = -1;
            for (int j = 0; j < str.length(); j++) {
                if (arr[str.charAt(j) - 'a'] < k) continue;

                int count = 1;
                for (int l = j + 1; l < str.length(); l++) {
                    if (str.charAt(j) == str.charAt(l)) count++;
                    if (count == k) {
                        min = Math.min(min, l - j + 1);
                        max = Math.max(max, l - j + 1);
                        break;
                    }
                }
            }
            if (min == Integer.MAX_VALUE || max == -1) sb.append("-1\n");
            else sb.append(min + " " + max + "\n");
        }
        System.out.print(sb);
    }


    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}