import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine(), T = br.readLine();
        if (dfs(S, T)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
        br.close();
    }

    static boolean dfs(String S, String T) {
        boolean ret = false;
        if (S.length() == T.length()) {
            if (S.equals(T)) {
                return true;
            }
            return false;
        }
        if (T.charAt(0) == 'B') {
            String subStr = T.substring(1);
            StringBuffer sb = new StringBuffer(subStr);
            ret |= dfs(S, sb.reverse().toString());
        }
        if (T.charAt(T.length()-1) == 'A') {
            ret |= dfs(S, T.substring(0, T.length()-1));
        }
        return ret;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}