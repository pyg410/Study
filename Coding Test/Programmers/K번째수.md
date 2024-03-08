### 문제
array의 i번째 수부터 j번째 숫자까지 자르고 정렬했을 때, k번째 수를 구하는 문제.

\[1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3 라면,

2번째 부터, 5번째까지 자르면 \[5,2,6,3]이다.
여기서 알 수 있는것은 index가 1부터 시작한다는 것.

k=3이므로, 자른 배열의 3번째 index에 있는 5를 출력한다.

매개변수 : 배열 array, \[i,j,k] 배열을 원소로 가진 2차원 배열 commands
출력 : 연산 적용 후 나온 결과를 1차원배열에 담아 return

### 제한 사항
 array의 길이 : 1이상 100 이하
 array의 원소 범위 : 1 이상 100 이하
 commands의 길이 : 1 이상 50 이하
 commands의 원소 길이 : 3 고정


### 풀이
1차원적으로 1회 돈다고 생각했을 때,

1. commands의 \[0]\[0] 인덱스에 있는 i와 \[0]\[1]에 있는 j를 뽑아 낸다.
2. array를 받아 split함수를 통해 i~j까지 잘라낸다.
3. 새로만들어진 배열을 정렬한다.
4. 새로 만들어진 배열의 k번째 index 원소를 뽑아서 ret 배열에 저장한다.
5. commands.length만큼 반복

### 코드
```
import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
    
        for(int idx=0; idx<commands.length; idx++){
            int i = commands[idx][0]-1;
            int j = commands[idx][1]-1;
            int k = commands[idx][2]-1;

            int[] splitArr = new int[j-i+1];

            for(int cnt=0; cnt<=(j-i); cnt++){
                splitArr[cnt]= array[i+cnt];
            }
            
            Arrays.sort(splitArr);

            answer[idx] = splitArr[k];    
        }
        
        return answer;
    }
}
```

조금 더 최적화 하자면, int i,j,k가 매번 새롭게 초기화되고 있다.
splitArr또한 마찬가지다.
이걸 바깥으로 빼준다.
```
import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int i=0;
        int j=0;
        int k=0;
        int[] splitArr;
    
        for(int idx=0; idx<commands.length; idx++){
            i = commands[idx][0]-1;
            j = commands[idx][1]-1;
            k = commands[idx][2]-1;

            splitArr = new int[j-i+1];

            for(int cnt=0; cnt<=(j-i); cnt++){
                splitArr[cnt]= array[i+cnt];
            }
            
            Arrays.sort(splitArr);

            answer[idx] = splitArr[k];    
        }
        
        return answer;
    }
}
```

여기서 split해주는 부분을 util패키지에 있는 메서드로 대체하면 코드 수를 줄일 수 있다.
Arrays.copyOfRange 메서드를 사용하면, 코드를 더 깔끔하게 변경할 수 있다.
```
import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int[] temp;

        for(int i=0; i<commands.length; i++){
            temp = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
            Arrays.sort(temp);
            answer[i] = temp[commands[i][2]-1];
        }

        return answer;
    }
}
```
