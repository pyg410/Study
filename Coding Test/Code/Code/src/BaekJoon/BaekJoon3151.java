package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon3151 {
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        // main logic
        long cnt = 0;
        for(int fixedNum =0; fixedNum<n-2; fixedNum++){
            if(arr[fixedNum]>0) break;
            int left= fixedNum+1;
            int right = n-1;

            while(left<right){
                int sum = arr[fixedNum] + arr[left] + arr[right];
                int rightCnt = 1;
                int leftCnt = 1;
                if(sum==0){
                    if(arr[left] == arr[right]){
                        cnt += (long) (right - left + 1) *(right-left)/2;
                        break;
                    }
                    while(left+1 < right && arr[left] == arr[left+1]){
                        leftCnt++;
                        left++;
                    }
                    while(left<right-1 && arr[right] == arr[right-1]){
                        rightCnt++;
                        right--;
                    }
                    cnt += (long) rightCnt * leftCnt;
                }

                if(sum > 0)right--;
                else left++;
            }
        }

        // output
        System.out.println(cnt);

    }
}
