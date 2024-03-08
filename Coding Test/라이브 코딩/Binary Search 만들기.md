### 문제
list안의 특정 원소를 찾기 위해 binary search를 직접 구현하는 내용
https://velog.io/@byeble23/Java-Binary-Search%EC%9D%B4%EC%A7%84%ED%83%90%EC%83%89-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%98%88%EC%A0%9C

### 예상 진행
list안의 원소는 어떤 타입인가요?
정수형 원소 입니다.
정해지지 않았습니다.

확장성을 고려하면 될까요?
네 고려하시면 됩니다.

제 생각에는 확장성을 고려한다면, 제네릭을 사용해야할 것 같습니다.
숫자만 취급한다면, Number를 상속한 타입을 받아야 할 것 같고,
문자열까지 취급한다면, Comparable를 상속한 타입을 받아야할 것 같습니다.

일단, 문자열까지 취급한다는 가정하에, Comparable타입을 상속받아서 구현하면 될 것 같습니다.

```
public static <T extends Comparable<T>> int binarySearch(T[] array, T target){
	int left = 0;
	int right = array.length;

	while(left<=right){
		int mid = left + (right-left)/2
		int cmp = target.compareTo(array[mid]);

		if(cmp = 0){
			return mid;
		} else if(cmp < 0){  
		    right = mid - 1;  
		} else{  
		    left = mid + 1;  
		}
	}
	return -1;
}

public static void main(String[] args) throws IOException {  
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
    System.out.print("찾을 숫자를 입력해주세요 : ");  
    StringTokenizer st = new StringTokenizer(br.readLine());  
    String[] sortedArray = {"가", "나", "다", "라"};  
  
    String target = st.nextToken();  
  
    int resultIndex = binarySearch(sortedArray, target);  
  
    if(resultIndex != -1){  
        System.out.println("타겟 숫자 " + target + "은 인덱스 " + resultIndex + "에 있습니다.");  
    }else {  
        System.out.println("타겟 숫자 " + target + "은 배열에 존재하지 않습니다.");  
    }  
}
```