import java.io.*;
import java.util.*;

public class Main {

    static class Room implements Comparable<Room> {

        int startTime, endTime;

        public Room(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Room o) {
            return startTime - o.startTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = stoi(br.readLine());
        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            rooms.add(new Room(stoi(st.nextToken()), stoi(st.nextToken())));
        }
        Collections.sort(rooms);

        int ret = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; ++i) {
            if (ret == 0 || pq.peek() > rooms.get(i).startTime) {
                ret++;
            } else {
                pq.poll();
            }
            pq.offer(rooms.get(i).endTime);
        }
        System.out.println(ret);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}