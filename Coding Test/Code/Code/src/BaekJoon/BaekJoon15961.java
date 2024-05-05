package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon15961 {

    private static Map<Integer, Integer> map;
    private static int[] susie;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = input[0]; // 접시 수
        int d = input[1]; // 초밥 가짓 수
        int k = input[2]; // 연속해서 먹는 접시 수
        int c = input[3]; // 쿠폰이 적용되는 초밥의 번호

        susie = new int[n];
        for(int i =0; i<n; i++){
            susie[i] = Integer.parseInt(br.readLine());
        }

        // main logic

        int left = 0;
        int right = left+k-1;
        int maxLength = 0;

        map = new HashMap<>();
        map.put(c, 1); // 쿠폰 저장
        for(int i =0; i<n; i++){

            if(i==0){ // 첫번째 반복
                for(int j=0; j<k; j++){
                    int index = left+j;
                    index = checkLength(index);
                    put(index, 1);
                }
            }else{ // 이외 반복
                leftRemove(left-1, c);
                right = checkLength(right);
                put(right, 1);
            }

            left++;
            right++;
            maxLength = Math.max(maxLength, map.size());

        }

        // output
        System.out.println(maxLength);

    }

    private static void put(int index, int operationNum){
        if(map.get(susie[index])==null) map.put(susie[index], 1);
        else map.put(susie[index], map.get(susie[index])+operationNum);
    }

    private static int checkLength(int num){
        if(num >= susie.length){
            num -= susie.length;
        }
        return num;
    }

    private static void leftRemove(int index, int c){
        if(susie[index]!=c){
            if(map.get(susie[index])<=1){
                map.remove(susie[index]);
            }else{
                put(index, -1);
            }
        }
    }
}
