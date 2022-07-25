class Solution {
    
    final long MAX = 1000000000;
    
    public long solution(int n, int[] times) {
        return binarySearch(1, MAX*times.length, n, times);
    }
    
    long binarySearch(long lo, long hi, long n, int[] times) {
        if (lo == hi) {
            return lo;
        }
        long mid = (lo+hi)/2;
        long ref = 0;
        for (int i = 0; i < times.length; ++i) {
            ref += mid / times[i];
        }
        if (ref >= n) {
            return binarySearch(lo, mid, n, times);
        }
        return binarySearch(mid+1, hi, n, times);
    }
}