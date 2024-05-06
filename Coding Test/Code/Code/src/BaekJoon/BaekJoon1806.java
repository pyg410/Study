package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon1806 {
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int s = Integer.parseInt(input[1]);

        int[] arr = new int[n+1];
        input = br.readLine().split(" ");
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(input[i]);
        }

        // main logic

        int left = 0;
        int right = 0;
        int sum=0;
        int result = Integer.MAX_VALUE;

        while(left <= right && right <= n) {
            if(sum < s) {
                sum += arr[right++];
            } else if(sum >= s) {
                result = Math.min(result, right-left);
                sum -= arr[left++];
            }
        }


        // output
        if(result == Integer.MAX_VALUE){
            System.out.println(0);
        }else{
            System.out.println(result);
        }



    }
}
