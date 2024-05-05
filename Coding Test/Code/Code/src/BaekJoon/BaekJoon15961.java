package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon15961 {
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = input[0]; // 접시 수
        int d = input[1]; // 초밥 가짓 수
        int k = input[2]; // 연속해서 먹는 접시 수
        int c = input[3]; // 쿠폰이 적용되는 초밥의 번호

        int[] susie = new int[n];
        for(int i =0; i<n; i++){
            susie[i] = Integer.parseInt(br.readLine());
        }

        // main logic

        int left = 0;
        int right = left+k-1;
        int maxLength = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(c, 1); // 쿠폰 저장
        for(int i =0; i<n; i++){

            if(i==0){
                for(int j=0; j<k; j++){
                    int index = left+j;
                    if(index >= susie.length){
                        index -= susie.length;
                    }
                    if(map.get(susie[index])==null) map.put(susie[index], 1);
                    else map.put(susie[index], map.get(susie[index])+1);
                }
            }else{
                if(susie[left-1]!=c){
                    if(map.get(susie[left-1])<=1){
                        map.remove(susie[left-1]);
                    }else{
                        if(map.get(susie[left-1])==null) map.put(susie[left-1], 1);
                        else map.put(susie[left-1], map.get(susie[left-1])-1);
                    }
                }
                if(right >= susie.length){
                    right -= susie.length;
                }
                if(map.get(susie[right])==null) map.put(susie[right], 1);
                else map.put(susie[right], map.get(susie[right])+1);
            }

            left++;
            right++;
            maxLength = Math.max(maxLength, map.size());

        }

        // output
        System.out.println(maxLength);

    }
}
