package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BaekJoon2212 {
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        String[] st = br.readLine().split(" ");
        for(int i =0; i<n; i++){
            arr[i] = Integer.parseInt(st[i]); // 센서
        }

        // main logic
        int result = 0;
        Arrays.sort(arr);

        Integer[] diff = new Integer[n-1]; // 센서간 차이
        for(int i =1; i<n; i++){
            diff[i-1] = arr[i] - arr[i-1];
            result += diff[i-1];
        }

        Arrays.sort(diff, Collections.reverseOrder());

        for(int i=0; i<k-1; i++){
            if(diff.length > i){
                result -= diff[i];
            }
        }

        // output
        System.out.println(result);

    }
}
