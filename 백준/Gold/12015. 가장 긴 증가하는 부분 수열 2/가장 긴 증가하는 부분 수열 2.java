import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n+1];
        for (int i = 1; i <= n; ++i) {
            arr[i] = stoi(st.nextToken());
        }

        list.add(0);
        for (int i = 1; i <= n; ++i) {
            int listLen = list.size();
            Integer lastValue = list.get(listLen - 1);
            if (lastValue < arr[i]) {
                list.add(arr[i]);
            } else {
                list.set(binarySearch(1, listLen-1, arr[i]), arr[i]);
            }
        }
        System.out.println(list.size()-1);
        br.close();
    }

    static int binarySearch(int lo, int hi, int val) {
        if (lo == hi) {
            return lo;
        }
        int mid = (lo+hi)/2;
        if (val <= list.get(mid)) {
            return binarySearch(lo, mid, val);
        }
        return binarySearch(mid+1, hi, val);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}