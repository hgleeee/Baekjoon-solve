import java.io.*;
import java.util.*;

public class Main {

    static int n, m, l;
    static List<Integer> posList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        l = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            posList.add(stoi(st.nextToken()));
        }
        Collections.sort(posList);
        System.out.println(binarySearch(1, l-1));
        br.close();
    }

    static int binarySearch(int lo, int hi) {
        if (lo == hi) {
            return lo;
        }
        int mid = (lo + hi) / 2;
        int cnt = 0;
        for (int i = 1; i < n; ++i) {
            cnt += getRestPlaceNum(posList.get(i) - posList.get(i-1), mid);
        }
        if (n >= 1) {
            cnt += getRestPlaceNum(posList.get(0), mid);
            cnt += getRestPlaceNum(l - posList.get(n-1), mid);
        } else {
            cnt += getRestPlaceNum(l, mid);
        }
        if (cnt <= m) {
            return binarySearch(lo, mid);
        }
        return binarySearch(mid+1, hi);
    }

    static int getRestPlaceNum(int dist, int gap) {
        if (dist % gap == 0) {
            return dist / gap - 1;
        }
        return dist / gap;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}