package TwoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target){
        // HashMap 생성
        Map<Integer, Integer> map = new HashMap<>();

        // 배열 길이만큼 반복
        for(int i=0; i< nums.length; i++){
            // Key가 포함되어있다면? -> 해시맵에 num[i]에 대한 target의 보수가 포함되어있다면?
            if(map.containsKey(nums[i])){
                // 해시맵에 있는 value(인덱스)와 주어진 배열의 인덱스를 반환
                return new int[]{map.get(nums[i]), i};
            }
            // 해시맵에 num[i]에 대한 target의 보수를 Key로 index를 value로 저장
            map.put(target - nums[i],i);
        }

        return new int[]{};
    }

    /**
     * 1, 4, 7, 8 / 11이 매개변수로 들어간다.
     * 해시맵이 생성된다.
     * for문에 진입한다.
     * 해시맵에 1이 키로 존재하는가?
     * 존재하지 않으므로 스킵
     * 해시맵에 10(11-1), 0 을 key,value로 넣는다.
     * 해시맵에 4가 키로 존재하는가?
     * 존재하지 않으므로 스킵
     * 맵에 7(11-4), 1을 key,value로 넣는다.
     * 해시맵에 7이 키로 존재하는가?
     * 존재하므로, return int형 배열에 해시맵에서 얻은 index 1과 해당 인덱스인 2를 넣어 반환
     * 종료
     */
    public static void main(String[] args) {
        int[] nums = {1, 4, 7, 8};
        int target = 11;

        TwoSum t = new TwoSum();
        System.out.println(Arrays.toString(t.twoSum(nums, target)));
    }
}

