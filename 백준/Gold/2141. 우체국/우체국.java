import java.io.*;
import java.util.*;

public class Main {

    static class Point implements Comparable<Point> {
        int x, a;

        public Point(int x, int a) {
            this.x = x;
            this.a = a;
        }
        @Override
        public int compareTo(Point o) {
            return x - o.x;
        }
    }

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = stoi(br.readLine());

        List<Point> list = new ArrayList<>();
        long total = 0;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken()), a = stoi(st.nextToken());
            list.add(new Point(x, a));
            total += a;
        }
        Collections.sort(list);
        long tmp = 0;
        for (int i = 0; i < n; ++i) {
            tmp += list.get(i).a;
            if (tmp >= (total+1)/2) {
                System.out.println(list.get(i).x);
                break;
            }
        }
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}