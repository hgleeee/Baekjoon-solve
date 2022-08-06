import java.util.*;
import java.io.*;

public class Main {

    static class Style {
        String name;
        int power;
        public Style(String name, int power) {
            this.name = name;
            this.power = power;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = stoi(st.nextToken()), m = stoi(st.nextToken());
        ArrayList<Style> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            list.add(new Style(st.nextToken(), stoi(st.nextToken())));
        }
        for (int i = 0; i < m; ++i) {
            int a = stoi(br.readLine());
            int idx = binarySearch(0, list.size() - 1, list, a);
            sb.append(list.get(idx).name + "\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int binarySearch(int lo, int hi, ArrayList<Style> list, int power) {
        if (lo == hi) {
            return lo;
        }
        int mid = (lo+hi)/2;
        if (list.get(mid).power >= power) {
            return binarySearch(lo, mid, list, power);
        }
        return binarySearch(mid+1, hi, list, power);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}