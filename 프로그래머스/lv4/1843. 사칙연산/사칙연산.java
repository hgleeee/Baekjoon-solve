import java.util.*;

class Solution {
    
    static final int INF = 987654321, MIN = -987654321;
    int[][] maxDp, minDp;
    
    public int solution(String arr[]) {
        maxDp = new int[arr.length][arr.length];
        minDp = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; ++i) {
            Arrays.fill(maxDp[i], MIN);
            Arrays.fill(minDp[i], INF);
        }
        return solveMax(0, arr.length-1, arr);
    }
    
    int solveMax(int lo, int hi, String[] arr) {
        if (lo == hi) {
            return stoi(arr[lo]);
        }
        if (maxDp[lo][hi] != MIN) {
            return maxDp[lo][hi];
        }
        for (int i = lo; i <= hi-2; i += 2) {
            if (arr[i+1].equals("+")) {
                maxDp[lo][hi] = Math.max(maxDp[lo][hi], solveMax(lo, i, arr) + solveMax(i+2, hi, arr));
            } else {
                maxDp[lo][hi] = Math.max(maxDp[lo][hi], solveMax(lo, i, arr) - solveMin(i+2, hi, arr));
            }
        }
        return maxDp[lo][hi];
    }
    
    int solveMin(int lo, int hi, String[] arr) {
        if (lo == hi) {
            return stoi(arr[lo]);
        }
        if (minDp[lo][hi] != INF) {
            return minDp[lo][hi];
        }
        for (int i = lo; i <= hi-2; i += 2) {
            if (arr[i+1].equals("+")) {
                minDp[lo][hi] = Math.min(minDp[lo][hi], solveMin(lo, i, arr) + solveMin(i+2, hi, arr));
            } else {
                minDp[lo][hi] = Math.min(minDp[lo][hi], solveMin(lo, i, arr) - solveMax(i+2, hi, arr));
            }
        }
        return minDp[lo][hi];
    }
    
    int stoi(String input) {
        return Integer.parseInt(input);
    }
}