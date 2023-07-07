import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cardNum = new int[10];
        for (int i = 0; i < 13; ++i) {
            cardNum[stoi(st.nextToken())]++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 9; ++i) {
            cardNum[i]++;
            if (isCompleted(cardNum)) {
                sb.append(i).append(" ");
            }
            cardNum[i]--;
        }
        if (sb.toString().length() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }
        br.close();
    }

    static boolean isCompleted(int[] cardNum) {
        for (int i = 1; i <= 9; ++i) {
            if (cardNum[i] > 4) return false;
        }
        return backtracking(1, cardNum, 0, 0, -1);
    }

    static boolean backtracking(int card, int[] cardNum, int headNum, int bodyNum, int lastHead) {
        if (card == 10) {
            if (headNum == 1 && bodyNum == 4 || headNum == 7) {
                return true;
            }
            return false;
        }
        boolean ret = false;
        if (cardNum[card] == 0) {
            ret |= backtracking(card+1, cardNum, headNum, bodyNum, lastHead);
            return ret;
        }
        if (cardNum[card] >= 3) {
            cardNum[card]-=3;
            ret |= backtracking(card, cardNum, headNum, bodyNum + 1, lastHead);
            cardNum[card]+=3;
        }
        if (card <= 7 && cardNum[card] > 0 && cardNum[card+1] > 0 && cardNum[card+2] > 0) {
            cardNum[card]--;
            cardNum[card + 1]--;
            cardNum[card + 2]--;
            ret |= backtracking(card, cardNum, headNum, bodyNum + 1, lastHead);
            cardNum[card]++;
            cardNum[card+1]++;
            cardNum[card+2]++;
        }
        if (cardNum[card] >= 2 && lastHead != card) {
            cardNum[card]-=2;
            ret |= backtracking(card, cardNum, headNum + 1, bodyNum, card);
            cardNum[card]+=2;
        }
        return ret;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}