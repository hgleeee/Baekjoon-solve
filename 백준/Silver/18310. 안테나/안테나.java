import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] houseNumPerIdx;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = stoi(br.readLine());
        houseNumPerIdx = new int[100001];
        st = new StringTokenizer(br.readLine());
        int maxPos = -1;
        for (int i = 0; i < n; ++i) {
            int idx = stoi(st.nextToken());
            houseNumPerIdx[idx]++;
            maxPos = Math.max(maxPos, idx);
        }
        System.out.println(findPos(maxPos));
        br.close();
    }

    static int findPos(int maxPos) {
        int pos = 1;
        int beforeNum = 0, afterNum = n;
        while (pos <= maxPos) {
            beforeNum += houseNumPerIdx[pos];
            afterNum -= houseNumPerIdx[pos];
            if (afterNum - beforeNum <= 0) {
                break;
            }
            pos++;
        }
        return pos;
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}