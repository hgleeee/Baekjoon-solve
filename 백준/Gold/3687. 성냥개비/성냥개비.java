import java.util.*;
import java.io.*;

public class Main {

    static int[] arr = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
    static String[][] dpMax, dpMin;
    static String maxVal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        dpMax = new String[101][2];
        dpMin = new String[101][2];
        maxVal = "";
        for (int i = 0; i < 80; ++i) {
            maxVal += "9";
        }
        int tst = stoi(br.readLine());
        for (int t = 0; t < tst; ++t) {
            for (int i = 0; i < 101; ++i) {
                Arrays.fill(dpMax[i], null);
                Arrays.fill(dpMin[i], null);
            }
            int n = stoi(br.readLine());
            sb.append(solveMin(n, true) + " " + solveMax(n, true)+ "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static String solveMax(int n, boolean firstEmpty) {
        if (n == 0) {
            return "";
        }
        if (dpMax[n][firstEmpty ? 1:0] != null) {
            return dpMax[n][firstEmpty ? 1:0];
        }
        dpMax[n][firstEmpty ? 1:0] = String.valueOf(0);
        for (int i = 0; i < 10; ++i) {
            if (firstEmpty && i == 0) continue;
            if (n-arr[i] >= 0) {
                String ref = solveMax(n-arr[i], false);
                if (ref.equals("0")) continue;
                dpMax[n][firstEmpty ? 1:0] = maxStr(dpMax[n][firstEmpty ? 1:0], i + ref);
            }
        }
        return dpMax[n][firstEmpty ? 1:0];
    }

    static String solveMin(int n, boolean firstEmpty) {
        if (n == 0) {
            return "";
        }
        if (dpMin[n][firstEmpty ? 1:0] != null) {
            return dpMin[n][firstEmpty ? 1:0];
        }
        dpMin[n][firstEmpty ? 1:0] = maxVal;
        for (int i = 0; i < 10; ++i) {
            if (firstEmpty && i == 0) continue;
            if (n-arr[i] >= 0) {
                dpMin[n][firstEmpty ? 1:0] = minStr(dpMin[n][firstEmpty ? 1:0], i + solveMin(n-arr[i], false));
            }
        }
        return dpMin[n][firstEmpty ? 1:0];
    }

    static String maxStr(String a, String b) {
        if (a.length() != b.length()) {
            if (a.length() > b.length()) {
                return a;
            } else {
                return b;
            }
        } else {
            for (int i = 0; i < a.length(); ++i) {
                if (a.charAt(i) < b.charAt(i)) {
                    return b;
                } else if (a.charAt(i) > b.charAt(i)) {
                    return a;
                }
            }
        }
        return a;
    }

    static String minStr(String a, String b) {
        if (a.length() != b.length()) {
            if (a.length() > b.length()) {
                return b;
            } else {
                return a;
            }
        } else {
            for (int i = 0; i < a.length(); ++i) {
                if (a.charAt(i) < b.charAt(i)) {
                    return a;
                } else if (a.charAt(i) > b.charAt(i)) {
                    return b;
                }
            }
        }
        return a;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}