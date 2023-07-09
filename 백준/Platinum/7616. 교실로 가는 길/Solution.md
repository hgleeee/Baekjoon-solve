# Solution
### 문제 접근
- 학생들이 위치하는 1번 node와 교실이 위치하는 2번 node를 포함해 총 n개의 정점이 서로 연결된 그래프가 주어진다.
- 이 때, 학생들이 서로 절대 마주치지 않는 경로가 k개 존재하면 그 경로 k개를 모두 출력하고 존재하지 않는다면 "Impossible"을 출력한다.
- 서로 만나지 않는 경로를 구하기 위해서는 각 node가 연결된 간선의 capacity를 1로 초기화하고 이 때 1부터 2까지의 최대 유량을 구하면 된다.
- 하지만 단순히 최대 유량만 구해서는 정점에서 만나는 경우를 제외하지 않은 결과가 도출된다.
  - 따라서, 각 정점의 분리를 통해 그 분리된 정점들 또한 1의 capacity를 가진 간선으로 연결시키면 된다.
 
- 경로는 1부터 2까지 경로를 탐색하면서 flow 배열에서 하나씩 제거하면서 도출할 수 있다.
 
### 문제 풀이
```java
static void setCapacity() {
    for (int i = 1; i <= n; ++i) {
        capacity[i*2][i*2+1] = 1;
        for (int next : graph[i]) {
            capacity[i*2+1][next*2] = 1;
        }
    }
}
```
- capacity를 처음 초기화하는 코드이다.
- 정점 분리를 시키기 위해 본래 i 정점을 i*2, i*2+1로 나누었고, 간선 또한 모두 1의 capacity를 가진다.
```java
while (!queue.isEmpty() && parent[sink] == 0) {
    int now = queue.poll();
    for (int next = 1; next <= 2*n+1; ++next) {
        if (parent[next] == 0 && capacity[now][next] - flow[now][next] > 0) {
            queue.offer(next);
            parent[next] = now;
        }
    }
}
if (parent[sink] == 0) break; // 증가 경로 X
for (int i = sink; i != src; i = parent[i]) {
    flow[parent[i]][i]++;
    flow[i][parent[i]]--;
}
```
- 에드몬드-카프로 증가 경로를 구하는 방법이다.
- 전형적인 최대 유량을 구하는 코드이다.
```java
static void searchRoute(int idx, int now, int sink, List<Integer> route) {
    if (now == sink) {
        if (cnt > 0) {
            for (int i = 0; i < route.size(); ++i) {
                sb.append(route.get(i) + " ");
            }
            sb.append("\n");
            cnt--;
        }
        return;
    }
    if (now % 2 == 0) {
        if (flow[now][now+1] > 0) {
            flow[now][now+1]--;
            searchRoute(idx, now + 1, sink, route);
        }
        return;
    }

    for (int next : graph[now/2]) {
        if (flow[now][next*2] > 0) {
            route.add(next);
            flow[now][next*2]--;
            searchRoute(idx+1, next*2, sink, route);
            route.remove(route.size()-1);
        }
    }
}
```
- dfs 방식으로 경로를 구했다.
- 중간에 now % 2 == 0 조건문의 의미는 정점 분리된 두 정점 사이 경로를 처리해주기 위한 것이다.
- route 리스트에 추가해가면서 sink에 도달했을 경우 route 리스트가 가리키는 경로를 sb 객체에 넣어주었다.
