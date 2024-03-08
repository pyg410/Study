- 현재 index를 value로 하고 해당 보수값을 key로 하는 Map을 선언한다.
- 반복문을 돌며 Map에 현재 수(nums[i])의 보수가 있는지 확인한다.
    
    1. 있는 경우, 현재수를 보수로 갖던 수의 index와 현재 index를 int배열을 생성하여 반환한다.
    2. 없는 경우, 현재 수의 index를 value로 하고 현재 수의 보수값을 key로 하는 데이터를 삽입한다.

출처: [https://the-dev.tistory.com/48](https://the-dev.tistory.com/48) [DevLogs:티스토리]

```
package TwoSum;  
  
import java.util.Arrays;  
import java.util.HashMap;  
import java.util.Map;  
  
public class TwoSum {  
    public int[] twoSum(int[] nums, int target){  
        Map<Integer, Integer> map = new HashMap<>();  
  
        for(int i=0; i< nums.length; i++){  
            if(map.containsKey(nums[i])){  
                return new int[]{map.get(nums[i]), i};  
            }  
            map.put(target - nums[i],i);  
        }  
  
        return new int[]{};  
    }  
  
    public static void main(String[] args) {  
        int[] nums = {1, 4, 7, 8};  
        int target = 11;  
  
        TwoSum t = new TwoSum();  
        System.out.println(Arrays.toString(t.twoSum(nums, target)));  
    }  
}
```