package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n+1][2];
        int[] dp = new int[k+1];
        Arrays.fill(dp, Integer.MAX_VALUE);


        for(int i =1; i<n+1; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // main logic
        dp[0] = 0;

        // dp[k] = ?
        for(int i=1; i<n+1; i++){
            int w = arr[i][0];
            int v = arr[i][1];

            for(int j= w; j<=k; j++){
                if(w > j){
                    dp[j] = dp[j-1];
                }
                else if(dp[j - w] != Integer.MAX_VALUE){
                    dp[j] = Math.max(dp[j], dp[j - w] + v);
                }
            }

        }

        System.out.println(dp[k]);




    }
}
