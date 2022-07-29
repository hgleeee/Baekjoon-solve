import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        List<Integer> bIdx = new ArrayList<>();
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(i) == 'b') {
                bIdx.add(i);
            }
        }
        int total = bIdx.size();
        int lo = 0, hi = total-1, max = 0;
        int num = 0;
        for (int i = 0; i <= hi; ++i) {
            if (input.charAt(i) == 'b') num++;
        }
        max = Math.max(max, num);
        lo++;
        hi++;
        while (lo < input.length()) {
            if (hi == input.length()) {
                hi -= input.length();
            }
            if (input.charAt(lo-1) == 'b') {
                num--;
            }
            if (input.charAt(hi) == 'b') {
                num++;
            }
            max = Math.max(max, num);
            lo++;
            hi++;
        }
        System.out.println(total - max);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}