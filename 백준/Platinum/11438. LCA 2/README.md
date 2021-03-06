# [Platinum V] LCA 2 - 11438 

[문제 링크](https://www.acmicpc.net/problem/11438) 

### 성능 요약

메모리: 99404 KB, 시간: 1076 ms

### 분류

자료 구조(data_structures), 최소 공통 조상(lca), 희소 배열(sparse_table), 트리(trees)

### 문제 설명

<p>N(2 ≤ N ≤ 100,000)개의 정점으로 이루어진 트리가 주어진다. 트리의 각 정점은 1번부터 N번까지 번호가 매겨져 있으며, 루트는 1번이다.</p>

<p>두 노드의 쌍 M(1 ≤ M ≤ 100,000)개가 주어졌을 때, 두 노드의 가장 가까운 공통 조상이 몇 번인지 출력한다.</p>

### 입력 

 <p>첫째 줄에 노드의 개수 N이 주어지고, 다음 N-1개 줄에는 트리 상에서 연결된 두 정점이 주어진다. 그 다음 줄에는 가장 가까운 공통 조상을 알고싶은 쌍의 개수 M이 주어지고, 다음 M개 줄에는 정점 쌍이 주어진다.</p>

### 출력 

 <p>M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력한다.</p>

### 문제 풀이
<p> 문제 설명에서 알 수 있듯 최소 공통 조상을 구하는 문제이다. 하지만, 단순한 최소 공통 조상을 구하는 문제와 달리 희소 테이블을 활용해야 제 시간 안에 풀 수 있는 문제였다.</p>
<p> 희소 테이블에 대해 간략한 설명을 덧붙이자면, 유향 그래프 상에서 k번 이동을 해야 한다고 가정했을 때, k번을 그대로 따라가서 원하는 node에 도달하는 방법이 일반적이겠지만 이 경우는 k가 매우 크다면 시간복잡도가 굉장히 커지게 된다. (O(n)의 시간 복잡도를 가지며, 선형으로 비례하게 된다.)</p>
<p> 이러한 문제를 해결하기 위해 희소 테이블이라는 테크닉을 사용하게 되는데, 그 방법이라 함은 2라는 숫자를 적극 활용하는 것이다. 대표적인 예시로, 이분 탐색이 주어진 범위를 계속 2로 나누어 가면서 원하는 숫자에 다다르듯이, 이 희소 테이블의 경우에는 정점으로부터 2^p의 거리에 어떤 정점이 있는가를 배열로써 기록해놓고(p = 0,1,2,3,...) k라는 거리와 기준점이 입력으로 들어왔을 때 O(logn)의 시간복잡도로 원하는 node에 도달할 수 있다. </p>
<p> 이 문제에서는 u, v라는 node가 주어지고 이 두 node의 최소 공통 조상을 구하는 방법으로 전형적인 최소 공통 조상을 구하는 알고리즘을 사용한다. depth라는 배열을 dfs를 통해 미리 채워놓고 희소 테이블 또한 채워놓는다. 그리고 depth[u]와 depth[v]의 차이를 구하고 더 깊은 node에서 root 쪽으로 그 difference만큼 이동한다. 그 후에 u와 v의 수를 비교하고 다르다면 root 쪽으로 이동시키면서 parent가 같아질 때까지 loop를 돌린다. (이 때 주의할 점은 위에서 설명했던 p의 값이 큰 쪽에서부터 작은 쪽으로 루프를 돌려야 한다는 점이다.)</p>
<p> 마지막으로 parent가 같아진 두 점을 구했다면 그 한 단계 위의 parent를 return하고 출력한다. </p>
