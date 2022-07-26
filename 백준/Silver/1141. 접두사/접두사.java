import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());
        String[] arr = new String[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = br.readLine();
        }
        Arrays.sort(arr, (str1, str2) -> str2.length() - str1.length());
        System.out.println(solve(arr));
        br.close();
    }

    static int solve(String[] arr) {
        List<String> list = new ArrayList<>();
        for (String s : arr) {
            if (!canEnterList(s, list)) continue;
            list.add(s);
        }
        return list.size();
    }

    static boolean canEnterList(String s, List<String> list) {
        int len = s.length();
        for (String ele : list) {
            if (ele.substring(0, len).equals(s)) return false;
        }
        return true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}