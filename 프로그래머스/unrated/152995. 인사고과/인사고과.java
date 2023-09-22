import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] myScore = scores[0];
        Arrays.sort(scores, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : b[0] - a[0]);
        int answer = 1, maxScore = -1;
        for (int[] score : scores) {
            if (score[1] < maxScore) {
                if (myScore.equals(score)) {
                    return -1;
                }
            } else {
                maxScore = score[1];
                if (score[0] + score[1] > myScore[0] + myScore[1]) {
                    answer++;
                }
            }
        }
        return answer;
    }
}