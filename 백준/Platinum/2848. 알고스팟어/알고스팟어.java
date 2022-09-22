import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int outputNum = 0;
        int numParent[] = new int[26];
        Arrays.fill(numParent, -1);

        String word[] = new String[n];
        for (int i = 0; i < n; ++i) {
            word[i] = br.readLine();
            for (char a : word[i].toCharArray()) {
                if (numParent[a-'a'] == -1) {
                    numParent[a-'a'] = 0;
                    outputNum++;
                }
            }
        }

        HashMap<Character, ArrayList<Character>> graph;
        graph = new HashMap<>();
        for (int i = 0; i < 26; ++i) {
            char sub = (char)('a' + i);
            graph.put(sub, new ArrayList<>());
        }
        char nowChar, nextChar;
        boolean shouldBeEM = false;
        boolean isBreak = false;
        for (int i = 0; i < n-1; ++i) {
            isBreak = false;
            for (int j = 0; j < Math.min(word[i].length(), word[i+1].length()); ++j) {
                nowChar = word[i].charAt(j);
                nextChar = word[i+1].charAt(j);
                if (nowChar == nextChar) {
                    continue;
                }
                else {
                    graph.get(nowChar).add(nextChar);
                    numParent[nextChar - 'a']++;
                    isBreak = true;
                    break;
                }
            }
            if (word[i].length() > word[i+1].length() && !isBreak) {
                shouldBeEM = true;
            }
        }

        Queue<Character> myQ = new LinkedList<>();
        boolean canPrint = true;
        for (int i = 0; i < 26; ++i) {
            if (numParent[i] == 0) {
                myQ.add((char)('a'+i));
            }
        }
        if (myQ.size() > 1) canPrint = false;

        ArrayList<Character> toPrint = new ArrayList<>();
        while (!myQ.isEmpty()) {
            if (myQ.size() > 1) {
                canPrint = false;
            }
            char now = myQ.poll();
            toPrint.add(now);
            for (char child : graph.get(now)) {
                numParent[child-'a']--;
                if (numParent[child-'a'] == 0) {
                    myQ.add(child);
                }
            }
        }

        if (canPrint && toPrint.size() == outputNum && !shouldBeEM) {
            for (char sub : toPrint) {
                sb.append(String.valueOf(sub));
            }
        } else if (toPrint.size() != outputNum || shouldBeEM) {
            sb.append("!");
        } else {
            sb.append("?");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}





