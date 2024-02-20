### 특징
- 해시함수를 사용하여 변환한 값을 인덱스로 삼아 Key와 Value를 저장하는 자료구조이다.
- 데이터의 크기에 관계없이 삽입 및 검색에 매우 효율적이다.

### 시간복잡도
- 평균 시간 복잡도는 O(1)이다.
- 충돌이 일어나는 경우 최악의 경우 O(n)이다.

### 사용 예시
- 데이터베이스 인덱스 구현
- 사용자 로그인 인증
- set 데이터 구조 구현

### 주의사항
- 충돌이 자주 일어날 수 있다. 이를 개선하기 위해 chaining, open, addressing 등의 방법이 사용된다.

### 적재율
- 해시 테이블 크기 대비, 키의 개수를 말한다.
- 키의 개수 = K
- 테이블 크기 = N
- 적재율 = K/N
- Direct Address Table의 경우 키 값을 인덱스로 사용하는 구조이기 때문에 적재율이 1 이하이며, 1 초과인 경우 충돌이 반드시 발생한다.

### 해시 충돌
왜 충돌이 발생하면 안되는 걸까?
- 충돌이 발생하지 않는경우 Search, Insert, Delete 연산 모두 O(1)에 수행된다.
- 충돌이 발생할 경우 Search, Delete 연산이 O(K)만큼 걸리게 된다.

## 충돌 개선 1. 해시 테이블 구조 개선
### Chaining
A1 -> 152 : B1
A2 -> 152 : B2
와 같이 A1, A2의 key값에 152 Index로 충돌하는 경우, B1의 뒤에 B2를 연결해줌으로써 충돌을 처리한다.

결과
1. A2 -> 152 : B1 -> B2
2. A1 -> 152 : B1

이렇게 해결하는 경우 Insert는 O(1)이 걸리지만, Search, Delete는 최악의 경우 O(K)가 걸리게 된다.


### Open Addressing
동일한 Index에 다른 데이터가 있는 경우 다른 주소를 사용하는 방법이다.
152 Index에 이미 데이터가 있는 경우 153에 Data를 저장한다.
- 삽입: 계산한 해시 값에 대한 인덱스가 이미 차있는 경우 다음 인덱스로 이동하면서 비어있는 곳에 저장한다. 이렇게 비어있는 자리를 탐색하는 것을 **탐사(Probing)** 라고 한다.
- 탐색: 계산한 해시 값에 대한 인덱스부터 검사하며 탐사를 해나가는데 이 때 “삭제” 표시가 있는 부분은 지나간다.
- 삭제: 탐색을 통해 해당 값을 찾고 삭제한 뒤 “삭제” 표시를 한다.

이러한 open addressing 방식은 3가지 방법을 통해서 해시 충돌을 처리한다.

## Open Addressing의 3가지 충돌 처리기법

### 선형탐사(Linear Probing)

![](https://baeharam.netlify.app/media/ds/hash6.gif)

[그림 출처](https://courses.cs.washington.edu/courses/cse326/00wi/handouts/lecture16/sld015.htm)

선형탐사는 가장 기본적인 충돌해결기법으로 위에서 설명한 기본적인 동작방식이다. 선형탐사는 바로 인접한 인덱스에 데이터를 삽입해가기 때문에 데이터가 밀집되는 클러스터링(Clustering) 문제가 발생하고 이로인해 탐색과 삭제가 느려지게 된다.

### 제곱탐사(Quadratic Probing)

![](https://baeharam.netlify.app/media/ds/hash7.png)

[그림 출처](https://stackoverflow.com/questions/27742285/what-is-primary-and-secondary-clustering-in-hash)

제곱탐사는 말 그대로 12,22,32..12,22,32.. 으로 탐사를 하는 방식으로 선형탐사에 비해 더 폭넓게 탐사하기 때문에 탐색과 삭제에 효율적일 수 있다. 하지만 이는 초기 해시값이 같을 경우에 탐사하는 역시나 클러스터링 문제가 발생하게 된다.

### 이중해싱(Double Hashing)

이중해싱은 선형탐사와 제곱탐사에서 발생하는 클러스터링 문제를 모두 피하기 위해 도입된 것이다. 처음 해시함수로는 해시값을 찾기 위해 사용하고 두번째 해시함수는 충돌이 발생했을 때 탐사폭을 계산하기 위해 사용되는 방식이다.

### 비교

![](https://baeharam.netlify.app/media/ds/graph.png)

[그림 출처](http://www.cs.uml.edu/~tom/404/notes/Hashing.pdf)

위에서 배운 충돌해결기법들을 비교해보면 적재율인 �α 에 따라서 위와 같이 나오는데, 여기서 successful search는 찾고자 하는 데이터가 해시테이블에 있는 경우이고 unsuccessful search는 없는 경우이다.



## 충돌해결 2 : 해시 함수 개선

### 나눗셈법(Division Method)

아주 간단하게 해시값을 구하는 방법으로 미리 해시 테이블의 크기인 N 을 아는 경우에 사용할 수 있다. 해시함수를 적용하고자 하는 값을 N 으로 나눈 나머지를 해시값으로 사용하는 방법이다. 즉 다음과 같다.

h(k)=k mod N

여기서 N 은 2의 제곱꼴을 사용하면 안된다고 하는데 이는 그 제곱꼴이 2^p 로 나타날 때 k 의 하위 p 개의 비트를 고려하지 않는다고 한다. 따라서 N 은 소수(Prime Number)를 사용하는 것이 좋다.

### 곱셈법(Multiplication Method)

0<A<1 인 A 에 대해서 다음과 같이 구할 수 있다.

h(k) = ⌊N(kA mod 1)⌋

kA mod 1 의 의미는 kA 의 소수점 이하 부분을 말하며 이를 N 에 곱하므로 0부터 N 사이의 값이 된다. 이 방법의 장점은 N 이 어떤 값이더라도 잘 동작한다는 것이며 A 를 잘 잡는 것이 중요하다.

이외에도 다양한 해시 함수가 있다는 것만 알아두도록 하자.

### 출처
[해시 테이블](https://baeharam.netlify.app/posts/data%20structure/hash-table) 
