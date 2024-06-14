package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon1654 {

    private static int k;
    private static int n;
    private static int[] lan;
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        k = Integer.parseInt(input[0]);
        n = Integer.parseInt(input[1]);

        lan = new int[k];
        long right = 0;
        for(int i =0; i<k; i++){
            lan[i] = Integer.parseInt(br.readLine());
            if(right<lan[i]){
                right = lan[i];
            }
        }

        // main logic
        right++;
        long left = 0;
        long mid = 0;


        while(left<right){
            mid = (left+right)/2;
            long lanNum = lenNum(mid);

            if(lanNum < n){
                right = mid;
            }
            else{
                left = mid+1;
            }
        }

        // output
        System.out.println(left-1);


    }

    private static long lenNum(long mid) {
        long cnt = 0;
        for(int i =0; i<lan.length; i++){
            cnt += (lan[i]/mid);
        }
        return cnt;
    }
}
