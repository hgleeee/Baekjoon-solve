import java.util.*;

class Solution {
    
    int h;
    int[][] dp;
    
    public int solution(int[][] triangle) {
        h = triangle.length;
        dp = new int[h][h];
        for (int i = 0; i < h; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return solve(triangle, 0, 0);
    }
    
    int solve(int[][] triangle, int height, int idx) {
        if (height == h) {
            return 0;
        }
        if (dp[height][idx] != -1) {
            return dp[height][idx];
        }
        dp[height][idx] = triangle[height][idx];
        int ref = 0;
        ref = Math.max(ref, solve(triangle, height+1, idx));
        ref = Math.max(ref, solve(triangle, height+1, idx+1));
        dp[height][idx] += ref;
        return dp[height][idx];
    }
}