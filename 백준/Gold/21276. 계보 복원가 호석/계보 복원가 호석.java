import java.io.*;
import java.util.*;

public class Main {

    static class Person implements Comparable<Person> {
        String name;
        PriorityQueue<String> pq = new PriorityQueue<>();

        public Person(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            return name.compareTo(o.name);
        }
    }

    static int n, m;
    static List<Person> personList;
    static Map<String, Integer> nameMap = new HashMap<>();
    static List<Integer>[] graph;
    static int[] parentNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = stoi(br.readLine());
        parentNum = new int[n];
        graph = new List[n];
        personList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            String name = st.nextToken();
            personList.add(new Person(name));
            nameMap.put(name, i);
            graph[i] = new ArrayList<>();
        }

        m = stoi(br.readLine());
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken(), b = st.nextToken();
            graph[nameMap.get(b)].add(nameMap.get(a));
            parentNum[nameMap.get(a)]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        List<String> rootList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (parentNum[i] == 0) {
                queue.offer(i);
                rootList.add(personList.get(i).name);
            }
        }
        Collections.sort(rootList);

        StringBuilder sb = new StringBuilder();
        sb.append(rootList.size()).append("\n");
        for (String root : rootList) {
            sb.append(root).append(" ");
        }
        sb.append("\n");

        while (!queue.isEmpty()) {
            int nowIdx = queue.poll();
            for (int nextIdx : graph[nowIdx]) {
                if (--parentNum[nextIdx] == 0) {
                    personList.get(nowIdx).pq.offer(personList.get(nextIdx).name);
                    queue.offer(nextIdx);
                }
            }
        }

        Collections.sort(personList);
        for (Person person : personList) {
            sb.append(person.name).append(" ").append(person.pq.size()).append(" ");
            while (!person.pq.isEmpty()) {
                sb.append(person.pq.poll()).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}