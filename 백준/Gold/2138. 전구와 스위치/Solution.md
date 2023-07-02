# Solution
### 접근
- 해당 문제에서 알아두어야 할 핵심이 존재한다.
  1. 각 스위치마다 누르거나 누르지 않는 동작이 존재하며, 최소 횟수가 되기 위해서는 1번을 넘어갈 수 없다.
  2. i번째 스위치를 동작시킬 때 i-1번째 전구의 상태는 고정된다. (그 다음으로 넘어가고부터는 i-1번째 전구는 건드릴 수 없다.)
  3. 2 ~ n-1 번째 스위치와는 달리 첫 번째 스위치는 i-1번째 전구가 없으므로 누르거나 누르지 않는 동작을 결정할 수 없다. 따라서, 처음 시작할 때 on 상태와 off 상태, 두 가지 케이스를 모두 고려하여 알고리즘을 짜야 한다.
 
- 위의 핵심을 잘 활용하여 코드를 짤 수 있다.

### 흐름

```java
// 33~38번째 줄
if (bulbState[i-1] != finalArr[i-1]) {
    ret++;
    bulbState[i+1] = pushSwitch(bulbState[i+1]);
    bulbState[i] = pushSwitch(bulbState[i]);
    bulbState[i-1] = pushSwitch(bulbState[i-1]);
}
```
- pushSwitch는 0을 1로, 1을 0으로 바꾸어주는 함수이다. (스위치 동작 그대로)
- ret은 solve 함수의 return 값으로써, 몇 번 스위치를 눌렀는지 반환해주는 값이다.
- 위의 코드는, 2 ~ n-1번째 스위치를 순회하며 i-1번째 전구 상태와 최종 결과의 i-1번째 전구 상태를 비교하여 다르면 스위치 누른 횟수를 1 증가시키고 인접한 전구의 스위치 모두를 눌러주는 코드이다.

```java
int[] bulbStateOnStart = new int[n], bulbStateOffStart = new int[n];
for (int i = 0; i < 2; ++i) {
    bulbStateOffStart[i] = initS.charAt(i) - '0';
    bulbStateOnStart[i] = initS.charAt(i) == '0' ? 1 : 0;
}
for (int i = 2; i < n; ++i) {
    bulbStateOffStart[i] = initS.charAt(i) - '0';
    bulbStateOnStart[i] = initS.charAt(i) - '0';
}
```

- 위 핵심 3번에서 언급했던 것처럼, on 상태와 off 상태의 배열을 따로 만들어준 것을 확인할 수 있다.
- 마지막으로, n번째 스위치에 도달해서는 n번째 전구와 n-1번째 전구에 한해 현 상태와 최종 상태를 비교하면서 둘 다 바꾸어주어야 하는 경우 ret을 1 증가시키고, 둘 다 안바꿔줘도 되는 경우는 그대로,
__그 외의 경우는 예외처리로써 -1을 출력__ 해야 한다.
