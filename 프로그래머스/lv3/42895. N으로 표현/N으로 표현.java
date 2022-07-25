import java.util.*;

class Solution {
    
    final int MAX = 32000;
    int[] dp = new int[32001];
    ArrayList<Integer>[] lists;
    
    public int solution(int N, int number) {
        lists = new ArrayList[9];
        for (int i = 1; i <= 8; ++i) {
            lists[i] = new ArrayList<>();
        }
        init(N);
        int ref = 2;
        while (ref <= 8) {
            for (int i = 1; i <= 7; ++i) {
                for (int j = 1; j <= 7; ++j) {
                    if (ref != i+j) continue;
                    solve(i, j, ref);
                }
            }
            ref++;
        }
        
        if (dp[number] == 0) {
            return -1;
        }
        return dp[number];
    }
    
    void init(int N) {
        int value = N;
        for (int i = 1; i <= 4; ++i) {
            dp[value] = i;
            lists[i].add(value);
            value = value * 10 + N;
        }
    }
    
    void solve(int i, int j, int ref) {
        for (int a : lists[i]) {
            for (int b : lists[j]) {
                if (a+b <= MAX) {
                    if (dp[a+b] == 0) {
                        dp[a+b] = ref;
                        lists[ref].add(a+b);
                    }
                }
                if (a*b <= MAX) {
                    if (dp[a*b] == 0) {
                        dp[a*b] = ref;
                        lists[ref].add(a*b);
                    }
                }
                if (b != 0 && a/b <= MAX) {
                    if (dp[a/b] == 0) {
                        dp[a/b] = ref;
                        lists[ref].add(a/b);
                    }
                }
                if (a-b <= MAX && a-b >= 0) {
                    if (dp[a-b] == 0) {
                        dp[a-b] = ref;
                        lists[ref].add(a-b);
                    }
                }
            }
        }
    }
}