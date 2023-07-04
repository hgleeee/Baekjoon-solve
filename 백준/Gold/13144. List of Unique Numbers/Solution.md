# Solution

### 접근
- 또한 같은 수가 여러 번 등장하는 수열을 incorSeq, 그렇지 않은 수열을 corSeq라고 하겠다.
- 가장 큰 전제는 incorSeq를 포함하는 수열은 그 또한 incorSeq라는 것이다. 반대로 corSeq의 부분수열은 모두 corSeq이다.
- 따라서, 전체 수열의 corSeq에 해당하는 부분 수열 중 가장 길이가 긴 수열들을 뽑아 각 부분 수열의 모든 경우의 수를 구해주면 된다.
  - 여기서 주의해야 하는 점은 전체 수열의 부분 수열 중 겹치는 부분이 존재하므로, 그 부분에 대해서 따로 처리가 필요하다.
 
### 풀이
#### 예시 : 1 2 3 1 2 수열
- 위에서 말한 corSeq에 해당하는 가장 길이가 긴 부분수열은 1 2 3 // 2 3 1 // 3 1 2 에 해당한다.
  - 여기서, 겹치는 부분을 볼 수 있는데 2 3 과 3 1이 겹치는 것을 확인할 수 있다.
- 해당 부분 수열을 코드로 어떻게 확인할 수 있을까.

```java
int startIdx = 0;
int[] idxArr = new int[n+1];    // 수열에 포함된 숫자의 index 배열
Arrays.fill(idxArr, -1);        // 배열을 -1로 초기화
for (int i = 0; i < list.size(); ++i) {
    int num = list.get(i);
    if (idxArr[num] != -1 && idxArr[num] >= startIdx) {
        ret += sum[i - startIdx - 1];
        if (i >= idxArr[num] + 2) {
            ret -= sum[i - idxArr[num] - 2];
        }
        startIdx = idxArr[num]+1;
    }
    idxArr[num] = i;
}
```
- startIdx 변수는 corSeq 부분수열의 시작 index를 가리키는 변수이다.
- 수열의 1부터 마지막 2까지 순회하면서 index 배열에 숫자에 따른 index를 기록하고 만일 배열에 저장된 index가 초기값 (-1)이 아니고 startIdx보다 크다면 그것이 corSeq 부분수열이 끝나는 지점이 된다.
  - 22번째 줄이 그에 맞춰 ret(결과값)에 더해주는 코드이다.
- 23~25번째 줄이 겹치는 부분을 빼주는 코드이다.

