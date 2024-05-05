package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BaekJoon22862 {
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);


        //Boolean[] arr = Arrays.stream(br.readLine().split(" ")).map(s -> Integer.parseInt(s)%2==0).toList().toArray(new Boolean[0]);
        Boolean[] arr = Arrays.stream(br.readLine().split(" ")).map(s -> Integer.parseInt(s)%2==0).collect(Collectors.toList()).toArray(new Boolean[0]);


        // main logic
        int left =0;
        int right = 0;
        int cnt = 0;
        int length = 0;
        while(right < n){
            if(cnt < k){
                if(!arr[right]){
                    cnt++;
                }
                right++;
                length = Math.max(right-left-cnt, length);
            } else if (arr[right]) {
                right++;
                length = Math.max(right-left-cnt, length);
            }else{
                if(!arr[left]){
                    cnt--;
                }
                left++;
            }
        }

        // output
        System.out.println(length);
    }
}
