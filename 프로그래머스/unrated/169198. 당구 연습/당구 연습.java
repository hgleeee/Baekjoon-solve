import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        for (int i = 0; i < balls.length; ++i) {
            int x = balls[i][0], y = balls[i][1];
            answer[i] = solve(m, n, startX, startY, x, y);
        }
        return answer;
    }
    
    int solve(int m, int n, int sx, int sy, int x, int y) {
        int[][] transPos = new int[][]{{-x, y}, {x, -y}, {2*m-x, y}, {x, 2*n-y}};
        int ret = 987654321;
        for (int i = 0; i < 4; ++i) {
            if (sx == x && ((i == 3 && y > sy) || (i == 1 && y < sy))) continue;
            if (sy == y && ((i == 2 && x > sx) || (i == 0 && x < sx))) continue;
            int tmp = (int)Math.pow((transPos[i][0]-sx), 2) + (int)Math.pow((transPos[i][1]-sy), 2);
            ret = Math.min(ret, tmp);
        }
        return ret;
    }
}