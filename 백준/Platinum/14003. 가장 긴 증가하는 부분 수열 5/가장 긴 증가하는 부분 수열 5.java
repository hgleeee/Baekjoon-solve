import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        n = stoi(br.readLine());
        st = new StringTokenizer(br.readLine());
        int arr[] = new int[n+1];
        int indexArr[] = new int[n+1];
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        list.add(INF);
        for(int i = 1 ; i <= n; i++){
            int now = arr[i];
            if(now > list.get(list.size() - 1)) {
                list.add(now);
                indexArr[i] = list.size() - 1;
            }
            else {
                int idx = binarySearch(0, list.size()-1, now);
                list.set(idx, now);
                indexArr[i] = idx;
            }
        }
        sb.append(list.size() + "\n");
        Stack<Integer> stack = new Stack();
        int index = list.size() - 1;
        for(int i = n; i >= 1; --i){
            if(indexArr[i] == index){
                index--;
                stack.push(arr[i]);
            }
        }

        while (!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }
        System.out.println(sb);
        br.close();
    }

    static int binarySearch(int lo, int hi, int val) {
        if (lo == hi) {
            return lo;
        }
        int mid = (lo+hi)/2;
        if (list.get(mid) >= val) {
            return binarySearch(lo, mid, val);
        }
        return binarySearch(mid+1, hi, val);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}