import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = stoi(br.readLine());
        while (t-- > 0) {
            String word = br.readLine();
            sb.append(nextWord(word)).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static String nextWord(String word) {
        char[] arr = word.toCharArray();

        // 감소 부분 찾기
        char x = arr[word.length()-1];
        int idx = -1;
        for (int i = word.length()-1; i >= 0; --i) {
            if (arr[i] < x) {
                idx = i;
                break;
            }
            x = arr[i];
        }
        if (idx == -1) {
            return word;
        }

        int gap = 987654321, toMoveIdx = -1;
        char a = arr[idx];
        char newA = '.';
        for (int i = idx+1; i < word.length(); ++i) {
            if (a < arr[i] && gap > arr[i] - a) {
                gap = arr[i]-a;
                newA = arr[i];
                toMoveIdx = i;
            }
        }

        for (int i = toMoveIdx; i >= idx+1; --i) {
            arr[i] = arr[i-1];
        }
        arr[idx] = newA;
        Arrays.sort(arr, idx+1, arr.length);
        return new String(arr);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}