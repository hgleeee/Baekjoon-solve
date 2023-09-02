import java.io.*;
import java.util.*;

public class Main {

    static class Egg {
        int hp, weight;

        public Egg(int hp, int weight) {
            this.hp = hp;
            this.weight = weight;
        }
    }

    static int n;
    static Egg[] eggArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = stoi(br.readLine());
        eggArr = new Egg[n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int s = stoi(st.nextToken()), w = stoi(st.nextToken());
            eggArr[i] = new Egg(s, w);
        }
        backtracking(0, 0);
        System.out.println(ans);
        br.close();
    }

    static int ans = -1;

    static void backtracking(int idx, int brokenEggNum) {
        if (idx == n) {
            ans = Math.max(ans, brokenEggNum);
            return;
        }
        Egg nowEgg = eggArr[idx];
        if (nowEgg.hp <= 0) {
            backtracking(idx+1, brokenEggNum);
            return;
        }
        boolean notContinued = false;
        for (int i = 0; i < n; ++i) {
            Egg otherEgg = eggArr[i];
            if (i == idx || otherEgg.hp <= 0) {
                continue;
            }
            notContinued = true;
            nowEgg.hp -= otherEgg.weight;
            otherEgg.hp -= nowEgg.weight;
            if (nowEgg.hp <= 0) {
                brokenEggNum++;
            }
            if (otherEgg.hp <= 0) {
                brokenEggNum++;
            }
            backtracking(idx+1, brokenEggNum);
            if (nowEgg.hp <= 0) {
                brokenEggNum--;
            }
            if (otherEgg.hp <= 0) {
                brokenEggNum--;
            }
            nowEgg.hp += otherEgg.weight;
            otherEgg.hp += nowEgg.weight;
        }

        if (!notContinued) {
            backtracking(idx+1, brokenEggNum);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}