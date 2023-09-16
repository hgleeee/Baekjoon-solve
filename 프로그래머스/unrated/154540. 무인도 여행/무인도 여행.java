import java.util.*;

class Solution {
    
    int n, m;
    char[][] map;
    int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();        
        map = new char[n][m];
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                map[j][i] = maps[j].charAt(i);
            }
        }
        
        List<Integer> ansList = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                if (map[j][i] == 'X') continue;
                ansList.add(bfs(i, j));
            }
        }
        if (ansList.size() == 0) {
            return new int[]{-1};
        }
        Collections.sort(ansList);
        return toList(ansList);
    }
    
    int[] toList(List<Integer> list) {
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            ret[i] = list.get(i);
        }
        return ret;
    }
    
    int bfs(int x, int y) {
        int ret = map[y][x] - '0';
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        map[y][x] = 'X';
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int nx = now[0] + dir[i][0], ny = now[1] + dir[i][1];
                if (!inRange(nx, ny) || map[ny][nx] == 'X') continue;
                ret += map[ny][nx] - '0';
                map[ny][nx] = 'X';
                queue.offer(new int[]{nx, ny});
            }
        }
        return ret;
    }
    
    boolean inRange(int x, int y) {
        return (x>=0 && y>=0 && x<m && y<n);
    }
}