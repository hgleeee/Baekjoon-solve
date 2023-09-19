import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        return binarySearch(1, 1000000001, distance, rocks, n);
    }
    
    int binarySearch(int lo, int hi, int distance, int[] rocks, int n) {
        if (lo+1 == hi) {
            return lo;
        }
        int mid = (lo+hi) / 2;
        int cnt = 0, s = 0; // cnt는 제거해야 하는 바위 수
        for (int i = 0; i < rocks.length; ++i) {
            if (rocks[i] - s < mid) {
                cnt++;
                continue;
            }
            s = rocks[i];
        }
        if (distance - s < mid) {
            cnt++;
        }
        if (n < cnt) {
            return binarySearch(lo, mid, distance, rocks, n);
        }
        return binarySearch(mid, hi, distance, rocks, n);
    }
}