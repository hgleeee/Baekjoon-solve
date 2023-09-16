import java.util.*;

class Solution {
    
    class Info implements Comparable<Info> {
        int start, end;
        
        Info(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public int compareTo(Info i) {
            return start - i.start;
        }
    }
    
    public int solution(String[][] book_time) {
        List<Info> infos = new ArrayList<>();
        for (int i = 0; i < book_time.length; ++i) {
            infos.add(new Info(trans(book_time[i][0]), trans(book_time[i][1])+10));
        }
        Collections.sort(infos);
        
        PriorityQueue<Integer> endTimePq = new PriorityQueue<>();
        endTimePq.offer(0);
        int answer = 1;
        for (int i = 0; i < infos.size(); ++i) {
            Info info = infos.get(i);
            int earliestEndTime = endTimePq.peek();
            if (earliestEndTime <= info.start) {
                endTimePq.poll();
            } else {
                answer++;
            }
            endTimePq.offer(info.end);            
        }
        return answer;
    }
    
    int trans(String time) {
        return Integer.parseInt(time.substring(0,2))*60 + Integer.parseInt(time.substring(3,5));
    }
}