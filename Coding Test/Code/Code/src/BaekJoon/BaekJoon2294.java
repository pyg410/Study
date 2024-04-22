package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon2294 {
    static int[] dp;
    static int n, k;
    static int[] coins;
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        coins = new int[n];
        dp = new int[k+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i =0; i<n; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        // main logic
        solution();

        // output
        System.out.println(dp[k]==Integer.MAX_VALUE?-1:dp[k]);



    }

    private static void solution() {
        dp[0] = 0;

        for(int i=0; i<n; i++){
            int coin = coins[i];

            for(int j=1; j<=k; j++){
                if(j-coin >= 0 && dp[j-coin] != Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j-coin] + 1, dp[j]);
                }
            }

        }


    }
}
