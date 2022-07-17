import java.util.*;
import java.io.*;

public class Main {

    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = stoi(st.nextToken());
        }
        list.add(0);
        for (int i = 0; i < n; ++i) {
            int len = list.size();
            if (list.get(len-1) < arr[i]) {
                list.add(arr[i]);
            } else {
                int idx = binarySearch(0, len - 1, arr[i]);
                list.set(idx, arr[i]);
            }
        }
        System.out.println(n - (list.size()-1));
        br.close();
    }

    static int binarySearch(int lo, int hi, int val) {
        if (lo == hi) {
            return lo;
        }
        int mid = (lo+hi)/2;
        if (list.get(mid) >= val) {
            return binarySearch(lo, mid, val);
        }
        return binarySearch(mid+1, hi, val);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}