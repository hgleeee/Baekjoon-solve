import java.util.*;
import java.io.*;

public class Main {

    static class Word {
        String word;
        int cnt;
        public Word(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = stoi(st.nextToken()), m = stoi(st.nextToken());
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            String input = br.readLine();
            if (input.length() < m) continue;
            if (hashMap.containsKey(input)) {
                hashMap.replace(input, hashMap.get(input)+1);
            } else {
                hashMap.put(input, 1);
            }
        }

        PriorityQueue<Word> pq = new PriorityQueue<>(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                if (o1.cnt != o2.cnt) {
                    return o2.cnt - o1.cnt;
                }
                if (o1.word.length() != o2.word.length()) {
                    return o2.word.length() - o1.word.length();
                }
                for (int i = 0; i < o1.word.length(); ++i) {
                    int gap = o1.word.charAt(i) - o2.word.charAt(i);
                    if (gap != 0) {
                        return gap;
                    }
                }
                return -1;
            }
        });

        for (String ele : hashMap.keySet()) {
            pq.offer(new Word(ele, hashMap.get(ele)));
        }

        while (!pq.isEmpty()) {
            sb.append(pq.poll().word + "\n");
        }
        System.out.print(sb);
        br.close();
    }



    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}