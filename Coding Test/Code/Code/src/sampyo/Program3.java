package sampyo;

import java.util.*;

public class Program3 {


    public int[] solution(int[] a, int[] b){
        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> aCompareMap = new HashMap<>();
        // a배열의 원소를 key로 해시맵에 다 저장함.
        for(int aComp : a){
            if(aCompareMap.containsKey(aComp)){
                aCompareMap.put(aComp, aCompareMap.get(aComp)+1);
            }else{
                aCompareMap.put(aComp, 1);
            }
        }

        for (int bComp : b){
            // b배열의 원소를 해시맵에서 key값으로 있는지 판단.
            if(aCompareMap.containsKey(bComp)){
                // 찾았으면, 원소를 출력 배열에 저장
                result.add(bComp);
                // 해당 Key에 대한 value -1
                aCompareMap.put(bComp, aCompareMap.get(bComp)-1);
                // -1했을 때 0이되면, key 삭제.
                if(aCompareMap.get(bComp)<1){
                    aCompareMap.remove(bComp);
                }
            }
        }

        int[] resultArr = result.stream().mapToInt(i -> i).toArray();
        Arrays.sort(resultArr);
        return resultArr;

    }

    public static void main(String[] args) {
        Program3 pg = new Program3();
        int[] a = {1,2,2,3,3,3};
        int[] b = {2,3,3,4,5};
        int[] result = pg.solution(a, b);

        System.out.println(Arrays.toString(result));



    }
}


