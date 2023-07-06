# Solution

### 문제 접근
- 해당 문제는 유향 그래프에서 사이클들을 찾고, 사이클에 해당되는 학생들을 뺀 나머지 학생 수를 구하면 된다.
- 사이클을 찾고 사이클에 해당되는 학생 수를 구하는 것이 핵심인데, 이를 깊이 우선 탐색으로 해결한다.
  - visited 배열과 banned 배열을 생성한다.
  - visited 배열 : 임의의 node에서 dfs를 하는 도중에 사이클이 존재한다면 방문했던 node를 다시 방문할 수 있는데, 이 때 사이클 여부를 알기 위한 배열이다.
  - banned 배열 : 해당 문제는 모든 정점이 이어져 있는 것이 아니기에, 각 cluster가 존재하게 된다.
  - 1번 cluster의 dfs를 마치고 2번 cluster의 dfs를 진행할 때 visited 배열로만 cycle을 구분하게 되면 앞의 1번 cluster와 혼재되므로 1번 cluster가 방문했던 정점을 banned로 따로 관리해서 구분한다.
 
### 문제 풀이
- 만일 dfs 중에 다음 노드가 방문했던 곳일 경우, 즉 cycle을 이루었을 경우 그 즉시 cycle을 탐색하면서 그 수를 파악해야 한다.
```java
if (!banned[nextIdx]) {
  cnt++;
  for (int i = nextIdx; i != idx; i = graph[i]) {
      cnt++;
  }
}
```
- 위의 코드는 visited[nextIdx] 가 성립할 경우 진입하는 블록이다.
  - nextIdx부터 현재 idx까지 왔던 길을 쭉 훑으면서 수(cnt)를 세게 된다. 물론 반복문 전에 현재 idx 몫을 더했으므로 idx가 아닐 경우를 조건으로 한다.
  - !banned[nextIdx]의 의미는 이전에 진행했던 dfs가 방문했던 곳을 고려하는 것이 아니기 때문이다.
 
- 이렇게 총 숫자(cnt)를 구하고 전제 학생 수에서 빼주면 구하고자 하는 값이 나온다.
