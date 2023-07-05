import java.io.*;
import java.util.*;

public class Main {

    static class Product implements Comparable<Product> {
        long size, value;

        public Product(long size, long value) {
            this.size = size;
            this.value = value;
        }

        @Override
        public int compareTo(Product o) {
            if (size - o.size < 0) {
                return -1;
            }
            return 1;
        }
    }

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = stoi(br.readLine());
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            long a = stol(st.nextToken()), b = stol(st.nextToken());
            products.add(new Product(a, b));
        }
        Collections.sort(products);

        long ret = 0, acc = 0, lo = 0;
        for (int i = 0; i < n; ++i) {
            lo = Math.min(lo, acc - products.get(i).size);
            acc += products.get(i).value;
            ret = Math.max(ret, acc - products.get(i).size - lo);
        }
        System.out.println(ret);
        br.close();
    }

    static long stol(String input) {
        return Long.parseLong(input);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}