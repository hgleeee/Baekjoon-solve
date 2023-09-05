import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static long k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        k = Long.parseLong(st.nextToken());
        System.out.println(binarySearch(0, n/2));
        br.close();
    }

    static String binarySearch(int lo, int hi) {
        if (lo == hi) {
            if (getNumOfPieces(lo) == k) {
                return "YES";
            }
            return "NO";
        }
        int mid = (lo + hi) / 2;
        long ref = getNumOfPieces(mid);
        if (ref >= k) {
            return binarySearch(lo, mid);
        }
        return binarySearch(mid+1, hi);
    }

    static long getNumOfPieces(int x) {
        return (1 + x) * (1 + (long)n - x);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}