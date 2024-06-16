package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon20444 {
    private static long n;
    private static long k;
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Long.parseLong(input[0]);
        k = Long.parseLong(input[1]);

        // main logic
        long start = 0;
        long end = n/2;
        while(start <= end){
            long mid = (start+end)/2;
            long col = n - mid;

            long total = (mid + 1) * (col + 1);

            if(total == k){
                System.out.println("YES");
                return;
            }
            else if ( total < k){
                start = mid + 1;
            }else{
                end = mid - 1;
            }

        }

        System.out.println("NO");


    }
}
