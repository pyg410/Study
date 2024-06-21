package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon13397 {

    private static int n, m;
    private static int[] arr;
    private static int result;
    public static void main(String[] args) throws IOException {

        // input
        input();

        // main logic
        solution();

        // output
        System.out.println(result);
    }

    private static void solution() {

        int left = 0;
        int right = Arrays.stream(arr).max().getAsInt();

        // upper bound
        while(left < right){

//            System.out.println("left : " + left  + ", right : " + right);

            int mid = (left+right) /2 ; // 임시로 구한 "구간내 최솟값과 최댓값의 차이의 최댓값"

            if(findSectionNum(mid) <= m){
                // mid가 커질 수록 구간 수가 감소한다. 즉, mid를 줄여야 한다.
                right = mid;
            }else{
                // 만약 findSection
                left = mid + 1;

            }
        }

        result = left;


    }

    private static int findSectionNum(int mid) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int sectionNum = 1;
        for(int i =0; i<arr.length; i++){
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if(Math.abs(max-min) > mid ){
                sectionNum ++;
                min = arr[i];
                max = arr[i];
            }
        }

//        System.out.println(" section Num = " + sectionNum);

        return sectionNum;

    }

    private static void input() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    }
}
