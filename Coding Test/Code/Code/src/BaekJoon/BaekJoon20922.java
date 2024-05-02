package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon20922 {
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] cnt = new int[100001];

        // main logic
        int result = Integer.MIN_VALUE;
        int left =0;
        int right = 0;
        while(left <= right){
            if(right<=n-1 && cnt[arr[right]] <k){
                cnt[arr[right]]++;
                right++;
            }else if(cnt[arr[right]] == k){
                cnt[arr[left]]--;
                left++;
            }
            result = Math.max(result, right-left);

            if(right ==n){
                // output
                System.out.println(result);
                return;
            }
        }
    }
}
