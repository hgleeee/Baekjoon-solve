import java.util.*;
import java.io.*;

public class Main {

    static int k, n;
    static boolean checked[];
    static Set<Integer>[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        k = stoi(br.readLine());
        n = stoi(br.readLine());
        group = new Set[9];
        checked = new boolean[1000000];

        for (int i = 1; i <= 8; ++i) {
            group[i] = new HashSet<>();
        }
        int num = k;
        for (int i = 1; i <= 6; ++i) {
            checked[num] = true;
            group[i].add(num);
            num *= 10;
            num += k;
        }

        for (int i = 2; i <= 8; ++i) {
            for (int j = 1; j < i; ++j) {
                for (int x : group[j]) {
                    for (int y : group[i-j]) {
                        if (x + y < 1000000 && !checked[x+y]) {
                            checked[x+y] = true;
                            group[i].add(x+y);
                        }
                        if (y != 0 && x / y > 0 && !checked[x/y]) {
                            checked[x/y] = true;
                            group[i].add(x/y);
                        }
                        if (x - y >= 0 && !checked[x-y]) {
                            checked[x-y] = true;
                            group[i].add(x-y);
                        }
                        if (x * y < 1000000 && !checked[x*y]) {
                            checked[x*y] = true;
                            group[i].add(x*y);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            int a = stoi(br.readLine());
            if (!checked[a]) {
                sb.append("NO\n");
                continue;
            }
            for (int k = 1; k <= 8; ++k) {
                if (group[k].contains(a)) {
                    sb.append(k + "\n");
                    break;
                }
            }
        }
        System.out.print(sb);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }


}