import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int len = lines.length;
        int[][] metaLines = new int[len][2];
        for (int i = 0; i < len; ++i) {
            String[] subArr = lines[i].split(" ");
            int now = transToTime(subArr[1]);
            int gap = transToSec(subArr[2].substring(0, subArr[2].length()-1));
            metaLines[i][0] = now;
            metaLines[i][1] = gap;
        }
        return solve(metaLines);
    }
    
    int solve(int[][] metaLines) {
        int ret = 0;
        int baseGap = 1000, len = metaLines.length, idx = 0;
        while (idx < len) {
            int lo = metaLines[idx][0], hi = lo + baseGap;
            int subAns = 0;
            for (int i = 0; i < len; ++i) {
                if (metaLines[i][0] - metaLines[i][1] >= hi-1 
                    || metaLines[i][0] < lo) continue;
                subAns++;
            }
            ret = Math.max(subAns, ret);
            idx++;
        }
        return ret;
    }
    
    int transToTime(String input) {
        int ret = 0;
        String[] nowInform = input.split(":");
        ret += stoi(nowInform[0]) * 3600000 + stoi(nowInform[1]) * 60000;
        ret += transToSec(nowInform[2]);
        return ret;
    }
    
    int transToSec(String input) {
        String[] secInform = input.split("\\.");
        if (secInform.length == 1) {
            return stoi(secInform[0]) * 1000;
        }
        return stoi(secInform[0]) * 1000 + stoi(secInform[1]);
    }
    
    int stoi(String input) {
        return Integer.parseInt(input);
    }
}