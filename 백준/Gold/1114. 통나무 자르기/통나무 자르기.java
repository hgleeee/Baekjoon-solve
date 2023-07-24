import java.io.*;
import java.util.*;

public class Main {

    static int L, K, C;
    static List<Integer> pos = new ArrayList<>();
    static int firstPos;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = stoi(st.nextToken());
        K = stoi(st.nextToken());
        C = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; ++i) {
            pos.add(stoi(st.nextToken()));
        }
        Collections.sort(pos);
        System.out.println(binarySearch(1, L) + " " + firstPos);
        br.close();
    }

    static int binarySearch(int lo, int hi) {
        if (lo == hi) {
            return lo;
        }
        int mid = (lo+hi) / 2;
        int firstPosIdx = judge(mid);
        if (firstPosIdx == -1) {
            return binarySearch(mid+1, hi);
        }
        firstPos = firstPosIdx;
        return binarySearch(lo, mid);
    }

    static int judge(int len) {
        int fPos = L, idx = K-1, total = C;
        while (idx >= 0 && total > 0) {
            int dist = fPos - pos.get(idx);
            if (dist > len) {
                if (idx == K-1 || pos.get(idx+1) == fPos) return -1;
                fPos = pos.get(idx+1);
                total--;
            } else if (dist == len) {
                fPos = pos.get(idx);
                total--;
                idx--;
            } else {
                idx--;
            }
        }
        if (total > 0 && pos.get(idx+1) <= len) {
            return pos.get(0);
        }
        if (total == 0 && pos.get(idx+1) <= len) {
            return pos.get(idx+1);
        }
        return -1;
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}