# Solution

### 문제 접근
- 어느 위치 인덱스를 기준으로 각 집까지의 거리가 최소가 되는 포인트를 구하는 문제이다.
- 각 집까지의 거리가 최소이다 = 위치 인덱스를 오른쪽으로 옮긴다고 가정할 때 오른쪽에 존재하는 집의 수보다 왼쪽에 존재하는 집의 수가 크다.
- 위의 아이디어를 바탕으로 코드를 작성할 수 있다.

### 문제 풀이
```java
static int findPos(int maxPos) {
    int pos = 1;
    int beforeNum = 0, afterNum = n;
    while (pos <= maxPos) {
        beforeNum += houseNumPerIdx[pos];
        afterNum -= houseNumPerIdx[pos];
        if (afterNum - beforeNum <= 0) {
            break;
        }
        pos++;
    }
    return pos;
}
```
- findPos 함수는 오른쪽에 존재하는 집의 수보다 왼쪽에 존재하는 집의 수가 많거나 같다면 곧바로 그 시점의 위치 인덱스를 반환한다.
- afterNum은 오른쪽에 존재하는 집의 수, beforeNum은 왼쪽에 존재하는 집의 수이고, 위치 인덱스를 옮길 때마다 값을 업데이트 해준다.
