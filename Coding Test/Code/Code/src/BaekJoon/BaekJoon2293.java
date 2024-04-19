package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon2293 {

    static int[] dp;
    static int result;
    static int n;
    static int k;

    public static void main(String[] args) throws IOException {

        // input

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        dp = new int[k+1];


        dp[0] = 1;
        for(int i=0; i<n; i++){
            int coin = Integer.parseInt(br.readLine());

            for(int j = 1; j<=k; j++){
                if(j-coin >=0)
                    dp[j] += dp[j-coin];
            }
        }

        // output
        System.out.println(dp[k]);


    }
}
