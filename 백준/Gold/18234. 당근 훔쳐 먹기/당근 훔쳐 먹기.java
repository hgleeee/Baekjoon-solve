import java.io.*;
import java.util.*;

public class Main {

    static class Carrot {
        int w, p; // w는 초기, p는 성장률

        public Carrot(int w, int p) {
            this.w = w;
            this.p = p;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken()), t = stoi(st.nextToken());

        List<Carrot> carrots = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            carrots.add(new Carrot(stoi(st.nextToken()), stoi(st.nextToken())));
        }
        Collections.sort(carrots, (o1, o2) -> (o1.p - o2.p));
        long ret = maxFlavor(t, carrots);

        System.out.println(ret);
        br.close();
    }

    static long maxFlavor(int t, List<Carrot> carrots) {
        long ret = 0;
        int n = carrots.size();
        for (int i = 0; i < n; ++i) {
            ret += carrots.get(i).w + (long)carrots.get(i).p * (t - (n-i));
        }
        return ret;
    }


    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}