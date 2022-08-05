import java.util.*;
import java.io.*;

public class Main {

    static class Room {
        int min, max;
        PriorityQueue<Member> pq = new PriorityQueue<>((m1, m2) -> m1.nickname.compareTo(m2.nickname));
        public Room(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    static class Member {
        int level;
        String nickname;
        public Member(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }
    }

    static int p, m;
    static List<Room> roomList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        p = stoi(st.nextToken());
        m = stoi(st.nextToken());
        for (int i = 0; i < p; ++i) {
            st = new StringTokenizer(br.readLine());
            int level = stoi(st.nextToken());
            String nickname = st.nextToken();
            boolean isEntered = false;
            for (int j = 0; j < roomList.size(); ++j) {
                if (roomList.get(j).pq.size() < m && level >= roomList.get(j).min && level <= roomList.get(j).max) {
                    roomList.get(j).pq.offer(new Member(level, nickname));
                    isEntered = true;
                    break;
                }
            }
            if (!isEntered) {
                Room room = new Room(level-10, level+10);
                room.pq.offer(new Member(level, nickname));
                roomList.add(room);
            }
        }
        for (Room now : roomList) {
            if (now.pq.size() == m) {
                sb.append("Started!\n");
            } else {
                sb.append("Waiting!\n");
            }
            while (!now.pq.isEmpty()) {
                Member member = now.pq.poll();
                sb.append(member.level + " " + member.nickname + "\n");
            }
        }
        System.out.print(sb);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}